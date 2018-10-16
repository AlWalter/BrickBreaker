package myprojects.bricks;

import java.awt.*;

public class BrickGenerator {
    public int BrickMap[][];
    public int brickWidth;
    public int brickHeigth;

    public BrickGenerator(int row, int col) {
        BrickMap = new int[row][col];
        brickWidth = 540 / col;
        brickHeigth = 150 / row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < BrickMap.length; i++) {
            for (int j = 0; j < BrickMap[0].length; j++) {
                if (BrickMap[i][j] != -1) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(j * brickWidth + 80, i * brickHeigth + 50, brickWidth, brickHeigth);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeigth + 50, brickWidth, brickHeigth);
                }
            }
        }
    }

    public void brickDelete(int row, int col) {
        BrickMap[row][col] = -1;
    }
}
