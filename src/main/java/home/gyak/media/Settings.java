package home.gyak.media;

public class Settings {
    private boolean gameMusic = true;
    private boolean menuMusic = true;
    private boolean gameOverMusic = true;

    public boolean isGameMusic() {
        return gameMusic;
    }

    public void setGameMusic(boolean gameMusic) {
        this.gameMusic = gameMusic;
    }

    public boolean isMenuMusic() {
        return menuMusic;
    }

    public void setMenuMusic(boolean menuMusic) {
        this.menuMusic = menuMusic;
    }

    public boolean isGameOverMusic() {
        return gameOverMusic;
    }

    public void setGameOverMusic(boolean gameOverMusic) {
        this.gameOverMusic = gameOverMusic;
    }
}