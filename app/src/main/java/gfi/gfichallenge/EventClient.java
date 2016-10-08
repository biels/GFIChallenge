package gfi.gfichallenge;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import gfi.gfichallenge.entities.Event;

/**
 * Created by Biel on 8/10/2016.
 */

public class EventClient {
    final String localhost = "localhost";
    final int port = 8080;
    final String uri = "http://" + localhost + ":" + port + "/event";
    private Event event;
    public void refresh(){
        requestEvent();
    }
    private void requestEvent(){
        GetDataTask task = new GetDataTask();
        task.execute();
    }

    public Event getEvent() {
        return event;
    }
    private class GetDataTask extends AsyncTask<Void, Void, Event> {

        private String urlToGet = uri;

        public GetDataTask() {

        }

        @Override
        protected Event doInBackground(Void... params) {
            Event result = null;
            try {
                RestTemplate restTemplate = new RestTemplate();
                // Add the String message converter
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                result = restTemplate.getForObject(uri, Event.class);


            } catch (Exception e) {
                Log.e("EventClient", e.getMessage());
            }

            if (isCancelled()) {
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Event result) {
            event = result;
            Log.i("EventClient", "Got server query!");
        }
    }
}
