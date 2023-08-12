package home.gyak.gui;

import home.gyak.media.GameAudio;
import home.gyak.media.GameImage;
import home.gyak.resource_handling.Score;
import home.gyak.media.Settings;
import home.gyak.serialize.LoadHighestScore;
import home.gyak.serialize.NewHighestScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    Settings settings;
    GameAudio audio = new GameAudio();
    GameImage image = new GameImage();
    GameFrame frame;
    Score score = new Score();

    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    JLabel gameOverScreen;


    GamePanel(GameFrame frame, Settings setting){
        this.settings = setting;

        //check if .ser exists
        File ser = new File("C:\\Users\\Public\\Documents\\score.ser");
        if(!ser.exists()) {
            if(!ser.canWrite()) {
                ser.setWritable(true);
            }
            if(!ser.canRead()) {
                ser.setReadable(true);
            }
            new NewHighestScore(score, applesEaten);
        }

        this.frame = frame;
        random = new Random();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        //check highest score
        LoadHighestScore load = new LoadHighestScore();
        score = load.score;

        if(settings.isGameMusic()) {
            audio.playGameMusicClip();
        }

        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if(running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.WHITE);
            g.setFont( new Font("Ink Free",Font.BOLD, 30));
            g.drawString("Score: "+applesEaten, 1150,40);
            g.drawString("Best: "+score.highestScore, 1122,80);


            if(applesEaten==1) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Ink Free", Font.BOLD, 60));
                g.drawString("Lets begin", 50,50);
            }
            if(applesEaten==10) {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Ink Free", Font.BOLD, 50));
                g.drawString("Dont stop", 70, 70);
            }
            if(applesEaten==20) {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Ink Free", Font.BOLD, 40));
                g.drawString("Professional", x[0]+70, y[0]+70);
            }
            if(applesEaten==30) {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Ink Free", Font.BOLD, 40));
                g.drawString("God mode", x[0]+70, y[0]+70);
            }
        }
        else {
            gameOver(g);
        }

    }
    public void newApple() {
        appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }

    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
            audio.playBiteClip();
        }
    }
    public void checkCollisions() {
        //checks if head collides with body
        for(int i = bodyParts;i>0;i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //check if head touches left border
        if(x[0] < 0) {
            running = false;
        }
        //check if head touches right border
        if(x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //check if head touches top border
        if(y[0] < 0) {
            running = false;
        }
        //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
        //stop game music
        audio.stopGameMusicClip();
        //PNG background
        gameOverScreen = new JLabel();
        gameOverScreen.setIcon(image.getCatIcon());
        gameOverScreen.setBounds(SCREEN_WIDTH-image.getCatIcon().getIconWidth(),0,image.getCatIcon().getIconWidth(),image.getCatIcon().getIconHeight());
        gameOverScreen.setVisible(true);

        //Score
        g.setColor(new Color(50,150,200));
        g.setFont( new Font("Comic Sans MS",Font.BOLD, 100));
        g.drawString("Score: "+applesEaten, UNIT_SIZE, UNIT_SIZE*3);

        //Game Over text and sound
        Random random = new Random();
        int rand = random.nextInt(3);

        g.setColor(new Color(200,50,50));
        if(applesEaten>score.highestScore) {
            g.setFont( new Font("Ink Free",Font.BOLD, 100));
            g.drawString("NEW", UNIT_SIZE+150, 300);
            g.drawString("BEST", UNIT_SIZE, 400);
            g.drawString("BRAUU :DDD", 20, 500);
            if(settings.isGameOverMusic()) {
                audio.playSoundClip("/gameOverSound.wav");
            }
        }else if(applesEaten<10) {
            g.setFont( new Font("Ink Free",Font.BOLD, 140));
            g.drawString("Loser", UNIT_SIZE, 350);
            switch (rand) {
                case 0 -> {
                    if(settings.isGameOverMusic()) {
                        audio.playSoundClip("/benaGameOverSound.wav");
                    }
                    gameOverScreen.setIcon(image.getMbdFlyout());
                    gameOverScreen.setBounds(SCREEN_WIDTH-image.mbdFlyout.getIconWidth(),0,image.mbdFlyout.getIconWidth(),image.mbdFlyout.getIconHeight());
                }
                case 1 -> {
                    if(settings.isGameOverMusic()) {
                        audio.playSoundClip("/benaGameOverSound.wav");
                    }
                    gameOverScreen.setIcon(image.getMbdCockbite());
                    gameOverScreen.setBounds(SCREEN_WIDTH-image.getMbdCockbite().getIconWidth(),0,image.getMbdCockbite().getIconWidth(),image.getMbdCockbite().getIconHeight());

                }
                case 2 -> {
                    if(settings.isGameOverMusic()) {
                        audio.playSoundClip("/benaGameOverSound.wav");
                    }
                    gameOverScreen.setIcon(image.getMbdSlide());
                    gameOverScreen.setBounds(SCREEN_WIDTH-image.getMbdSlide().getIconWidth(),0,image.getMbdSlide().getIconWidth(),image.getMbdSlide().getIconHeight());
                }
            }
        } else if(applesEaten>=10 && applesEaten<20) {
            g.setFont( new Font("Ink Free",Font.BOLD, 120));
            g.drawString("You can", 30, 300);
            g.drawString("do better", 40, 400);
            if(settings.isGameOverMusic()) {
                audio.playSoundClip("/gameOverSound.wav");
            }
            gameOverScreen.setIcon(image.getJustDoIt());
            gameOverScreen.setBounds(SCREEN_WIDTH-image.getJustDoIt().getIconWidth(),0,image.getJustDoIt().getIconWidth(),image.getJustDoIt().getIconHeight());
        } else if(applesEaten>=20) {
            g.setFont( new Font("Ink Free",Font.BOLD, 120));
            g.drawString("Not", 30, 300);
            g.drawString("bad", 40, 400);
            if(settings.isGameOverMusic()) {
                audio.playSoundClip("/gameOverSound.wav");
            }
        }
        this.add(gameOverScreen);

        System.out.println("loaded highest score: "+score.highestScore);
        if(applesEaten>score.highestScore) {
            new NewHighestScore(score, applesEaten);
            System.out.println("new highest score: "+score.highestScore);
        }

        //Add buttons to play again or exit to menu
        JButton playAgain = new JButton("New game");
        playAgain.setBounds(50,600,200,100);
        playAgain.setFocusable(false);
        playAgain.setFont(new Font("MV Boli", Font.BOLD, 30));
        playAgain.setBackground(Color.MAGENTA);
        playAgain.setForeground(Color.black);
        playAgain.addActionListener(e -> {
            if(settings.isGameOverMusic()) {
                audio.stopSoundClip();
            }
            frame.frame.dispose();
            new GameFrame(settings);
        });
        this.add(playAgain);

        JButton exitButton = new JButton();
        exitButton.setText("<html>Exit<br />to menu</html>");
        exitButton.setBounds(300,600,200,100);
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        exitButton.setBackground(Color.MAGENTA);
        exitButton.setForeground(Color.black);
        exitButton.addActionListener(e -> {
            if(settings.isGameOverMusic()) {
                audio.stopSoundClip();
            }
            frame.frame.dispose();
            new GameMenu(settings);
        });
        this.add(exitButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (direction != 'R') {
                        direction = 'L';
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (direction != 'L') {
                        direction = 'R';
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (direction != 'D') {
                        direction = 'U';
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (direction != 'U') {
                        direction = 'D';
                    }
                }
            }
        }
    }
}