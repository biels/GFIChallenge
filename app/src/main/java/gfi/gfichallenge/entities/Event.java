package gfi.gfichallenge.entities;

import java.util.UUID;

/**
 * Created by Biel on 8/10/2016.
 */
public class Event {
    Sequence sequence;
    Long timeToStart;
    UUID uuid;
    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
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
