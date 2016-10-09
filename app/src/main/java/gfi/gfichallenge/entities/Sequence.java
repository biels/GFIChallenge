package gfi.gfichallenge.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biel on 8/10/2016.
 */
public class Sequence {
    List<SequenceFrame> sequenceFrames = new ArrayList<>();

    public List<SequenceFrame> getSequenceFrames() {
        return sequenceFrames;
    }

    public void setSequenceFrames(List<SequenceFrame> sequenceFrames) {
        this.sequenceFrames = sequenceFrames;
    }
}
