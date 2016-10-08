package gfi.gfichallenge.entities;

/**
 * Created by Biel on 8/10/2016.
 */
public class Event {
    Animation  animation;
    Long timeToStart;

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Long getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(Long timeToStart) {
        this.timeToStart = timeToStart;
    }
}
