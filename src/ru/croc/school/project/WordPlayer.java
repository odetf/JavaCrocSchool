package ru.croc.school.project;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class WordPlayer {

    private String MP3file;
    private AudioInputStream audioInputStream;
    private Clip clip;


    public WordPlayer(String MP3file) {
        try {
            this.MP3file = MP3file;
            this.audioInputStream = AudioSystem.getAudioInputStream(new File(MP3file));
            this.clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void playWord() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void restartWord() {
        try {
            clip.stop();
            clip.close();
            audioInputStream = AudioSystem.getAudioInputStream(new File(MP3file));
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            this.playWord();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void stopAndClose() {
        clip.stop();
        clip.close();
    }
}
