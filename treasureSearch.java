import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class treasureSearch extends Game  {
    public static final String TITLE = "Treasure Search";
    public static final int SCREEN_WIDTH = 1940;
    public static final int SCREEN_HEIGHT = 1079;
    // declare variables here
    static Object[][] grid;
    static Player p1;
    static Treasure t;
    static Screen activeScreen;
    static gameScreen game;
    static titleScreen start;
    static endScreen end;
    static BufferedImage playerImg;
    static BufferedImage treasureImg;
    static BufferedImage e11, e12, e13, e14, e15;
    static BufferedImage e21, e22, e23, e24, e25;
    static BufferedImage e31, e32, e33, e34, e35;
    static BufferedImage e41, e42, e43, e44, e45, e46;
    static BufferedImage e51, e52, e53, e54, e55;
    static BufferedImage emptiness;
    static BufferedImage treasureButton;
    static SimpleAudioPlayer fred, hen, electrode, para, crim, whis;
    static ItemShop shop;
    public treasureSearch() {
        // initialize variables here
        try {
            playerImg=ImageIO.read(getClass().getResourceAsStream("/images/player.png"));
            treasureImg=ImageIO.read(getClass().getResourceAsStream("/images/treasure.png"));
            e11=ImageIO.read(getClass().getResourceAsStream("/images/enemy1.1.png"));
            e12=ImageIO.read(getClass().getResourceAsStream("/images/enemy1.2.png"));
            e13=ImageIO.read(getClass().getResourceAsStream("/images/enemy1.3.png"));
            e14=ImageIO.read(getClass().getResourceAsStream("/images/enemy1.4.png"));
            e15=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            e21=ImageIO.read(getClass().getResourceAsStream("/images/enemy2.1.png"));
            e22=ImageIO.read(getClass().getResourceAsStream("/images/enemy2.2.png"));
            e23=ImageIO.read(getClass().getResourceAsStream("/images/enemy2.3.png"));
            e24=ImageIO.read(getClass().getResourceAsStream("/images/enemy2.4.png"));
            e25=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            e31=ImageIO.read(getClass().getResourceAsStream("/images/enemy3.1.png"));
            e32=ImageIO.read(getClass().getResourceAsStream("/images/enemy3.2.png"));
            e33=ImageIO.read(getClass().getResourceAsStream("/images/enemy3.3.png"));
            e34=ImageIO.read(getClass().getResourceAsStream("/images/enemy3.4.png"));
            e35=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            e41=ImageIO.read(getClass().getResourceAsStream("/images/enemy4.1.png"));
            e42=ImageIO.read(getClass().getResourceAsStream("/images/enemy4.2.png"));
            e43=ImageIO.read(getClass().getResourceAsStream("/images/enemy4.3.png"));
            e44=ImageIO.read(getClass().getResourceAsStream("/images/enemy4.4.png"));
            e45=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            e46=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            e51=ImageIO.read(getClass().getResourceAsStream("/images/enemy5.1.png"));
            e52=ImageIO.read(getClass().getResourceAsStream("/images/enemy5.2.png"));
            e53=ImageIO.read(getClass().getResourceAsStream("/images/enemy5.3.png"));
            e54=ImageIO.read(getClass().getResourceAsStream("/images/enemy5.4.png"));
            e55=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            emptiness=ImageIO.read(getClass().getResourceAsStream("/images/void.png"));
            treasureButton=ImageIO.read(getClass().getResourceAsStream("/images/specialTreasure.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fred = new SimpleAudioPlayer("audio/MusicBox.wav");
            hen = new SimpleAudioPlayer("audio/Speech.wav");
            electrode = new SimpleAudioPlayer("audio/Boom.wav");
            para = new SimpleAudioPlayer("audio/Para.wav");
            crim = new SimpleAudioPlayer("audio/Crim.wav");
            whis = new SimpleAudioPlayer("audio/Whis.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        shop = new ItemShop("Shop", new ArrayList<Product>(), 1000, 100);
        shop.addProduct(new Product("Spawn Special Treasure", 10, treasureButton));
        grid = new Object[15][15];
        p1 = new Player(7, 7, playerImg);
        new Enemy((int)(Math.random()*15), (int)(Math.random()*15), emptiness);
        t = new Treasure((int)(Math.random()*15), (int)(Math.random()*15), (int)((Math.random()*5)+1), treasureImg);
        for(int i = 0; i < Enemy.allEnemies.size(); i++) {
            while(Enemy.allEnemies.get(i).getRow()%2 != 0) { Enemy.allEnemies.get(i).setRow((int)(Math.random()*15)); }
            while(Enemy.allEnemies.get(i).getCol()%2 != 1) { Enemy.allEnemies.get(i).setCol((int)(Math.random()*15)); }
        }
        grid[p1.getRow()][p1.getCol()] = p1;
        grid[t.getRow()][t.getCol()] = t;
        for(int i = 0; i < Enemy.allEnemies.size(); i++) {
            grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()] = Enemy.allEnemies.get(i);
        }
        game = new gameScreen(TITLE, Color.CYAN);
        start = new titleScreen(TITLE, Color.BLUE);
        end = new endScreen("YOU DIED", Color.RED);
        activeScreen = start;
    }
    
    public void update() {
        // updating logic
        activeScreen.update();
    }
    
    public void draw(Graphics pen) {
        activeScreen.draw(pen);
    }
        
    @Override
    public void keyTyped(KeyEvent ke) {
        int xDelta = 0;
        int yDelta = 0;
        if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 'a' || ke.getKeyChar() == 's' || ke.getKeyChar() == 'd') {
            grid[p1.getRow()][p1.getCol()] = null;
            if(ke.getKeyChar() == 'w') {
                p1.decreaseRow();
                if(p1.getRow() < 0) {
                    p1.setRow(14);
                }
            } else if(ke.getKeyChar() == 's') {
                p1.increaseRow();
                if(p1.getRow() > 14) {
                    p1.setRow(0);
                }
            } else if(ke.getKeyChar() == 'a') {
                p1.decreaseCol();
                if(p1.getCol() < 0) {
                    p1.setCol(14);
                }
            } else if(ke.getKeyChar() == 'd') {
                p1.increaseCol();
                if(p1.getCol() > 14) {
                    p1.setCol(0);
                }
            }
            for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()] = null;
                if(i == 0) {
                    xDelta = Enemy.allEnemies.get(i).getCol() - p1.getCol();
                    yDelta = Enemy.allEnemies.get(i).getRow() - p1.getRow();
                    if(Math.abs(xDelta) > 4 || Math.abs(yDelta) > 4 ) {
                        int roll = (int)(Math.random()*4);
                        if(roll == 0){
                            Enemy.allEnemies.get(i).decreaseCol();
                            if(Enemy.allEnemies.get(i).getCol() < 0) {
                                Enemy.allEnemies.get(i).setCol(14);
                            }
                        } else if(roll == 1) {
                            Enemy.allEnemies.get(i).decreaseRow();
                            if(Enemy.allEnemies.get(i).getRow() < 0) {
                                Enemy.allEnemies.get(i).setRow(14);
                            }
                        } else if(roll == 2) {
                            Enemy.allEnemies.get(i).increaseCol();
                            if(Enemy.allEnemies.get(i).getCol() > 14) {
                                Enemy.allEnemies.get(i).setCol(0);
                            }
                        } else {
                            Enemy.allEnemies.get(i).increaseRow();
                            if(Enemy.allEnemies.get(i).getRow() > 14) {
                                Enemy.allEnemies.get(i).setRow(0);
                            }
                        }
                    } else {
                        if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                            Enemy.allEnemies.get(i).increaseCol();
                        } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                            Enemy.allEnemies.get(i).decreaseCol();
                        } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                            Enemy.allEnemies.get(i).increaseRow();
                        } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                            Enemy.allEnemies.get(i).decreaseRow();
                        } else if(xDelta == 0) {
                            if(yDelta < 0) {
                                Enemy.allEnemies.get(i).increaseRow();
                            } else if(yDelta > 0) {
                                Enemy.allEnemies.get(i).decreaseRow();
                            }
                        } else if(yDelta == 0) {
                            if(xDelta < 0) {
                                Enemy.allEnemies.get(i).increaseCol();
                            } else if(xDelta > 0) {
                                Enemy.allEnemies.get(i).decreaseCol();
                            }
                        }
                    }
                    /* if(yDelta > xDelta) {
                        if(Math.abs((Enemy.allEnemies.get(i).getRow()+1) - p1.getRow()) > Math.abs((Enemy.allEnemies.get(i).getRow()-1) - p1.getRow())) {
                            Enemy.allEnemies.get(i).decreaseRow();
                            if(Enemy.allEnemies.get(i).getRow() < 0) {
                                Enemy.allEnemies.get(i).setRow(14);
                            }
                        } else if(Math.abs((Enemy.allEnemies.get(i).getRow()+1) - p1.getRow()) < Math.abs((Enemy.allEnemies.get(i).getRow()-1) - p1.getRow())) {
                            Enemy.allEnemies.get(i).increaseRow();
                            if(Enemy.allEnemies.get(i).getRow() > 14) {
                                Enemy.allEnemies.get(i).setRow(0);
                            }
                        }
                    } else if(xDelta > yDelta) {
                        if(Math.abs((Enemy.allEnemies.get(i).getCol()+1) - p1.getCol()) > Math.abs((Enemy.allEnemies.get(i).getCol()-1) - p1.getCol())) {
                            Enemy.allEnemies.get(i).decreaseCol();
                            if(Enemy.allEnemies.get(i).getCol() < 0) {
                                Enemy.allEnemies.get(i).setCol(14);
                            }
                        } else if(Math.abs((Enemy.allEnemies.get(i).getCol()+1) - p1.getCol()) < Math.abs((Enemy.allEnemies.get(i).getCol()-1) - p1.getCol())) {
                            Enemy.allEnemies.get(i).increaseCol();
                            if(Enemy.allEnemies.get(i).getCol() > 14) {
                                Enemy.allEnemies.get(i).setCol(0);
                            }
                        }
                    } else {
                        int roll = (int)(Math.random()*2);
                        if(roll == 0) {
                            if(Math.abs((Enemy.allEnemies.get(i).getRow()+1) - p1.getRow()) > Math.abs((Enemy.allEnemies.get(i).getRow()-1) - p1.getRow())) {
                                Enemy.allEnemies.get(i).decreaseRow();
                                if(Enemy.allEnemies.get(i).getRow() < 0) {
                                    Enemy.allEnemies.get(i).setRow(14);
                                }
                            } else if(Math.abs((Enemy.allEnemies.get(i).getRow()+1) - p1.getRow()) < Math.abs((Enemy.allEnemies.get(i).getRow()-1) - p1.getRow())) {
                                Enemy.allEnemies.get(i).increaseRow();
                                if(Enemy.allEnemies.get(i).getRow() > 14) {
                                    Enemy.allEnemies.get(i).setRow(0);
                                }
                            }
                        } else {
                            if(Math.abs((Enemy.allEnemies.get(i).getCol()+1) - p1.getCol()) > Math.abs((Enemy.allEnemies.get(i).getCol()-1) - p1.getCol())) {
                                Enemy.allEnemies.get(i).decreaseCol();
                                if(Enemy.allEnemies.get(i).getCol() < 0) {
                                    Enemy.allEnemies.get(i).setCol(14);
                                }
                            } else if(Math.abs((Enemy.allEnemies.get(i).getCol()+1) - p1.getCol()) < Math.abs((Enemy.allEnemies.get(i).getCol()-1) - p1.getCol())) {
                                Enemy.allEnemies.get(i).increaseCol();
                                if(Enemy.allEnemies.get(i).getCol() > 14) {
                                    Enemy.allEnemies.get(i).setCol(0);
                                }
                            }
                        }
                    } */
                } else {
                    if(Enemy.allEnemies.get(i).getSprite() == e11 || Enemy.allEnemies.get(i).getSprite() == e12 || Enemy.allEnemies.get(i).getSprite() == e13 || Enemy.allEnemies.get(i).getSprite() == e14 || Enemy.allEnemies.get(i).getSprite() == e15) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            Enemy.allEnemies.get(i).setSprite(e11);
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 10) {
                                Enemy.allEnemies.get(i).changeAngry();
                                try {
                                    whis.resetAudioStream();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                                whis.play();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry()) {
                            xDelta = Enemy.allEnemies.get(i).getCol() - p1.getCol();
                            yDelta = Enemy.allEnemies.get(i).getRow() - p1.getRow();
                            if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).increaseCol();
                            } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).decreaseCol();
                            } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).increaseRow();
                            } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).decreaseRow();
                            } else if(xDelta == 0) {
                                if(yDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseCol();
                                } else if(yDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseCol();
                                }
                            } else if(yDelta == 0) {
                                if(xDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseRow();
                                } else if(xDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseRow();
                                }
                            }
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 3 && Enemy.allEnemies.get(i).getSprite() == e15) {
                                Enemy.allEnemies.get(i).changeAngry();
                                try {
                                    whis.resetAudioStream();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).setTime(0);
                                Enemy.allEnemies.get(i).changeAngry();
                                try {
                                    whis.resetAudioStream();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                            }
                        }
                    } else if(Enemy.allEnemies.get(i).getSprite() == e21 || Enemy.allEnemies.get(i).getSprite() == e22 || Enemy.allEnemies.get(i).getSprite() == e23 || Enemy.allEnemies.get(i).getSprite() == e24 || Enemy.allEnemies.get(i).getSprite() == e25) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            try {
                                whis.resetAudioStream();
                            } catch (Exception f) {
                                f.printStackTrace();
                            }
                            Enemy.allEnemies.get(i).setSprite(e21);
                        }
                        int roll = (int)(Math.random()*4);
                        if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                        else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                        else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                        else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                        Enemy.allEnemies.get(i).increaseTime();
                        if(Enemy.allEnemies.get(i).getTime() == 5 && Enemy.allEnemies.get(i).getSprite() == e25) {
                            Enemy.allEnemies.get(i).changeAngry();
                            Enemy.allEnemies.get(i).setTime(0);
                        }
                    } else if(Enemy.allEnemies.get(i).getSprite() == e31 || Enemy.allEnemies.get(i).getSprite() == e32 || Enemy.allEnemies.get(i).getSprite() == e33 || Enemy.allEnemies.get(i).getSprite() == e34 || Enemy.allEnemies.get(i).getSprite() == e35) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            Enemy.allEnemies.get(i).setSprite(e31);
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 10) {
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                                try {
                                    whis.resetAudioStream();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                                whis.play();
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry()) {
                            xDelta = Enemy.allEnemies.get(i).getCol() - t.getCol();
                            yDelta = Enemy.allEnemies.get(i).getRow() - t.getRow();
                            if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).increaseCol();
                            } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).decreaseCol();
                            } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).increaseRow();
                            } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).decreaseRow();
                            } else if(xDelta == 0) {
                                if(yDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseCol();
                                } else if(yDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseCol();
                                }
                            } else if(yDelta == 0) {
                                if(xDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseRow();
                                } else if(xDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseRow();
                                }
                            } else if(Math.abs(xDelta) == Math.abs(yDelta)) {
                                int roll = (int)(Math.random()*4);
                                if(roll == 0 && xDelta < 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                                else if(roll == 1 && xDelta > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                                else if(roll == 2 && yDelta < 0) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                                else if(roll == 3 && yDelta > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            }
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 5 && Enemy.allEnemies.get(i).getSprite() == e35) {
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).setTime(0);
                                Enemy.allEnemies.get(i).changeAngry();
                            }
                        }
                    } else if(Enemy.allEnemies.get(i).getSprite() == e41 || Enemy.allEnemies.get(i).getSprite() == e42 || Enemy.allEnemies.get(i).getSprite() == e43 || Enemy.allEnemies.get(i).getSprite() == e44 || Enemy.allEnemies.get(i).getSprite() == e45) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 10) {
                                Enemy.allEnemies.get(i).changeAngry();
                                try {
                                    whis.resetAudioStream();
                                } catch (Exception f) {
                                    f.printStackTrace();
                                }
                                whis.play();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry()) {
                            if(Enemy.allEnemies.get(i).getSprite() == e46) {
                                Enemy.allEnemies.get(i).setSprite(e46);
                            } else {
                                Enemy.allEnemies.get(i).setSprite(e45);
                            }
                            xDelta = Enemy.allEnemies.get(i).getCol() - p1.getCol();
                            yDelta = Enemy.allEnemies.get(i).getRow() - p1.getRow();
                            if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).increaseCol();
                            } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).decreaseCol();
                            } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).increaseRow();
                            } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).decreaseRow();
                            } else if(xDelta == 0) {
                                if(yDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseCol();
                                } else if(yDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseCol();
                                }
                            } else if(yDelta == 0) {
                                if(xDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseRow();
                                } else if(xDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseRow();
                                }
                            }
                            if(Enemy.allEnemies.get(i).getTime() % 3 == 0) {
                                Enemy.allEnemies.get(i).setSprite(e41);
                            }
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 5 && Enemy.allEnemies.get(i).getSprite() == e46) {
                                Enemy.allEnemies.get(i).setSprite(e41);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).setSprite(e41);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        }
                    } else if(Enemy.allEnemies.get(i).getSprite() == e51 || Enemy.allEnemies.get(i).getSprite() == e52 || Enemy.allEnemies.get(i).getSprite() == e53 || Enemy.allEnemies.get(i).getSprite() == e54 || Enemy.allEnemies.get(i).getSprite() == e55) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 15) {
                                for(int r = 1; r < Enemy.allEnemies.size(); r++) {
                                    if(!Enemy.allEnemies.get(r).isAngry()) {
                                        Enemy.allEnemies.get(r).changeAngry();
                                        try {
                                            whis.resetAudioStream();
                                        } catch (Exception f) {
                                            f.printStackTrace();
                                        }
                                        whis.play();
                                        Enemy.allEnemies.get(r).setTime(0);
                                    }
                                    if(Enemy.allEnemies.get(r).getSprite() == e11 || Enemy.allEnemies.get(r).getSprite() == e12 || Enemy.allEnemies.get(r).getSprite() == e13 || Enemy.allEnemies.get(r).getSprite() == e14) { Enemy.allEnemies.get(r).setSprite(e15); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e21 || Enemy.allEnemies.get(r).getSprite() == e22 || Enemy.allEnemies.get(r).getSprite() == e23 || Enemy.allEnemies.get(r).getSprite() == e24) { Enemy.allEnemies.get(r).setSprite(e25); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e31 || Enemy.allEnemies.get(r).getSprite() == e32 || Enemy.allEnemies.get(r).getSprite() == e33 || Enemy.allEnemies.get(r).getSprite() == e34) { Enemy.allEnemies.get(r).setSprite(e35); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e41 || Enemy.allEnemies.get(r).getSprite() == e42 || Enemy.allEnemies.get(r).getSprite() == e43 || Enemy.allEnemies.get(r).getSprite() == e44) { Enemy.allEnemies.get(r).setSprite(e46); }
                                    else { Enemy.allEnemies.get(r).setSprite(e55); }
                                }
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry()) {
                            Enemy.allEnemies.get(i).setSprite(e55);
                            xDelta = Enemy.allEnemies.get(i).getCol() - p1.getCol();
                            yDelta = Enemy.allEnemies.get(i).getRow() - p1.getRow();
                            if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).increaseCol();
                            } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta)) {
                                Enemy.allEnemies.get(i).decreaseCol();
                            } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).increaseRow();
                            } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta)) {
                                Enemy.allEnemies.get(i).decreaseRow();
                            } else if(xDelta == 0) {
                                if(yDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseCol();
                                } else if(yDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseCol();
                                }
                            } else if(yDelta == 0) {
                                if(xDelta < 0) {
                                    Enemy.allEnemies.get(i).increaseRow();
                                } else if(xDelta > 0) {
                                    Enemy.allEnemies.get(i).decreaseRow();
                                }
                            }
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 5) {
                                Enemy.allEnemies.get(i).setSprite(e51);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) { activeScreen.keyPressed(ke); }

    @Override
    public void keyReleased(KeyEvent ke) {}

    @Override
    public void mouseClicked(MouseEvent ke) {}

    @Override
    public void mousePressed(MouseEvent me) {}
    
    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {}

    //Launches the Game
    public static void main(String[] args) { new treasureSearch().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }

}