import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;

public class SnakePanel extends JPanel implements KeyListener, Runnable{

    private final int sizeX = 600, sizeY = 650;
    private boolean isPlaying = true;
    private boolean isPaused = false;
    private boolean canIMove = true;
    private Integer points = 0;
    private Integer bestPoints = 0;
    private Snake snake;
    private Score score;
    private Thread thread;
    private boolean left, right, up, down;

    private URL takeCoinSound;
    private URL gameOverSound;

    private Color[] kolory;

    private SuperScore superScore;
    private Thread superScoreThread;

    public SnakePanel(){
        setPreferredSize(new Dimension(sizeX,sizeY));
        addKeyListener(this);
        setFocusable(true);

        try {
            takeCoinSound = new File("sounds\\TakeScore.wav").toURI().toURL();
            gameOverSound = new File("sounds\\GameOver.wav").toURI().toURL();
        }catch(Exception e){
            e.printStackTrace();
        }

        kolory = new Color[3];
        kolory[0] = new Color(37, 116, 37);
        kolory[1] = new Color(0, 41, 102);
        kolory[2] = new Color(22, 22, 22);

        left = false;
        right = true;
        up = false;
        down = false;

        snake = new Snake();
        score = new Score(20,20);

        superScore = new SuperScore(20,20);
        superScoreThread = new Thread(superScore);
        superScoreThread.start();

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        while(isPlaying) {
            if (up) {
                if ((snake.ogonek.get(snake.ogonek.size() - 1).indeksX == score.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY - 1 == score.indeksY)) takeScore();
                else if((snake.ogonek.get(snake.ogonek.size() - 1).indeksX == superScore.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY - 1 == superScore.indeksY) && superScore.canIShow)
                    takeSuperScore();
                else {
                    if (snake.ogonek.get(snake.ogonek.size() - 1).indeksY - 1 > -1) {
                        if(snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY - 1]
                                == snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY]) isPlaying = false;
                        else snake.moveUp();
                    }
                    else isPlaying = false;
                }
            }

