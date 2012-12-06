package yuuki.gui.UI;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
        
/**
 * Plays sound files. Note that only one instance of this class is necessary.
 * Simply create an instance of this class, preload your sound files if you
 * wish, and then call playSound() with the name of the audio file to play.
 * 
 * @author TF Nelson
 */
public class Audio
{
	/**
	 * Immediately caches a sound file's data if it hasn't yet been loaded.
	 * @param filename
	 */
	public void preload(String filename) {
		if (!isLoaded(filename)) {
    		try {
				loadSound(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	}
	
	/**
	 * Plays a sound file. If it hasn't yet been loaded, its data will be read
	 * from file and cached.
	 * 
	 * @param filename The name of the file.
	 */
    public void playSound(String filename)
    {
    	preload(filename);
    	beginSound(filename);
    }
    
    private class SoundPlayer implements Runnable {
		private byte[] data;
		public SoundPlayer(byte[] soundData) {
			data = Arrays.copyOf(soundData, soundData.length);
		}
		public void run() {
			ByteArrayInputStream input = new ByteArrayInputStream(data);
			try {
				AudioInputStream stream = AudioSystem.getAudioInputStream(input);
				AudioFormat format = stream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clip = (Clip) AudioSystem.getLine(info);
				clip.open(stream);
				clip.start();
				while (!clip.isRunning()) {
					Thread.sleep(10);
				}
				while (clip.isRunning()) {
					Thread.sleep(10);
				}
				clip.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private HashMap<String, byte[]> sounds;
	
	public Audio() {
		sounds = new HashMap<String, byte[]>();
	}
	
	private boolean isLoaded(String file) {
		return sounds.containsKey(file);
	}
	
	private void loadSound(String file) throws IOException {
		InputStream s = getClass().getResourceAsStream(file);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int n = 0;
		while ((n = s.read()) != -1) {
			buffer.write(n);
		}
		buffer.flush();
		byte[] sData = buffer.toByteArray();
		sounds.put(file, sData);
	}
	
	private void beginSound(String filename) {
		byte[] data = sounds.get(filename);
		SoundPlayer player = new SoundPlayer(data);
		(new Thread(player)).start();
	}
    
}
