package yuuki.gui.UI;

import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Plays Audio tracks.
 * 
 * @author Caleb Smith
 * @version 11/29/12
 */
public class Audio {
    AudioPlayer BGMPlayer = AudioPlayer.player;
    //AudioStream BGM;
    AudioData BGMData;
            
    public void playAudio(AudioStream BGM)
    {        
        try
            {
            BGMData = BGM.getData();
            AudioDataStream loop = null;
            loop = new AudioDataStream(BGMData);
            BGMPlayer.start(loop);
            }
            catch(IOException error)
            {
                System.out.println("Audio play @ Audio.playAudio went wrong.");
            }
    }
    
}
