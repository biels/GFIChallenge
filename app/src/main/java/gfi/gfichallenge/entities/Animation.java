package gfi.gfichallenge.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biel on 8/10/2016.
 */
public class Animation {
    List<AnimationFrame> animationFrames = new ArrayList<>();

    public List<AnimationFrame> getAnimationFrames() {
        return animationFrames;
    }

    public void setAnimationFrames(List<AnimationFrame> animationFrames) {
        this.animationFrames = animationFrames;
    }
}
