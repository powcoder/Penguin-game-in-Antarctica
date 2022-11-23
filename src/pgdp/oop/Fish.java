https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
  static String filename = "fish.png";

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public void move() {
//TODO
    if (alive) {
      if (antarktis[(x - 1 + 41) % 41][y] == null || antarktis[x][(y - 1 + 41) % 41] == null ||
              antarktis[(x + 1) % 41][y] == null || antarktis[x][(y + 1) % 41] == null) {
        //up
        if (antarktis[x][(y - 1 + 41) % 41] == null && !antarktis[x][(y - 2 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)) {
          y = (y - 1 + 41) % 41;
        }
        //right
        else if (antarktis[(x + 1) % 41][y] == null && !antarktis[x + 2][y].canEat(this)
                && !antarktis[(x + 1) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x + 1) % 41][(y - 1 + 41) % 41].canEat(this)) {
          x = (x + 1) % 41;
        }
        //down
        else if (antarktis[x][(y + 1) % 41] == null && !antarktis[x][(y + 2) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x + 1) % 41][(y + 1) % 41].canEat(this)) {
          y = (y + 1) % 41;
        }
        //left
        else if (antarktis[(x - 1 + 41) % 41][y] == null
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x - 2 + 41) % 41][y].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)) {
          x = (x - 1 + 41) % 41;
        }
        else {
          x = x;
          y = y;
        }
      }
    }

  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  protected boolean eatenBy(Penguin penguin) {
    this.alive = false;
    antarktis[x][y] = null;
    return true;
  }
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    this.alive = false;
    antarktis[x][y] = null;
    return true;
  }
  protected boolean eatenBy(Whale whale){
    return false;
  }
  protected boolean eatenBy(LeopardSeal leopardSeal){
    this.alive = false;
    antarktis[x][y] = null;
    return true;
  }
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
