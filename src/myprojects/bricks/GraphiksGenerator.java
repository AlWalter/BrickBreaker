package myprojects.bricks;

import java.awt.*;

public class GraphiksGenerator {

    public void draw(Graphics g, int score, int playerX, int ballPosX, int ballPosY) {
        createBackground(Color.white, g);
        createBorders(Color.darkGray, g);
        createScores(Color.black, g, score);
        createPaddle(Color.black, g, playerX);
        createBall(Color.red, g, ballPosX, ballPosY);
    }

    private void createBackground(Color color, Graphics g) {
        g.setColor(color);
        g.fillRect(1, 1, 692, 592);
    }

    private void createBorders(Color color, Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(681, 0, 3, 592);
    }

    private void createScores(Color color, Graphics g, int score) {
        g.setColor(color);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
    }

    private void createPaddle(Color color, Graphics g, int playerX) {
        g.setColor(color);
        g.fillRect(playerX, 550, 100, 8);
    }

    private void createBall(Color color, Graphics g, int ballPosX, int ballPosY) {
        g.setColor(color);
        g.fillRect(ballPosX, ballPosY, 20, 20);
    }
}
