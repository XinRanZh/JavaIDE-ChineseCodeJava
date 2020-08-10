package ui;

import java.applet.Applet;
import java.applet.AudioClip;

public class audio extends Applet {
    public void play() {
        AudioClip ac = getAudioClip(getCodeBase(),"");
    }
}
