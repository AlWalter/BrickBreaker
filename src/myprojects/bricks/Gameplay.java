package myprojects.bricks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score;
    private int totalBricks;
    private Timer timer;
    private int delay = 8;
    private int playerX;
    private int ballPosX;
    private int ballPosY;
    private int ballXDirection;
    private int ballYDirection;
    private BrickGenerator brickGenerator;
    private GraphiksGenerator graphiksGenerator;

    public Gameplay() {
        setAll();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        graphiksGenerator.draw(g, score, playerX, ballPosX, ballPosY);
        brickGenerator.draw((Graphics2D) g);
        //win
        if (totalBricks <= 0) {
            gameEnd("You won!", g);
        }
        //game over
        if (ballPosY > 570) {
            gameEnd("You Lose!", g);
        }
        g.dispose();
    }

    private void gameEnd(String endScreenText, Graphics g) {
        play = false;
        ballXDirection = 0;
        ballYDirection = 0;
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString(endScreenText + " Your score: " + score, 150, 300);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press Enter to restart", 230, 350);
    }

    private void setAll() {
        ballPosX = 120;
        ballPosY = 350;
        ballXDirection = 2;
        ballYDirection = -2;
        playerX = 310;
        score = 0;
        int row = 4;
        int col = 9;
        totalBricks = row * col;
        brickGenerator = new BrickGenerator(row, col);
        graphiksGenerator = new GraphiksGenerator();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballChangeYDirection();
            }
            brickAndBallInteraction();
            ballPosX += ballXDirection;
            ballPosY += ballYDirection;
            if (ballPosX < 0) {
                ballChangeXDirection();
            }
            if (ballPosY < 0) {
                ballChangeYDirection();
            }
            if (ballPosX > 670) {
                ballChangeXDirection();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 581) {
                playerX = 581;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                setAll();
                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    private void ballChangeYDirection() {
        ballYDirection = -ballYDirection;
    }

    private void ballChangeXDirection() {
        ballXDirection = -ballXDirection;
    }

    private void brickAndBallInteraction() {
        A:
        for (int i = 0; i < brickGenerator.BrickMap.length; i++) {
            for (int j = 0; j < brickGenerator.BrickMap[0].length; j++) {
                if (brickGenerator.BrickMap[i][j] != -1) {
                    int brickWith = brickGenerator.brickWidth;
                    int brickHeigth = brickGenerator.brickHeigth;
                    int brickX = j * brickWith + 80;
                    int brickY = i * brickHeigth + 50;
                    Rectangle brickRectangle = new Rectangle(brickX, brickY, brickWith, brickHeigth);
                    Rectangle ballRectangle = new Rectangle(ballPosX, ballPosY, 20, 20);
                    if (ballRectangle.intersects(brickRectangle)) {
                        brickGenerator.brickDelete(i, j);
                        totalBricks--;
                        score += 5;
                        if (ballPosX + 19 <= brickRectangle.x || ballPosX + 1 >= brickRectangle.x + brickRectangle.width) {
                            ballChangeXDirection();
                        } else {
                            ballChangeYDirection();
                        }
                        break A;
                    }
                }
            }
        }
    }
}
