package gfi.gfichallenge.entities;

import java.util.UUID;

/**
 * Created by Biel on 8/10/2016.
 */
public class Event {
    Animation  animation;
    Long timeToStart;
    UUID uuid;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
