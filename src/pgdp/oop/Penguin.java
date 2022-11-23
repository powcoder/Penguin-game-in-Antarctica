https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Penguin extends Animal {
  static String filename = "tux.png";

  public Penguin(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  protected boolean eatenBy(Penguin penguin) {
    return false;
  }
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return false;
  }
  protected boolean eatenBy(Whale whale){
    this.alive = false;
    antarktis[x][y] = null;
    return true;
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
