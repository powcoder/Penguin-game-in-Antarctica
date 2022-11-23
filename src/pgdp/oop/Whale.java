https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Whale extends Animal {
  static String filename = "whale.png";

  public Whale(int x, int y) {
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
    return false;
  }
  protected boolean eatenBy(LeopardSeal leopardSeal){
    return false;
  }
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
