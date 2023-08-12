package home.gyak.media;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class GameAudio {
    private Clip gameMusic;
    private Clip gameOverSound;
    private Clip biteSound;
    private Clip menuMusic;
    private Clip loserGameOverSound;
    private Clip soundToPlay;

    public GameAudio() {
        setUpGameMusicClip();
        setUpMenuMusicClip();
        setUpGameOverClip();
        setUpLoserClip();
    }

    public void playGameOverClip() {
        try (InputStream in = getClass().getResourceAsStream("/gameOverSound.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                gameOverSound = AudioSystem.getClip();
                gameOverSound.open(audioIn);
                gameOverSound.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopGameOverClip() {
        gameOverSound.stop();
        gameOverSound.close();
    }

    public void playMenuMusicClip() {
        try (InputStream in = getClass().getResourceAsStream("/menuMusic.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                menuMusic = AudioSystem.getClip();
                menuMusic.open(audioIn);
                menuMusic.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopMenuMusicClip() {
        menuMusic.stop();
        menuMusic.close();
    }

    public void playLoserClip() {
        try (InputStream in = getClass().getResourceAsStream("/benaGameOverSound.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                loserGameOverSound = AudioSystem.getClip();
                loserGameOverSound.open(audioIn);
                loserGameOverSound.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopLoserClip() {
        loserGameOverSound.stop();
        loserGameOverSound.close();
    }

    public void playGameMusicClip() {
        try (InputStream in = getClass().getResourceAsStream("/gameMusic.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                gameMusic = AudioSystem.getClip();
                gameMusic.open(audioIn);
                gameMusic.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopGameMusicClip() {
        gameMusic.stop();
        gameMusic.close();
    }

    public void playBiteClip() {
        try (InputStream in = getClass().getResourceAsStream("/biteSound.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                biteSound = AudioSystem.getClip();
                biteSound.open(audioIn);
                biteSound.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSoundClip(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                soundToPlay = AudioSystem.getClip();
                soundToPlay.open(audioIn);
                soundToPlay.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopSoundClip() {
        soundToPlay.stop();
        soundToPlay.close();
    }

    public boolean menuMusicIsPlaying() {
        return menuMusic.isRunning();
    }

    //open audio files in constructor
    private void setUpGameOverClip() {
        try (InputStream in = getClass().getResourceAsStream("/gameOverSound.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                gameOverSound = AudioSystem.getClip();
                gameOverSound.open(audioIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setUpLoserClip() {
        try (InputStream in = getClass().getResourceAsStream("/benaGameOverSound.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                loserGameOverSound = AudioSystem.getClip();
                loserGameOverSound.open(audioIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setUpMenuMusicClip() {
        try (InputStream in = getClass().getResourceAsStream("/menuMusic.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                menuMusic = AudioSystem.getClip();
                menuMusic.open(audioIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setUpGameMusicClip() {
        try (InputStream in = getClass().getResourceAsStream("/gameMusic.wav")) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)) {
                gameMusic = AudioSystem.getClip();
                gameMusic.open(audioIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
