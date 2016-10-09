package gfi.gfichallenge;

import org.junit.Test;


public class RestTest {
    @Test
    public void isEventClientCorrect(){
        ScheduledSequenceClient c = new ScheduledSequenceClient();
        c.refresh();
//        assertTrue(c.getEvent().getAnimation().getAnimationFrames().size() > 0);
    }
}
