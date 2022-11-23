https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.awt.event.WindowEvent;

public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);
        setupMaze();
        draw();
        gameLoop();
        // Close the opnend frame
        closeFrame();


    }

    private static void gameLoop() {
        boolean end = false;
        while (!end) {
            // TODO maybe
            draw();
            // TODO maybe
            if (currentEvent == UP) {
                end = playerPenguin.move(playerPenguin.x, playerPenguin.y - 1);

            }
            else if (currentEvent == DOWN) {
                end = playerPenguin.move(playerPenguin.x, playerPenguin.y + 1);
            }
            else if (currentEvent == LEFT) {
                end = playerPenguin.move(playerPenguin.x - 1, playerPenguin.y);
            }
            else if (currentEvent == RIGHT) {
                end = playerPenguin.move(playerPenguin.x + 1, playerPenguin.y);
            }
            if (end) return;
            else moveAll();
            currentEvent = NOTHING;


            // TODO maybe
        }
    }

    private static void moveAll() {
        // TODO
        //playerPenguin.move()
        for (int i = 0; i < whales.length; i++) {
            whales[i].move();
        }
        for (int i = 0; i < leopardSeals.length; i++) {
            leopardSeals[i].move();
        }
        lostPenguin.move();
        for (int i = 0; i < fishes.length; i ++) {
            fishes[i].move();
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