            else if (down) {
                if ((snake.ogonek.get(snake.ogonek.size() - 1).indeksX == score.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY + 1 == score.indeksY)) takeScore();
                else if((snake.ogonek.get(snake.ogonek.size() - 1).indeksX == superScore.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY + 1 == superScore.indeksY) && superScore.canIShow)
                    takeSuperScore();
                else {
                    if (snake.ogonek.get(snake.ogonek.size() - 1).indeksY + 1 < 19){
                        if(snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY + 1]
                                == snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY]) isPlaying = false;
                        else snake.moveDown();
                    }
                    else isPlaying = false;
                }
            }

            else if (left) {
                if ((snake.ogonek.get(snake.ogonek.size() - 1).indeksX - 1 == score.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY == score.indeksY)) takeScore();
                else if((snake.ogonek.get(snake.ogonek.size() - 1).indeksX - 1 == superScore.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY == superScore.indeksY) && superScore.canIShow)
                    takeSuperScore();
                else {
                    if (snake.ogonek.get(snake.ogonek.size() - 1).indeksX - 1 > -1){
                        if(snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX-1][snake.ogonek.get(snake.ogonek.size()-1).indeksY]
                                == snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY]) isPlaying = false;
                        else snake.moveLeft();
                    }
                    else isPlaying = false;

                }
            }

            else if (right) {
                if ((snake.ogonek.get(snake.ogonek.size() - 1).indeksX + 1 == score.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY == score.indeksY)) takeScore();
                else if((snake.ogonek.get(snake.ogonek.size() - 1).indeksX + 1 == superScore.indeksX && snake.ogonek.get(snake.ogonek.size() - 1).indeksY == superScore.indeksY) && (superScore.canIShow))
                    takeSuperScore();

                else {
                    if (snake.ogonek.get(snake.ogonek.size() - 1).indeksX + 1 < 19){
                        if(snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX+1][snake.ogonek.get(snake.ogonek.size()-1).indeksY]
                                == snake.ogonek.get(snake.ogonek.size()-1).potwierdzenie[snake.ogonek.get(snake.ogonek.size()-1).indeksX][snake.ogonek.get(snake.ogonek.size()-1).indeksY]) isPlaying = false;
                        else snake.moveRight();
                    }
                    else isPlaying = false;
                }
            }
            try {
                Thread.sleep(80);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            repaint();

            canIMove = true;

            while(isPaused){
                try {
                    Thread.sleep(80);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        makeSound(gameOverSound);
        if(bestPoints < points) bestPoints = points;
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,sizeX, sizeY);

        g.setColor(kolory[0]);
        g.fillRect(2,2,sizeX-4,sizeY-4);


        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(10,10,sizeX-20, sizeX-20);

        g.setColor(kolory[1]);
        g.fillRect(12,12,sizeX-24,sizeX-24);

        g.setColor(kolory[2]);
        g.drawString("Score: " + points.toString(), 500,610);
        g.drawString("Best Score: " + bestPoints.toString(), 250,610);

        g.drawString("P - Paused", 12,640);
        g.drawString("R - Restart after Death", 120,640);

        g.setColor(Color.GREEN);
        snake.ogonek.forEach(e -> g.fillRect(e.wspolrzedne[e.indeksX], e.wspolrzedne[e.indeksY], e.szerokosc, e.wysokosc));

        g.setColor(Color.RED);
        g.fillOval(score.wspolrzedne[score.indeksX], score.wspolrzedne[score.indeksY], score.szerokosc, score.wysokosc);

        if(isPaused) g.drawString("Game Paused", 75,610);
        if(!isPlaying) g.drawString("Game Over", 75,610);


        if(superScore.canIShow) {
            g.setColor(Color.WHITE);
            g.fillOval(superScore.wspolrzedne[superScore.indeksX], superScore.wspolrzedne[superScore.indeksY], superScore.szerokosc, superScore.wysokosc);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int pressedKey = e.getKeyCode();

        if(!isPaused) {

            if(canIMove) {
                if (pressedKey == KeyEvent.VK_W) {
                    if (!down) {
                        up = true;
                        down = false;
                        right = false;
                        left = false;
                    }
                } else if (pressedKey == KeyEvent.VK_S) {
                    if (!up) {
                        down = true;
                        up = false;
                        right = false;
                        left = false;
                    }
                } else if (pressedKey == KeyEvent.VK_A) {
                    if (!right) {
                        left = true;
                        right = false;
                        up = false;
                        down = false;
                    }
                } else if (pressedKey == KeyEvent.VK_D) {
                    if (!left) {
                        right = true;
                        left = false;
                        up = false;
                        down = false;
                    }
                }
                canIMove = false;
            }
        }

        if (pressedKey == KeyEvent.VK_R){
            if(!isPlaying){
                for(int i=0;i<snake.ogonek.get(0).potwierdzenie.length;i++){
                    for(int j=0;j<snake.ogonek.get(0).potwierdzenie[i].length;j++){
                        snake.ogonek.get(0).potwierdzenie[i][j] = false;
                    }
                }

                isPlaying = true;
                snake = new Snake();
                score.makeCoordinates();
                points = 0;

                thread = new Thread(this);
                thread.start();

                up = false;
                down = false;
                left = false;
                right = true;
            }
        }

        else if (pressedKey == KeyEvent.VK_P){
            if(isPlaying) {
                if (isPaused) isPaused = false;
                else isPaused = true;
            }
        }
    }

    private void takeSuperScore(){
        snake.ogonek.add(new Tail(superScore.indeksX, superScore.indeksY, 29, 29));
        superScoreThread.interrupt();
        points += 10;

        makeSound(takeCoinSound);

        superScore = new SuperScore(20, 20);
        superScoreThread = new Thread(superScore);
        superScoreThread.start();
    }

    private  void takeScore(){
        snake.ogonek.add(new Tail(score.indeksX, score.indeksY, 29, 29));
        score.makeCoordinates();
        points++;
        makeSound(takeCoinSound);
    }

    public void makeSound(URL soundFile){
        try{
            AudioClip audioClip = Applet.newAudioClip(soundFile);
            audioClip.play();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}