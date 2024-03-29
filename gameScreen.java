import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class gameScreen extends Screen {
    int itemcount = 0;
    public gameScreen(String title, Color color) {
        super(title, color);
    }
    public void update() {
        try {
            treasureSearch.title.resetAudioStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int t = 0; t < Treasure.treasures.size(); t++) {
            if(treasureSearch.p1.getRow() == Treasure.treasures.get(t).getRow() && treasureSearch.p1.getCol() == Treasure.treasures.get(t).getCol()) {
                treasureSearch.p1.increaseTreasure();
                treasureSearch.p1.increaseGold(Treasure.treasures.get(t).getWorth());
                Treasure.treasures.get(t).setRow((int)(Math.random()*15));
                Treasure.treasures.get(t).setCol((int)(Math.random()*15));
                Treasure.treasures.get(t).setWorth((int)((Math.random()*5)+1));
                treasureSearch.grid[Treasure.treasures.get(t).getRow()][Treasure.treasures.get(t).getCol()] = Treasure.treasures.get(t);
            }
        }
        for(int x = 0; x < 3; x++) {
            if(treasureSearch.t1 != null) {
                if(x == 0 && treasureSearch.p1.getRow() == treasureSearch.t1.getRow() && treasureSearch.p1.getCol() == treasureSearch.t1.getCol()) {
                    treasureSearch.p1.increaseTreasure();
                    treasureSearch.p1.increaseGold(treasureSearch.t1.getWorth());
                    treasureSearch.t1 = null;
                } 
            } if(treasureSearch.t2 != null) {
                if(x == 1 && treasureSearch.p1.getRow() == treasureSearch.t2.getRow() && treasureSearch.p1.getCol() == treasureSearch.t2.getCol()) {
                    treasureSearch.p1.increaseTreasure();
                    treasureSearch.p1.increaseGold(treasureSearch.t2.getWorth());
                    treasureSearch.t2 = null;
                }
            } if(treasureSearch.t3 != null) {
                if(treasureSearch.p1.getRow() == treasureSearch.t3.getRow() && treasureSearch.p1.getCol() == treasureSearch.t3.getCol()) {
                    treasureSearch.p1.increaseTreasure();
                    treasureSearch.p1.increaseGold(treasureSearch.t3.getWorth());
                    treasureSearch.t3 = null;
                }
            }
        }
        for(int e = 0; e < Enemy.allEnemies.size(); e++) {
            for(int t = 0; t < Treasure.treasures.size(); t++) {
                if(Enemy.allEnemies.get(e).getRow() == Treasure.treasures.get(t).getRow() && Enemy.allEnemies.get(e).getCol() == Treasure.treasures.get(t).getCol()) {
                    Treasure.treasures.get(t).setRow((int)(Math.random()*15));
                    Treasure.treasures.get(t).setCol((int)(Math.random()*15));
                    Treasure.treasures.get(t).setWorth((int)((Math.random()*5)+1));
                    treasureSearch.grid[Treasure.treasures.get(t).getRow()][Treasure.treasures.get(t).getCol()] = Treasure.treasures.get(t);
                }
            }
        }
        for(int r = 0; r < treasureSearch.grid.length; r++) {
            for(int c = 0; c < treasureSearch.grid[r].length; c++) {
                if(treasureSearch.p1.getRow() == r && treasureSearch.p1.getCol() == c) {
                    treasureSearch.grid[r][c] = treasureSearch.p1;
                } else {
                    for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
                        if(treasureSearch.shop.getProducts().get(i).getRow() == r && treasureSearch.shop.getProducts().get(i).getCol() == c && treasureSearch.p1.getMaxGold() >= treasureSearch.shop.getProducts().get(i).getPrice()) {
                            treasureSearch.grid[r][c] = treasureSearch.shop.getProducts().get(i);
                        }
                    }
                }
                if(treasureSearch.t1 != null) {
                    if(treasureSearch.t1.getRow() == r && treasureSearch.t1.getCol() == c) {
                        treasureSearch.grid[r][c] = treasureSearch.t1;
                    }
                }
                if(treasureSearch.t2 != null) {
                    if(treasureSearch.t2.getRow() == r && treasureSearch.t2.getCol() == c) {
                        treasureSearch.grid[r][c] = treasureSearch.t2;
                    }
                }
                if(treasureSearch.t3 != null) {
                    if(treasureSearch.t3.getRow() == r && treasureSearch.t3.getCol() == c) {
                        treasureSearch.grid[r][c] = treasureSearch.t3;
                    }
                }
                for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                    if(Enemy.allEnemies.get(i).getRow() == r && Enemy.allEnemies.get(i).getCol() == c) {
                        treasureSearch.grid[r][c] = Enemy.allEnemies.get(i);
                    } 
                }
                for(int t = 0; t < Treasure.treasures.size(); t++) {
                    if(Treasure.treasures.get(t).getRow() == r && Treasure.treasures.get(t).getCol() == c) {
                        treasureSearch.grid[r][c] = Treasure.treasures.get(t);
                    }
                }
            }
        }
        for(int e = 0; e < Enemy.allEnemies.size(); e++) {
            if(treasureSearch.p1.getRow() == Enemy.allEnemies.get(e).getRow() && treasureSearch.p1.getCol() == Enemy.allEnemies.get(e).getCol()) {
                treasureSearch.p1.setRow(7);
                treasureSearch.p1.setCol(7);
                Enemy.allEnemies.clear();
                for(int x = 0; x < 3; x++) {
                    if(x == 0) { 
                        treasureSearch.t1 = null;
                    } else if(x == 1) {
                        treasureSearch.t2 = null;
                    } else {
                        treasureSearch.t3 = null;
                    }
                }
                new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.voidweaver);
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                    while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                }
                treasureSearch.p1.setGold(0);
                treasureSearch.p1.resetCounter();
                treasureSearch.score = treasureSearch.p1.getMaxGold();
                try {
                    File bestScore = new File("text/highscore.txt");
                    Scanner scoreCheck = new Scanner(bestScore);
                    treasureSearch.highScore = scoreCheck.nextLine();
                    scoreCheck.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error");
                    ex.printStackTrace();
                }
                if(Integer.parseInt(treasureSearch.highScore) < treasureSearch.score) {
                    treasureSearch.highScore = treasureSearch.score + "";
                }
                treasureSearch.p1.resetMaxGold();
                itemcount = 0;
                treasureSearch.p1.setTreasure(0);
                treasureSearch.activeScreen = treasureSearch.end;
                treasureSearch.endSpeech.play();
                treasureSearch.endSong.play();
                try {
                    treasureSearch.activeSong.resetAudioStream();
                    treasureSearch.addItem.resetAudioStream();
                } catch (Exception f) {
                    f.printStackTrace();
                }
                break;
            }
        }
        for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
            if(treasureSearch.shop.getProducts().get(i).getCol() == treasureSearch.p1.getCol() && treasureSearch.shop.getProducts().get(i).getRow() == treasureSearch.p1.getRow() && treasureSearch.p1.getGold() >= treasureSearch.shop.getProducts().get(i).getPrice()) {
                treasureSearch.grid[treasureSearch.shop.getProducts().get(i).getRow()][treasureSearch.shop.getProducts().get(i).getCol()] = null;
                if(treasureSearch.shop.getProducts().get(i).getSprite() == treasureSearch.treasureButton) {
                    int worth = (int)(Math.random()*5)+1;
                    for(int x = 0; x < 3; x++) {
                        if(x == 0) { 
                            treasureSearch.t1 = new Treasure((int)(Math.random()*15), (int)(Math.random()*15), worth*3, treasureSearch.treasureImg);
                            treasureSearch.grid[treasureSearch.t1.getRow()][treasureSearch.t1.getCol()] = treasureSearch.t1;
                        } else if(x == 1) {
                            treasureSearch.t2 = new Treasure((int)(Math.random()*15), (int)(Math.random()*15), worth*3, treasureSearch.treasureImg);
                            treasureSearch.grid[treasureSearch.t2.getRow()][treasureSearch.t2.getCol()] = treasureSearch.t2;
                        } else {
                            treasureSearch.t3 = new Treasure((int)(Math.random()*15), (int)(Math.random()*15), worth*3, treasureSearch.treasureImg);
                            treasureSearch.grid[treasureSearch.t3.getRow()][treasureSearch.t3.getCol()] = treasureSearch.t3;
                        }
                    }
                    treasureSearch.p1.setGold(treasureSearch.p1.getGold()-10);
                } else if(treasureSearch.shop.getProducts().get(i).getSprite() == treasureSearch.treasureInc) {
                    treasureSearch.treasure += (int)(Math.random()*3)+1;
                    treasureSearch.p1.setGold(treasureSearch.p1.getGold()-25);
                }
                treasureSearch.shop.getProducts().get(i).setCol((int)(Math.random()*15));
                treasureSearch.shop.getProducts().get(i).setRow((int)(Math.random()*15));
            }
        }
        if(treasureSearch.p1.getCounter() == 10 && treasureSearch.p1.getGold() != 0) {
            int roll = (int)(Math.random()*50) + 1;
            if(roll<=5) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.g1); }
            else if(roll%5==0) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e11); }
            else if(roll%5==1) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e21); }
            else if(roll%5==2) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e31); }
            else if(roll%5==3) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e41); }
            else { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e51); }
            for(int i = Enemy.allEnemies.size()-1; i < Enemy.allEnemies.size(); i++) {
                while(Enemy.allEnemies.get(i).getRow()%2 != 0) { Enemy.allEnemies.get(i).setRow((int)(Math.random()*15)); }
                while(Enemy.allEnemies.get(i).getCol()%2 != 1) { Enemy.allEnemies.get(i).setCol((int)(Math.random()*15)); }
            }
            treasureSearch.grid[Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getRow()][Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getCol()] = Enemy.allEnemies.get(Enemy.allEnemies.size()-1);
            treasureSearch.p1.resetCounter();
            try {
                treasureSearch.spawn1.resetAudioStream();
                treasureSearch.spawn2.resetAudioStream();
                treasureSearch.spawn3.resetAudioStream();
                treasureSearch.spawn4.resetAudioStream();
                treasureSearch.spawn5.resetAudioStream();
                treasureSearch.spawn6.resetAudioStream();
                treasureSearch.spawn7.resetAudioStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(treasureSearch.activeSong == treasureSearch.main1) { treasureSearch.spawn1.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main2) { treasureSearch.spawn2.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main3) { treasureSearch.spawn3.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main4) { treasureSearch.spawn4.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main5) { treasureSearch.spawn5.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main6) { treasureSearch.spawn6.play(); }
            else if(treasureSearch.activeSong == treasureSearch.main7) { treasureSearch.spawn7.play(); }
            if(treasureSearch.p1.getMaxGold() >= treasureSearch.shop.getProducts().get(itemcount).getPrice()) {
                treasureSearch.grid[treasureSearch.shop.getProducts().get(itemcount).getRow()][treasureSearch.shop.getProducts().get(itemcount).getCol()] = treasureSearch.shop.getProducts().get(itemcount);
                itemcount++;
                try {
                    treasureSearch.addItem.resetAudioStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                treasureSearch.addItem.play();
            }
        }
    }
    public void draw(Graphics pen) {
        for(int r = 0; r < treasureSearch.grid.length; r++) {
            for(int c = 0; c < treasureSearch.grid[r].length; c++) {
                pen.setColor(Color.BLACK);
                pen.fillRect(50 * (1+c), 50 * (1+r), 50, 50);
                for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                    if(treasureSearch.grid[r][c] == Enemy.allEnemies.get(i)) {
                        pen.drawImage(Enemy.allEnemies.get(i).getSprite(),50*(1+c),50*(1+r),50,50,null);
                    }
                }
                if(treasureSearch.grid[r][c] == treasureSearch.p1) {
                    pen.drawImage(treasureSearch.p1.getSprite(),50*(1+c),50*(1+r),50,50,null);
                } else if(treasureSearch.grid[r][c] == treasureSearch.t1 && treasureSearch.t1 != null) { 
                    pen.drawImage(treasureSearch.t1.getSprite(),50*(1+c),50*(1+r),50,50,null);
                } else if(treasureSearch.grid[r][c] == treasureSearch.t2 && treasureSearch.t2 != null) { 
                    pen.drawImage(treasureSearch.t2.getSprite(),50*(1+c),50*(1+r),50,50,null);
                } else if(treasureSearch.grid[r][c] == treasureSearch.t3 && treasureSearch.t3 != null) { 
                    pen.drawImage(treasureSearch.t3.getSprite(),50*(1+c),50*(1+r),50,50,null);
                }
                for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
                    if(treasureSearch.grid[r][c] == treasureSearch.shop.getProducts().get(i)) {
                        pen.drawImage(treasureSearch.shop.getProducts().get(i).getSprite(),50*(1+c),50*(1+r),50,50,null);
                    }
                }
                for(int t = 0; t < Treasure.treasures.size(); t++) {
                    if(treasureSearch.grid[r][c] == Treasure.treasures.get(t)) {
                        pen.drawImage(Treasure.treasures.get(t).getSprite(),50*(1+c),50*(1+r),50,50,null);
                    }
                }
            }
        }
        for(int r = 0; r < treasureSearch.grid.length; r++) {
            for(int c = 0; c < treasureSearch.grid[r].length; c++) {
                for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                    if(treasureSearch.grid[r][c] == Enemy.allEnemies.get(i)) {
                        pen.drawImage(Enemy.allEnemies.get(i).getSprite(),50*(1+c),50*(1+r),50,50,null);
                        if(Enemy.allEnemies.get(i).isAngry() && Enemy.allEnemies.get(i).getSprite() == treasureSearch.g5) {
                            treasureSearch.hollowPurple(pen, Enemy.allEnemies.get(i));
                        }
                        if(Enemy.allEnemies.get(i).isAngry() && Enemy.allEnemies.get(i).getSprite() == treasureSearch.g6) {
                            treasureSearch.hollowBlack(pen, Enemy.allEnemies.get(i));
                        }
                    }
                }
            }
        }
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        pen.drawString("Gold: " + treasureSearch.p1.getGold(), 1000, 150);
        pen.drawString("Total Score: " + treasureSearch.p1.getMaxGold(), 1000, 175);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.drawString("Item Shop", treasureSearch.shop.getX(), treasureSearch.shop.getY());
        pen.drawImage(treasureSearch.qIdle, treasureSearch.shop.getX() + 400, treasureSearch.shop.getY(), null);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
            if(treasureSearch.p1.getMaxGold() >= treasureSearch.shop.getProducts().get(i).getPrice()) {
                pen.drawImage(treasureSearch.shop.getProducts().get(i).getSprite(), treasureSearch.shop.getX(), treasureSearch.shop.getY() + (70 * (i+1)), null);
                pen.drawString(treasureSearch.shop.getProducts().get(i).getName() + ": " + treasureSearch.shop.getProducts().get(i).getPrice() + " gold", treasureSearch.shop.getX() + 50, (treasureSearch.shop.getY() + (70 * (i+1)) + 25));
            }
        }
    }
    public void keyPressed (KeyEvent ke) {}
}
