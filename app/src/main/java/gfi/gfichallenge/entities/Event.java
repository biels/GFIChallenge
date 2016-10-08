package gfi.gfichallenge.entities;

import java.time.Instant;
import java.util.Date;

/**
 * Created by Biel on 8/10/2016.
 */
public class Event {
    Animation  animation;
    Date startInstant;

    public Event(Animation animation, Date startInstant) {
        this.animation = animation;
        this.startInstant = startInstant;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Date getStartInstant() {
        return startInstant;
    }

    public void setStartInstant(Date startInstant) {
        this.startInstant = startInstant;
    }

    Long getTimeToStart(){
        return (startInstant.getTime() - new Date().getTime());
    }

}
