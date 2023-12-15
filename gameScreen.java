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
        if(treasureSearch.p1.getRow() == treasureSearch.t.getRow() && treasureSearch.p1.getCol() == treasureSearch.t.getCol()) {
            treasureSearch.p1.increaseTreasure();
            treasureSearch.p1.increaseGold(treasureSearch.t.getWorth());
            treasureSearch.t.setRow((int)(Math.random()*15));
            treasureSearch.t.setCol((int)(Math.random()*15));
            treasureSearch.t.setWorth((int)((Math.random()*3)+1));
            treasureSearch.grid[treasureSearch.t.getRow()][treasureSearch.t.getCol()] = treasureSearch.t;
        }
        for(int e = 0; e < Enemy.allEnemies.size(); e++) {
            if(Enemy.allEnemies.get(e).getRow() == treasureSearch.t.getRow() && Enemy.allEnemies.get(e).getCol() == treasureSearch.t.getCol()) {
                treasureSearch.t.setRow((int)(Math.random()*15));
                treasureSearch.t.setCol((int)(Math.random()*15));
                treasureSearch.t.setWorth((int)((Math.random()*3)+1));
                treasureSearch.grid[treasureSearch.t.getRow()][treasureSearch.t.getCol()] = treasureSearch.t;
            }
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
                new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.emptiness);
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                    while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                }
                treasureSearch.p1.setGold(0);
                treasureSearch.p1.resetCounter();
                treasureSearch.score = treasureSearch.p1.getMaxGold();
                try {
                    File bestScore = new File("highscore.txt");
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
                treasureSearch.p1.setTreasure(0);
                treasureSearch.activeScreen = treasureSearch.end;
                treasureSearch.endSpeech.play();
                try {
                    treasureSearch.main1.resetAudioStream();
                    treasureSearch.main2.resetAudioStream();
                    treasureSearch.main3.resetAudioStream();
                    treasureSearch.main4.resetAudioStream();
                    treasureSearch.main5.resetAudioStream();
                    treasureSearch.main6.resetAudioStream();
                    treasureSearch.main7.resetAudioStream();
                } catch (Exception f) {
                    f.printStackTrace();
                }
                break;
            }
        }
        if(treasureSearch.p1.getCounter() == 10 && treasureSearch.p1.getMaxGold() == treasureSearch.p1.getGold() && treasureSearch.p1.getGold() != 0) {
            int roll = (int)(Math.random()*20) + 1;
            if(roll%5==0) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e11); }
            else if(roll%5==1) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e21); }
            else if(roll%5==2) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e31); }
            else if(roll%5==3) { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e41); }
            else { new Enemy((int)(Math.random()*15), (int)(Math.random()*15), treasureSearch.e51); }
            for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                while(Enemy.allEnemies.get(i).getRow()%2 != 0) { Enemy.allEnemies.get(i).setRow((int)(Math.random()*15)); }
                while(Enemy.allEnemies.get(i).getCol()%2 != 1) { Enemy.allEnemies.get(i).setCol((int)(Math.random()*15)); }
            }
            treasureSearch.grid[Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getRow()][Enemy.allEnemies.get(Enemy.allEnemies.size()-1).getCol()] = Enemy.allEnemies.get(Enemy.allEnemies.size()-1);
            treasureSearch.p1.resetCounter();
            try {
                treasureSearch.spawn.resetAudioStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            treasureSearch.spawn.play();
            if(itemcount < treasureSearch.shop.getProducts().size()) {
                treasureSearch.grid[treasureSearch.shop.getProducts().get(itemcount).getX()][treasureSearch.shop.getProducts().get(itemcount).getY()] = treasureSearch.shop.getProducts().get(itemcount);
                itemcount++;
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
                } else if(treasureSearch.grid[r][c] == treasureSearch.t) {
                    pen.drawImage(treasureSearch.t.getSprite(),50*(1+c),50*(1+r),50,50,null);
                }
                for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
                    if(treasureSearch.grid[r][c] == treasureSearch.shop.getProducts().get(i)) {
                        pen.drawImage(treasureSearch.shop.getProducts().get(i).getSprite(),50*(1+c),50*(1+r),50,50,null);
                    }
                }
            }
        }
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        pen.drawString("Gold: " + String.valueOf(treasureSearch.p1.getGold()), 1000, 150);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        pen.drawString("Item Shop", treasureSearch.shop.getX(), treasureSearch.shop.getY());
        pen.drawImage(treasureSearch.qIdle, treasureSearch.shop.getX() + 400, treasureSearch.shop.getY(), null);
        pen.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        for(int i = 0; i < treasureSearch.shop.getProducts().size(); i++) {
            if(treasureSearch.p1.getMaxGold() >= treasureSearch.shop.getProducts().get(i).getPrice()) {
                pen.drawImage(treasureSearch.shop.getProducts().get(i).getSprite(), treasureSearch.shop.getX(), treasureSearch.shop.getY() + (70 * (i+1)), null);
                pen.drawString(treasureSearch.shop.getProducts().get(i).getName() + ": " + treasureSearch.shop.getProducts().get(i).getPrice() + " gold", treasureSearch.shop.getX() + 50, treasureSearch.shop.getY() + (100 * (i+1)));
            }
        }
    }
    public void keyPressed (KeyEvent ke) {}
}
