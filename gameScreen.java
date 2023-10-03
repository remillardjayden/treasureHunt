import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class gameScreen extends Screen {
    ItemShop shop = new ItemShop("Shop", new ArrayList<Product>(), new ArrayList<Integer>(), 1000, 100);
    public gameScreen(String title, Color color) {
        super(title, color);
    }

    public void update() {
        if(treasureSearch.p1.getRow() == treasureSearch.t.getRow() && treasureSearch.p1.getCol() == treasureSearch.t.getCol()) {
            treasureSearch.p1.increaseTreasure();
            treasureSearch.p1.increaseGold(treasureSearch.t.getWorth());
            treasureSearch.t.setRow((int)(Math.random()*15));
            treasureSearch.t.setCol((int)(Math.random()*15));
            treasureSearch.t.setWorth((int)((Math.random()*3)+1));
            treasureSearch.grid[treasureSearch.t.getRow()][treasureSearch.t.getCol()] = treasureSearch.t;
        }
        for(int r = 0; r < treasureSearch.grid.length; r++) {
            for(int c = 0; c < treasureSearch.grid[r].length; c++) {
                if(treasureSearch.p1.getRow() == r && treasureSearch.p1.getCol() == c) {
                    treasureSearch.grid[r][c] = treasureSearch.p1;
                } else if(treasureSearch.t.getRow() == r && treasureSearch.t.getCol() == c) {
                    treasureSearch.grid[r][c] = treasureSearch.t;
                } else {
                    treasureSearch.grid[r][c] = null;
                }
                for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                    if(Enemy.allEnemies.get(i).getRow() == r && Enemy.allEnemies.get(i).getCol() == c) {
                        treasureSearch.grid[r][c] = Enemy.allEnemies.get(i);
                    } 
                }
            }
        }
        for(int e = 0; e < Enemy.allEnemies.size(); e++) {
            if(treasureSearch.p1.getRow() == Enemy.allEnemies.get(e).getRow() && treasureSearch.p1.getCol() == Enemy.allEnemies.get(e).getCol()) {
                treasureSearch.p1.setRow(7);
                treasureSearch.p1.setCol(7);
                Enemy.allEnemies.clear();
                int roll = (int)(Math.random()*4);
                if(roll == 0) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy1.1.png"); }
                else if(roll == 1) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy2.1.png"); }
                else if(roll == 2) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy3.1.png"); }
                else { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy4.1.png"); }
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                    while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                }
                treasureSearch.p1.setGold(0);
                treasureSearch.p1.resetCounter();
                treasureSearch.p1.resetMaxGold();
                treasureSearch.p1.setTreasure(0);
                treasureSearch.activeScreen = treasureSearch.end;
                break;
            }
        }
        if(treasureSearch.p1.getCounter() == 10 && treasureSearch.p1.getMaxGold() == treasureSearch.p1.getGold() && treasureSearch.p1.getGold() != 0) {
            int roll = (int)(Math.random()*4);
            if(roll == 0) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy1.1.png"); }
            else if(roll == 1) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy2.1.png"); }
            else if(roll == 2) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy3.1.png"); }
            else { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), "imagesOrPiskels/enemy4.1.png"); }
            for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                while(Enemy.allEnemies.get(i).getRow()%2 != 0) { Enemy.allEnemies.get(i).setRow((int)(Math.random()*15)); }
                while(Enemy.allEnemies.get(i).getCol()%2 != 1) { Enemy.allEnemies.get(i).setCol((int)(Math.random()*15)); }
            }
            treasureSearch.grid[Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getRow()][Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getRow()] = Enemy.allEnemies.get(Enemy.allEnemies.size()-1);
            treasureSearch.p1.resetCounter();
        }
    }
    public void draw(Graphics pen) {
        for(int r = 0; r < treasureSearch.grid.length; r++) {
            for(int c = 0; c < treasureSearch.grid[r].length; c++) {
                for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                    if(treasureSearch.grid[r][c] == Enemy.allEnemies.get(i)) {
                        //pen.drawImage(Enemy.allEnemies.get(i).getSprite(), 50 * (1+c), 50 * (1+r), null);
                        pen.setColor(Color.RED);
                    }
                }
                if(treasureSearch.grid[r][c] == null) {
                    pen.setColor(Color.WHITE);
                } else if(treasureSearch.grid[r][c] == treasureSearch.p1) {
                    pen.setColor(Color.BLUE);
                } else if(treasureSearch.grid[r][c] == treasureSearch.t) {
                    pen.setColor(Color.YELLOW);
                }
                pen.fillRect(50 * (1+c), 50 * (1+r), 50, 50);
                pen.setColor(Color.BLACK);
                pen.drawRect(50 * (1+c), 50 * (1+r), 50, 50);
            }
        }
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        pen.drawString("Gold: " + String.valueOf(treasureSearch.p1.getGold()), 1000, 150);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.drawString(shop.getTitle(), shop.getX(), shop.getY());

    }
    public void keyPressed (KeyEvent ke) {}
}
