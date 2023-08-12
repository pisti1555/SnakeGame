package home.gyak;

import home.gyak.gui.GameMenu;
import home.gyak.media.Settings;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();
        new GameMenu(settings);
    }
}