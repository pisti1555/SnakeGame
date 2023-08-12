package home.gyak.gui;

import home.gyak.media.GameAudio;
import home.gyak.media.GameImage;
import home.gyak.media.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu {
    Settings settings;
    GameAudio audio = new GameAudio();
    private final GameImage image = new GameImage();
    JFrame menu = new JFrame("Main menu");
    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");
    JButton exitButton = new JButton("Exit");
    JPanel imagePanel = new JPanel();
    JLabel imageLabel = new JLabel();
    JFrame settingsFrame = new JFrame();
    ImageIcon menuImage;
    private final int IMAGE_WIDTH = 711;
    private final int IMAGE_HEIGHT = 600;

    public GameMenu(Settings settings) {
        this.settings = settings;
        if(settings.isMenuMusic()) {
            audio.playMenuMusicClip();
        }
        menuImage = new ImageIcon(image.getMenuImageIconFull().getImage());
        imageLabel.setIcon(menuImage);
        imageLabel.setBounds(-75,0,840,IMAGE_HEIGHT);
        imageLabel.setVisible(true);

        imagePanel.setPreferredSize(new Dimension(IMAGE_WIDTH,IMAGE_HEIGHT));
        imagePanel.setBounds(0,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        imagePanel.setBackground(Color.BLACK);
        imagePanel.setLayout(null);
        imagePanel.add(imageLabel);
        imagePanel.setOpaque(true);
        imagePanel.setVisible(true);

        playButton.setFocusable(false);
        playButton.setFont(new Font("Ink Free", Font.BOLD, 25));
        playButton.setBackground(Color.magenta);
        playButton.setForeground(Color.black);
        playButton.setBounds(50, 450, 150,70);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                GameFrame frame = new GameFrame(settings);
                audio.stopMenuMusicClip();
            }
        });

        settingsButton.setFocusable(false);
        settingsButton.setFont(new Font("Ink Free", Font.BOLD, 25));
        settingsButton.setBackground(Color.magenta);
        settingsButton.setForeground(Color.black);
        settingsButton.setBounds(250, 450, 200,70);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = new JButton();
                button.setText("OK");
                button.setFont(new Font("MV Boli", Font.BOLD, 25));
                button.setForeground(Color.magenta);
                button.setBackground(Color.DARK_GRAY);
                button.setBounds(400,130,75,50);
                button.setFocusable(false);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settingsFrame.dispose();
                    }
                });

                JCheckBox gameMusic = new JCheckBox("Game music on/off");
                JCheckBox menuMusic = new JCheckBox("Menu music on/off");
                JCheckBox gameOverMusic = new JCheckBox("G-O sound on/off");

                gameMusic.setFont(new Font("MV Boli", Font.BOLD, 20));
                gameMusic.setForeground(Color.magenta);
                gameMusic.setBounds(25,25,300,50);
                gameMusic.setFocusable(false);
                gameMusic.setIcon(image.getXIcon());
                gameMusic.setSelectedIcon(image.getCheckIcon());
                gameMusic.setSelected(settings.isGameMusic());
                gameMusic.setIconTextGap(25);
                gameMusic.setVisible(true);
                gameMusic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settings.setGameMusic(gameMusic.isSelected());
                        System.out.println(settings.isGameMusic());
                    }
                });

                menuMusic.setFont(new Font("MV Boli", Font.BOLD, 20));
                menuMusic.setForeground(Color.magenta);
                menuMusic.setBounds(25,75,300,50);
                menuMusic.setFocusable(false);
                menuMusic.setIcon(image.getXIcon());
                menuMusic.setSelectedIcon(image.getCheckIcon());
                menuMusic.setSelected(settings.isMenuMusic());
                menuMusic.setIconTextGap(25);
                menuMusic.setVisible(true);
                menuMusic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settings.setMenuMusic(menuMusic.isSelected());
                        if(settings.isMenuMusic()) {
                            if(!audio.menuMusicIsPlaying()) {
                                audio.playMenuMusicClip();
                            }
                        }
                        if(!settings.isMenuMusic()) {
                            if(audio.menuMusicIsPlaying()) {
                                audio.stopMenuMusicClip();
                            }
                        }

                        System.out.println(settings.isMenuMusic());
                    }
                });

                gameOverMusic.setFont(new Font("MV Boli", Font.BOLD, 20));
                gameOverMusic.setForeground(Color.magenta);
                gameOverMusic.setBounds(25,125,300,50);
                gameOverMusic.setFocusable(false);
                gameOverMusic.setIcon(image.getXIcon());
                gameOverMusic.setSelectedIcon(image.getCheckIcon());
                gameOverMusic.setSelected(settings.isGameOverMusic());
                gameOverMusic.setIconTextGap(25);
                gameOverMusic.setVisible(true);
                gameOverMusic.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settings.setGameOverMusic(gameOverMusic.isSelected());
                        System.out.println(settings.isGameOverMusic());
                    }
                });

                settingsFrame.setSize(500,200);
                settingsFrame.setLayout(null);
                settingsFrame.add(gameMusic);
                settingsFrame.add(menuMusic);
                settingsFrame.add(gameOverMusic);
                settingsFrame.add(button);
                settingsFrame.setLocationRelativeTo(null);
                settingsFrame.setUndecorated(true);
                settingsFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
                settingsFrame.setVisible(true);
            }
        });

        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Ink Free", Font.BOLD, 25));
        exitButton.setBackground(Color.magenta);
        exitButton.setForeground(Color.black);
        exitButton.setBounds(500, 450, 150,70);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.setSize(710,IMAGE_HEIGHT);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.getContentPane().setBackground(Color.BLACK);
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);

        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0,0,IMAGE_WIDTH, IMAGE_HEIGHT);
        pane.add(imagePanel, 0);
        pane.add(playButton, 1);
        pane.add(settingsButton, 1);
        pane.add(exitButton, 1);
        pane.setLayer(imagePanel, 0);
        pane.setLayer(playButton, 1);
        pane.setLayer(settingsButton, 1);
        pane.setLayer(exitButton, 1);
        pane.setVisible(true);
        menu.add(pane);

        menu.setVisible(true);
    }
}