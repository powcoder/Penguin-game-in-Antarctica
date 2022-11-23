https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

public abstract class Animal {
  protected int x, y;
  static String filename;
  protected File f;
  protected Image image;
  protected boolean alive;

  protected static Animal[][] antarktis;

  public Animal(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void move() {

    //TODO
    if (alive) {
      if (antarktis[(x - 1 + 41) % 41][y] != null || antarktis[x][(y - 1 + 41) % 41] != null ||
              antarktis[(x + 1) % 41][y] != null || antarktis[x][(y + 1) % 41] != null ) {
        //left
        if(canEat(antarktis[(x - 1 + 41) % 41][y]) && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x - 2 + 41) % 41][y].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)) {
          antarktis[(x - 1 + 41) % 41][y].alive = false;
          x = (x - 1 + 41) % 41;
        }
        //up
        else if (canEat(antarktis[x][(y - 1 + 41) % 41]) && !antarktis[x][(y - 2 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)) {
          antarktis[x][y - 1].alive = false;
          y = (y - 1 + 41) % 41;
        }
        //right
        else if (canEat(antarktis[(x + 1) % 41][y]) && !antarktis[(x + 2) % 41][y].canEat(this)
                && !antarktis[(x + 1) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x + 1) % 41][(y - 1 + 41) % 41].canEat(this)) {
          antarktis[(x + 1) % 41][y].alive = false;
          x = (x + 1) % 41;
        }
        //down
        else if (canEat(antarktis[x][(y + 1) % 41]) && !antarktis[x][(y + 2) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x + 1) % 41][(y + 1) % 41].canEat(this)) {
          antarktis[(x + 1) % 41][y].alive = false;
          y = (y + 1) % 41;
        }
      }
      else if (antarktis[(x - 1 + 41) % 41][y] == null || antarktis[x][(y - 1 + 41) % 41] == null ||
              antarktis[(x + 1) % 41][y] == null || antarktis[x][(y + 1) % 41] == null) {
        //left
        if (antarktis[(x - 1 + 41) % 41][y] == null
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)
                && !antarktis[(x - 2 + 41) % 41][y].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)) {
          x = x - 1;
        }
        //up
        else if (antarktis[x][(y - 1 + 41) % 41] == null && !antarktis[x][(y - 2 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y - 1 + 41) % 41].canEat(this)
                && !antarktis[(x - 1 + 41) % 41][(y + 1) % 41].canEat(this)) {
          y = (y - 1 + 41) % 41;
        }
        //right
        else if (antarktis[(x + 1) % 41][y] == null && !antarktis[(x + 2) % 41][y].canEat(this)
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
      }
      else {
        x = x;
        y = y;
      }
    }
  }

  public abstract boolean canEat(Animal animal);

  protected abstract boolean eatenBy(Penguin penguin);
  protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
  protected abstract boolean eatenBy(Whale whale);
  protected abstract boolean eatenBy(LeopardSeal leopardSeal);
  protected abstract boolean eatenBy(Fish fish);

  public static void setAntarktis(Animal[][] antarktis) {
    Animal.antarktis = antarktis;
  }
  // Graphics Stuff - You don't have to do anything here

  private void paintSymbol(Graphics g, Color c, int height, int width) {
    GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
    ((Graphics2D) g).setPaint(gradient);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
        (int) (height * 0.5));
  }

  public void draw(Graphics g, int height, int width) {
    if (image == null) {
      paintSymbol(g, Color.YELLOW, height, width);
      return;
    }
    ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
        image.getHeight(null), null);
  }
}
