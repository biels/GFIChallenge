package gfi.gfichallenge;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import gfi.gfichallenge.entities.ScheduledSequence;

/**
 * Created by Biel on 8/10/2016.
 */

public class ScheduledSequenceClient {
    final private int pos;
    final private String localhost = "10.192.127.192";
    final private int port = 8080;
    final private String uri = "http://" + localhost + ":" + port + "/event/sequence";
    private ScheduledSequence event;

    public Long getPing() {
        return ping;
    }

    public void setPing(Long ping) {
        this.ping = ping;
    }

    private Long ping = 50L;

    public ScheduledSequenceClient(int pos) {
        this.pos = pos;
    }
    public void updatePing(){
       GetPingTask getPingTask = new GetPingTask();
        getPingTask.execute();
    }
    public void refresh(){
        requestScheduledSequence();
    }
    private void requestScheduledSequence(){
        GetDataTask task = new GetDataTask();
        task.execute();
    }

    public ScheduledSequence getEvent() {
        return event;
    }
    private class GetPingTask extends  AsyncTask<Void, Void, Long>{

        @Override
        protected Long doInBackground(Void... voids) {
            long currentTime = new Date().getTime();
            try {
                boolean isPinged = InetAddress.getByName(localhost).isReachable(2000); // 2 seconds
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTime = new Date().getTime() - currentTime;
            return currentTime;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            ping = aLong;
        }
    }
    private class GetDataTask extends AsyncTask<Void, Void, ScheduledSequence> {

        private String urlToGet = uri;

        public GetDataTask() {

        }

        @Override
        protected ScheduledSequence doInBackground(Void... params) {
            ScheduledSequence result = null;
            try {
                RestTemplate restTemplate = new RestTemplate();
                // Add the String message converter
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                result = restTemplate.getForObject(uri + "/"+ pos, ScheduledSequence.class);

            } catch (Exception e) {
                Log.e("ScheduledSequenceClient", e.getMessage());
            }

            if (isCancelled()) {
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(ScheduledSequence result) {
            event = result;
            Log.i("ScheduledSequenceClient", "Got server query!");
        }
    }
}
