package gfi.gfichallenge.entities;

/**
 * Created by Biel on 8/10/2016.
 */
public class Event {
    Animation  animation;
    Long startInstant;

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Long getStartIntant() {
        return startInstant;
    }

    public void setStartIntant(Long startIntant) {
        this.startInstant = startIntant;
    }
}
