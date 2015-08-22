package com.sagicode.engine.sounds;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.sagicode.engine.util.Log;

public class SoundClip {
	
	private Clip clip;
	private FloatControl control;
	
	public SoundClip(String path) {
		try {
			InputStream audioSrc = getClass().getResourceAsStream(path);
			InputStream BufferedInt = new BufferedInputStream(audioSrc);
			AudioInputStream ais = AudioSystem.getAudioInputStream(BufferedInt);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		}catch(Exception e) {
			Log.error("SoundClip", e.getMessage());
			e.printStackTrace();
			return;
		}
	}
	
	public void play() {
		if(clip == null) {
			Log.error("SoundClip", "Could not play sound, Sound is not set.");
			return;
		}
		stop();
		clip.setFramePosition(0);
		while(!clip.isRunning()) {
			clip.start();
		}
	}
	
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	public void close() {
		stop();
		clip.drain();
		clip.close();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		while(!clip.isRunning()) {
			clip.start();
		}
	}
	
	public void setVolume(float volume) {
		control.setValue(volume);
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
	
}
