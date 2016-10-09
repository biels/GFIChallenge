package gfi.gfichallenge;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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

    public ScheduledSequenceClient(int pos) {
        this.pos = pos;
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
