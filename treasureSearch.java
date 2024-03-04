import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
public class treasureSearch extends Game  {
    public static final String TITLE = "Treasure Search";
    public static final int SCREEN_WIDTH = 1940;
    public static final int SCREEN_HEIGHT = 1079;
    // declare variables here
    static Object[][] grid;
    static Player p1;
    static Screen activeScreen;
    static gameScreen game;
    static titleScreen start;
    static endScreen end;
    static BufferedImage playerImg;
    static BufferedImage treasureImg, specialTreasureImg;
    static BufferedImage e11, e12, e13, e14, e15;
    static BufferedImage e21, e22, e23, e24, e25;
    static BufferedImage e31, e32, e33, e34, e35;
    static BufferedImage e41, e42, e43, e44, e45, e46;
    static BufferedImage e51, e52, e53, e54, e55;
    static BufferedImage g1, g2, g3, g4, g5, g6;
    static BufferedImage qIdle, qUp, qDown, qTalk, qBlink, qSus;
    static BufferedImage voidweaver;
    static BufferedImage treasureButton, treasureInc;
    static SimpleAudioPlayer endSpeech, spawn, title, main1, main2, main3, main4, main5, main6, main7;
    static ItemShop shop;
    static int score;
    static String highScore;
    static Treasure t1, t2, t3;
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
            g1=ImageIO.read(getClass().getResourceAsStream("/images/Gojo1.png"));
            g2=ImageIO.read(getClass().getResourceAsStream("/images/Gojo2.png"));
            g3=ImageIO.read(getClass().getResourceAsStream("/images/Gojo3.png"));
            g4=ImageIO.read(getClass().getResourceAsStream("/images/Gojo4.png"));
            g5=ImageIO.read(getClass().getResourceAsStream("/images/Gojo5.png"));
            g6=ImageIO.read(getClass().getResourceAsStream("/images/invispider.png"));
            qIdle=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireIdle.png"));
            qUp=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireLookUp.png"));
            qDown=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireLookDown.png"));
            qTalk=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireTalk.png"));
            qBlink=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireCloseEyes.png"));
            qSus=ImageIO.read(getClass().getResourceAsStream("/images/QuentinQuagmireBiteLip.png"));
            voidweaver=ImageIO.read(getClass().getResourceAsStream("/images/void.png"));
            treasureButton=ImageIO.read(getClass().getResourceAsStream("/images/specialTreasure.png"));
            specialTreasureImg=ImageIO.read(getClass().getResourceAsStream("/images/specialTreasureImg.png"));
            treasureInc=ImageIO.read(getClass().getResourceAsStream("/images/moreCoins.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            endSpeech = new SimpleAudioPlayer("audio/Speech.wav");
            spawn = new SimpleAudioPlayer("audio/One.wav");
            title = new SimpleAudioPlayer("audio/Title.wav");
            main1 = new SimpleAudioPlayer("audio/AroundtheWorld.wav");
            main2 = new SimpleAudioPlayer("audio/NegativeRhapsody.wav");
            main3 = new SimpleAudioPlayer("audio/Everlong.wav");
            main4 = new SimpleAudioPlayer("audio/IGKDBIGUA.wav");
            main5 = new SimpleAudioPlayer("audio/GO.wav");
            main6 = new SimpleAudioPlayer("audio/Drift.wav");
            main7 = new SimpleAudioPlayer("audio/Ads.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        shop = new ItemShop("Shop", new ArrayList<Product>(), 1000, 100);
        shop.addProduct(new Product("Spawn Special Treasure", 10, treasureButton, (int)(Math.random()*15), (int)(Math.random()*15)));
        shop.addProduct(new Product("Spawn Special Treasure", 10, treasureButton, (int)(Math.random()*15), (int)(Math.random()*15)));
        grid = new Object[15][15];
        p1 = new Player(7, 7, playerImg);
        highScore = "2";
        score = p1.getMaxGold();
        int treasure = 1;
        new Enemy((int)(Math.random()*15), (int)(Math.random()*15), voidweaver);
        for(int i = 0; i < treasure; i++) {new Treasure((int)(Math.random()*15), (int)(Math.random()*15), (int)((Math.random()*5)+1), treasureImg);}
        for(int i = 0; i < Enemy.allEnemies.size(); i++) {
            while(Enemy.allEnemies.get(i).getRow()%2 != 0) { Enemy.allEnemies.get(i).setRow((int)(Math.random()*15)); }
            while(Enemy.allEnemies.get(i).getCol()%2 != 1) { Enemy.allEnemies.get(i).setCol((int)(Math.random()*15)); }
        }
        grid[p1.getRow()][p1.getCol()] = p1;
        for(int t = 0; t < Treasure.treasures.size(); t++) {grid[Treasure.treasures.get(t).getRow()][Treasure.treasures.get(t).getCol()] = t;}
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
        if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 'a' || ke.getKeyChar() == 's' || ke.getKeyChar() == 'd' || ke.getKeyChar() == 'W' || ke.getKeyChar() == 'A' || ke.getKeyChar() == 'S' || ke.getKeyChar() == 'D') {
            grid[p1.getRow()][p1.getCol()] = null;
            if(ke.getKeyChar() == 'w' || ke.getKeyChar() == 'W') {
                p1.decreaseRow();
                if(p1.getRow() < 0) {
                    p1.setRow(14);
                }
            } else if(ke.getKeyChar() == 's' || ke.getKeyChar() == 'S') {
                p1.increaseRow();
                if(p1.getRow() > 14) {
                    p1.setRow(0);
                }
            } else if(ke.getKeyChar() == 'a' || ke.getKeyChar() == 'A') {
                p1.decreaseCol();
                if(p1.getCol() < 0) {
                    p1.setCol(14);
                }
            } else if(ke.getKeyChar() == 'd' || ke.getKeyChar() == 'D') {
                p1.increaseCol();
                if(p1.getCol() > 14) {
                    p1.setCol(0);
                }
            }
            for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()] = null;
                if(i == 0) {
                    if(Enemy.allEnemies.get(i).getSprite() == g1 || Enemy.allEnemies.get(i).getSprite() == g2 || Enemy.allEnemies.get(i).getSprite() == g3 || Enemy.allEnemies.get(i).getSprite() == g4 || Enemy.allEnemies.get(i).getSprite() == g5) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setSprite(g5);
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else {
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
                                Enemy.allEnemies.get(i).setSprite(g1);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        }
                    } else {
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
                    }
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
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).setTime(0);
                                Enemy.allEnemies.get(i).changeAngry();
                            }
                        }
                    } else if(Enemy.allEnemies.get(i).getSprite() == e21 || Enemy.allEnemies.get(i).getSprite() == e22 || Enemy.allEnemies.get(i).getSprite() == e23 || Enemy.allEnemies.get(i).getSprite() == e24 || Enemy.allEnemies.get(i).getSprite() == e25) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
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
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry()) {
                            for(int t = 0; t < Treasure.treasures.size()-1; t++) {
                                int xDelta1 = Enemy.allEnemies.get(i).getCol() - Treasure.treasures.get(t+1).getCol();
                                int yDelta1 = Enemy.allEnemies.get(i).getRow() - Treasure.treasures.get(t+1).getRow();
                                int delta1 = Math.abs(yDelta1/xDelta1);
                                int xDelta2 = Enemy.allEnemies.get(i).getCol() - Treasure.treasures.get(t).getCol();
                                int yDelta2 = Enemy.allEnemies.get(i).getRow() - Treasure.treasures.get(t).getRow();
                                int delta2 = Math.abs(yDelta2/xDelta2);
                                if(delta2 > delta1 && xDelta > delta1) {
                                    xDelta = xDelta1;
                                    yDelta = yDelta1;
                                } else if(delta1 > delta2 && xDelta > delta2) {
                                    xDelta = xDelta2;
                                    yDelta = yDelta2;
                                }
                            }
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
                                        Enemy.allEnemies.get(r).setTime(0);
                                    }
                                    if(Enemy.allEnemies.get(r).getSprite() == e11 || Enemy.allEnemies.get(r).getSprite() == e12 || Enemy.allEnemies.get(r).getSprite() == e13 || Enemy.allEnemies.get(r).getSprite() == e14) { Enemy.allEnemies.get(r).setSprite(e15); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e21 || Enemy.allEnemies.get(r).getSprite() == e22 || Enemy.allEnemies.get(r).getSprite() == e23 || Enemy.allEnemies.get(r).getSprite() == e24) { Enemy.allEnemies.get(r).setSprite(e25); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e31 || Enemy.allEnemies.get(r).getSprite() == e32 || Enemy.allEnemies.get(r).getSprite() == e33 || Enemy.allEnemies.get(r).getSprite() == e34) { Enemy.allEnemies.get(r).setSprite(e35); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == e41 || Enemy.allEnemies.get(r).getSprite() == e42 || Enemy.allEnemies.get(r).getSprite() == e43 || Enemy.allEnemies.get(r).getSprite() == e44) { Enemy.allEnemies.get(r).setSprite(e46); }
                                    else if(Enemy.allEnemies.get(r).getSprite() == g1 || Enemy.allEnemies.get(r).getSprite() == g2 || Enemy.allEnemies.get(r).getSprite() == g3 || Enemy.allEnemies.get(r).getSprite() == g4 || Enemy.allEnemies.get(r).getSprite() == g5){ Enemy.allEnemies.get(r).setSprite(g6); }
                                }
                                Enemy.allEnemies.get(i).setSprite(e55);
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
                    } else if(Enemy.allEnemies.get(i).getSprite() == g1 || Enemy.allEnemies.get(i).getSprite() == g2 || Enemy.allEnemies.get(i).getSprite() == g3 || Enemy.allEnemies.get(i).getSprite() == g4 || Enemy.allEnemies.get(i).getSprite() == g5 || Enemy.allEnemies.get(i).getSprite() == g6) {
                        if(!Enemy.allEnemies.get(i).isAngry()) {
                            int roll = (int)(Math.random()*4);
                            if(roll == 0 && Enemy.allEnemies.get(i).getCol()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()+1] == null) { Enemy.allEnemies.get(i).increaseCol(); }}
                            else if(roll == 1 && Enemy.allEnemies.get(i).getCol()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()][Enemy.allEnemies.get(i).getCol()-1] == null) { Enemy.allEnemies.get(i).decreaseCol(); }}
                            else if(roll == 2 && Enemy.allEnemies.get(i).getRow()+1 < 14) { if(grid[Enemy.allEnemies.get(i).getRow()+1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).increaseRow(); }}
                            else if(roll == 3 && Enemy.allEnemies.get(i).getRow()-1 > 0) { if(grid[Enemy.allEnemies.get(i).getRow()-1][Enemy.allEnemies.get(i).getCol()] == null) { Enemy.allEnemies.get(i).decreaseRow(); }}
                            Enemy.allEnemies.get(i).increaseTime();
                            if(Enemy.allEnemies.get(i).getTime() == 20) {
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setSprite(g5);
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else if(Enemy.allEnemies.get(i).isAngry() && Enemy.allEnemies.get(i).getSprite() == g6) {
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
                            if(Enemy.allEnemies.get(i).getTime() == 8) {
                                Enemy.allEnemies.get(i).setSprite(g1);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        } else {
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
                                Enemy.allEnemies.get(i).setSprite(g1);
                                Enemy.allEnemies.get(i).changeAngry();
                                Enemy.allEnemies.get(i).setTime(0);
                            }
                        }
                    }
                }
            }
        } else if(ke.getKeyChar() == KeyEvent.VK_SPACE) {
            p1.setRow((int)(Math.random()*15));
            p1.setCol((int)(Math.random()*15));
            for(int i = 0; i < Enemy.allEnemies.size(); i++) {
                Enemy.allEnemies.get(i).setRow((int)(Math.random()*15));
                Enemy.allEnemies.get(i).setCol((int)(Math.random()*15));
            }
        }
    }

    public static void hollowPurple(Graphics pen, Enemy e) {
        pen.setColor(Color.MAGENTA);
        for(int i = 0; i < 15; i++) {
            if(i != e.getCol()) { pen.fillRect(50*(i+1), 50*(e.getRow()+1), 50, 50); }
            if(grid[e.getRow()][i] != null && i != e.getCol()) {
                if(grid[e.getRow()][i] == p1) {
                    p1.setRow(7);
                    p1.setCol(7);
                    Enemy.allEnemies.clear();
                    new Enemy((int)(Math.random()*15), (int)(Math.random()*15), voidweaver);
                    for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                        while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                        while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                    }
                    p1.setGold(0);
                    p1.resetCounter();
                    score = p1.getMaxGold();
                    try {
                        File bestScore = new File("text/highscore.txt");
                        Scanner scoreCheck = new Scanner(bestScore);
                        highScore = scoreCheck.nextLine();
                        scoreCheck.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error");
                        ex.printStackTrace();
                    }
                    if(Integer.parseInt(highScore) < score) {
                        highScore = score + "";
                    }
                    p1.resetMaxGold();
                    p1.setTreasure(0);
                    activeScreen = end;
                    endSpeech.play();
                    try {
                        main1.resetAudioStream();
                        main2.resetAudioStream();
                        main3.resetAudioStream();
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                    break;
                }
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    if(grid[e.getRow()][i] == Enemy.allEnemies.get(x) && Enemy.allEnemies.get(x) != e) {
                        Enemy.allEnemies.remove(x);
                        grid[e.getRow()][i] = null;
                    }
                }
            }
        }
        for(int i = 0; i < 15; i++) {
            if(i != e.getRow()) { pen.fillRect(50*(e.getCol()+1), 50*(i+1), 50, 50); }
            if(grid[i][e.getCol()] != null && i != e.getRow()) {
                if(grid[i][e.getCol()] == p1) {
                    p1.setRow(7);
                    p1.setCol(7);
                    Enemy.allEnemies.clear();
                    new Enemy((int)(Math.random()*15), (int)(Math.random()*15), voidweaver);
                    for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                        while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                        while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                    }
                    p1.setGold(0);
                    p1.resetCounter();
                    score = p1.getMaxGold();
                    try {
                        File bestScore = new File("text/highscore.txt");
                        Scanner scoreCheck = new Scanner(bestScore);
                        highScore = scoreCheck.nextLine();
                        scoreCheck.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error");
                        ex.printStackTrace();
                    }
                    if(Integer.parseInt(highScore) < score) {
                        highScore = score + "";
                    }
                    p1.resetMaxGold();
                    p1.setTreasure(0);
                    activeScreen = end;
                    endSpeech.play();
                    try {
                        main1.resetAudioStream();
                        main2.resetAudioStream();
                        main3.resetAudioStream();
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                    break;
                }
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    if(grid[i][e.getCol()] == Enemy.allEnemies.get(x) && Enemy.allEnemies.get(x) != e) {
                        Enemy.allEnemies.remove(x);
                        grid[i][e.getCol()] = null;
                    }
                }
            }
        }
    }

    public static void hollowBlack(Graphics pen, Enemy e) {
        pen.setColor(Color.BLUE);
        for(int i = 0; i < 15; i++) {
            if(i != e.getCol()) { pen.fillRect(50*(i+1), 50*(e.getRow()+1), 50, 50); }
            if(grid[e.getRow()][i] != null && i != e.getCol()) {
                if(grid[e.getRow()][i] == p1) {
                    p1.setRow(7);
                    p1.setCol(7);
                    Enemy.allEnemies.clear();
                    new Enemy((int)(Math.random()*15), (int)(Math.random()*15), voidweaver);
                    for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                        while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                        while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                    }
                    p1.setGold(0);
                    p1.resetCounter();
                    score = p1.getMaxGold();
                    try {
                        File bestScore = new File("text/highscore.txt");
                        Scanner scoreCheck = new Scanner(bestScore);
                        highScore = scoreCheck.nextLine();
                        scoreCheck.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error");
                        ex.printStackTrace();
                    }
                    if(Integer.parseInt(highScore) < score) {
                        highScore = score + "";
                    }
                    p1.resetMaxGold();
                    p1.setTreasure(0);
                    activeScreen = end;
                    endSpeech.play();
                    try {
                        main1.resetAudioStream();
                        main2.resetAudioStream();
                        main3.resetAudioStream();
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                    break;
                }
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    if(grid[e.getRow()][i] == Enemy.allEnemies.get(x) && Enemy.allEnemies.get(x) != e) {
                        Enemy.allEnemies.remove(x);
                        grid[e.getRow()][i] = null;
                    }
                }
            }
        }
        for(int i = 0; i < 15; i++) {
            if(i != e.getRow()) { pen.fillRect(50*(e.getCol()+1), 50*(i+1), 50, 50); }
            if(grid[i][e.getCol()] != null && i != e.getRow()) {
                if(grid[i][e.getCol()] == p1) {
                    p1.setRow(7);
                    p1.setCol(7);
                    Enemy.allEnemies.clear();
                    new Enemy((int)(Math.random()*15), (int)(Math.random()*15), voidweaver);
                    for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                        while(Enemy.allEnemies.get(x).getRow()%2 != 0) { Enemy.allEnemies.get(x).setRow((int)(Math.random()*15)); }
                        while(Enemy.allEnemies.get(x).getCol()%2 != 1) { Enemy.allEnemies.get(x).setCol((int)(Math.random()*15)); }
                    }
                    p1.setGold(0);
                    p1.resetCounter();
                    score = p1.getMaxGold();
                    try {
                        File bestScore = new File("text/highscore.txt");
                        Scanner scoreCheck = new Scanner(bestScore);
                        highScore = scoreCheck.nextLine();
                        scoreCheck.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error");
                        ex.printStackTrace();
                    }
                    if(Integer.parseInt(highScore) < score) {
                        highScore = score + "";
                    }
                    p1.resetMaxGold();
                    p1.setTreasure(0);
                    activeScreen = end;
                    endSpeech.play();
                    try {
                        main1.resetAudioStream();
                        main2.resetAudioStream();
                        main3.resetAudioStream();
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                    break;
                }
                for(int x = 0; x < Enemy.allEnemies.size(); x++) {
                    if(grid[i][e.getCol()] == Enemy.allEnemies.get(x) && Enemy.allEnemies.get(x) != e) {
                        Enemy.allEnemies.remove(x);
                        grid[i][e.getCol()] = null;
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