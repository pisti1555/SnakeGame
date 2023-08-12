package home.gyak.gui;

import home.gyak.media.Settings;

import javax.swing.*;

public class GameFrame extends JFrame {
    JFrame frame = new JFrame();
    GamePanel panel;
    Settings settings;
    GameFrame(Settings settings) {
        this.settings = settings;
        frame.add(panel = new GamePanel(this, settings));
        frame.setTitle("Snake game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
