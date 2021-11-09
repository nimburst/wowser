package cloud.nimburst.wowzer;

import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

public class MixerLine {

    private final Mixer.Info mixer;
    private final Line.Info line;

    public MixerLine(Mixer.Info mixer, Line.Info line) {
        this.mixer = mixer;
        this.line = line;
    }

    public Mixer.Info getMixer() {
        return mixer;
    }

    public Line.Info getLine() {
        return line;
    }
}
