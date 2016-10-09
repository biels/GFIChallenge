package gfi.gfichallenge.entities;

import java.util.UUID;

/**
 * Created by jordiae on 9/10/16.
 */

public class ScheduledSequence {
    Long timeToStart;
    Sequence sequence;
    UUID uuid;

    public ScheduledSequence(Long timeToStart, Sequence sequence, UUID uuid) {
        this.timeToStart = timeToStart;
        this.sequence = sequence;
        this.uuid = uuid;
    }

    public ScheduledSequence() {
    }

    public Long getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(Long timeToStart) {
        this.timeToStart = timeToStart;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}