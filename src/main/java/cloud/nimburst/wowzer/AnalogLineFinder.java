package cloud.nimburst.wowzer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class AnalogLineFinder {

    public static List<MixerLine> getAnalogInputs() {
        List<MixerLine> lineInfos = new ArrayList<>();
        for (Mixer.Info info : AudioSystem.getMixerInfo()) {
            Mixer m = AudioSystem.getMixer(info);
            Line.Info[] lineInInfos = m.getSourceLineInfo(Port.Info.LINE_IN);
            Line.Info[] microphoneInfos = m.getSourceLineInfo(Port.Info.MICROPHONE);
            lineInfos.addAll(Arrays.asList(lineInInfos).stream().map(line -> new MixerLine(info, line)).collect(Collectors.toList()));
            lineInfos.addAll(Arrays.asList(microphoneInfos).stream().map(line -> new MixerLine(info, line)).collect(Collectors.toList()));
        }
        return lineInfos;
    }

    private AnalogLineFinder() {

    }
}
