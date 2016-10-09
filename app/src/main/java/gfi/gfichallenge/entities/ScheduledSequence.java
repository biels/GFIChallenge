package gfi.gfichallenge.entities;

/**
 * Created by jordiae on 9/10/16.
 */

public class ScheduledSequence {
    Long timeToStart;
    Sequence sequence;

    public ScheduledSequence(Long timeToStart, Sequence sequence) {
        this.timeToStart = timeToStart;
        this.sequence = sequence;
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
}