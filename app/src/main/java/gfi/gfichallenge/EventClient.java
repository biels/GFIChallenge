package gfi.gfichallenge;

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
        event = requestEvent();
    }
    private Event requestEvent(){
        RestTemplate restTemplate = new RestTemplate();
        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        Event result = restTemplate.getForObject(uri, Event.class);
        return result;
    }

    public Event getEvent() {
        return event;
    }
}
