package gfi.gfichallenge;

import org.junit.Test;

import static org.junit.Assert.*;


public class RestTest {
    @Test
    public void isEventClientCorrect(){
        EventClient c = new EventClient();
        c.refresh();
//        assertTrue(c.getEvent().getAnimation().getAnimationFrames().size() > 0);
    }
}
