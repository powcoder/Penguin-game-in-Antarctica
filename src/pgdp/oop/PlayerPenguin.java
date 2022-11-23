https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }

    //TODO
    public boolean move(int newX, int newY) {
        //game continues
        if (antarktis[newX][newY] == null || this.canEat(antarktis[newX][newY])) {
            this.x = newX;
            this.y = newY;
            return false;
        }
        //win
        else if (antarktis[newX][newY].getClass().isAssignableFrom(Penguin.class)) {
            this.x = newX;
            this.y = newY;
            return true;
        }
        //lose
        else {
            this.x = newX;
            this.y = newY;
            return true;
        }

    }
}
