import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    static BufferedImage e11, e12, e13, e14;
    static BufferedImage e21, e22, e23, e24;
    static BufferedImage e31, e32, e33, e34;
    static BufferedImage e41, e42, e43, e44;
    static BufferedImage emptiness;
    public treasureSearch() {
        // initialize variables here
        try {
            playerImg=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/player.png"));
            treasureImg=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/treasure.png"));
            e11=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy1.1.png"));
            e12=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy1.2.png"));
            e13=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy1.3.png"));
            e14=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy1.4.png"));
            e21=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy2.1.png"));
            e22=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy2.2.png"));
            e23=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy2.3.png"));
            e24=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy2.4.png"));
            e31=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy3.1.png"));
            e32=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy3.2.png"));
            e33=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy3.3.png"));
            e34=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy3.4.png"));
            e41=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy4.1.png"));
            e42=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy4.2.png"));
            e43=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy4.3.png"));
            e44=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/enemy4.4.png"));
            emptiness=ImageIO.read(getClass().getResourceAsStream("/imagesOrPiskels/void.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    } else if(Math.abs(xDelta) <= 4 && Math.abs(yDelta) <= 4) {
                        if(xDelta < 0 && Math.abs(yDelta) < Math.abs(xDelta) || yDelta == 0 && xDelta != 0) {
                            Enemy.allEnemies.get(i).increaseCol();
                        } else if(xDelta > 0 && Math.abs(yDelta) < Math.abs(xDelta) || yDelta == 0 && xDelta != 0) {
                            Enemy.allEnemies.get(i).decreaseCol();
                        } else if(yDelta < 0 && Math.abs(xDelta) < Math.abs(yDelta) || xDelta == 0 && yDelta != 0) {
                            Enemy.allEnemies.get(i).increaseRow();
                        } else if(yDelta > 0 && Math.abs(xDelta) < Math.abs(yDelta) || xDelta == 0 && yDelta != 0) {
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
                    int roll = (int)(Math.random()*4);
                    if(roll == 0){
                        Enemy.allEnemies.get(i).decreaseCol();
                        if(Enemy.allEnemies.get(i).getSprite() == e12 || Enemy.allEnemies.get(i).getSprite() == e13 || Enemy.allEnemies.get(i).getSprite() == e14) { Enemy.allEnemies.get(i).setSprite(e11); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e22 || Enemy.allEnemies.get(i).getSprite() == e23 || Enemy.allEnemies.get(i).getSprite() == e24) { Enemy.allEnemies.get(i).setSprite(e21); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e32 || Enemy.allEnemies.get(i).getSprite() == e33 || Enemy.allEnemies.get(i).getSprite() == e34) { Enemy.allEnemies.get(i).setSprite(e31); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e42 || Enemy.allEnemies.get(i).getSprite() == e43 || Enemy.allEnemies.get(i).getSprite() == e44) { Enemy.allEnemies.get(i).setSprite(e41); }
                        if(Enemy.allEnemies.get(i).getCol() < 0) {
                            Enemy.allEnemies.get(i).setCol(14);
                        }
                    } else if(roll == 1) {
                        Enemy.allEnemies.get(i).decreaseRow();
                        if(Enemy.allEnemies.get(i).getSprite() == e11 || Enemy.allEnemies.get(i).getSprite() == e12 || Enemy.allEnemies.get(i).getSprite() == e13) { Enemy.allEnemies.get(i).setSprite(e14); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e21 || Enemy.allEnemies.get(i).getSprite() == e22 || Enemy.allEnemies.get(i).getSprite() == e23) { Enemy.allEnemies.get(i).setSprite(e24); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e31 || Enemy.allEnemies.get(i).getSprite() == e32 || Enemy.allEnemies.get(i).getSprite() == e33) { Enemy.allEnemies.get(i).setSprite(e34); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e41 || Enemy.allEnemies.get(i).getSprite() == e42 || Enemy.allEnemies.get(i).getSprite() == e43) { Enemy.allEnemies.get(i).setSprite(e44); }
                        if(Enemy.allEnemies.get(i).getRow() < 0) {
                            Enemy.allEnemies.get(i).setRow(14);
                        }
                    } else if(roll == 2) {
                        Enemy.allEnemies.get(i).increaseCol();
                        if(Enemy.allEnemies.get(i).getSprite() == e11 || Enemy.allEnemies.get(i).getSprite() == e12 || Enemy.allEnemies.get(i).getSprite() == e14) { Enemy.allEnemies.get(i).setSprite(e13); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e21 || Enemy.allEnemies.get(i).getSprite() == e22 || Enemy.allEnemies.get(i).getSprite() == e24) { Enemy.allEnemies.get(i).setSprite(e23); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e31 || Enemy.allEnemies.get(i).getSprite() == e32 || Enemy.allEnemies.get(i).getSprite() == e34) { Enemy.allEnemies.get(i).setSprite(e33); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e41 || Enemy.allEnemies.get(i).getSprite() == e42 || Enemy.allEnemies.get(i).getSprite() == e44) { Enemy.allEnemies.get(i).setSprite(e43); }
                        if(Enemy.allEnemies.get(i).getCol() > 14) {
                            Enemy.allEnemies.get(i).setCol(0);
                        }
                    } else {
                        Enemy.allEnemies.get(i).increaseRow();
                        if(Enemy.allEnemies.get(i).getSprite() == e11 || Enemy.allEnemies.get(i).getSprite() == e13 || Enemy.allEnemies.get(i).getSprite() == e14) { Enemy.allEnemies.get(i).setSprite(e12); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e21 || Enemy.allEnemies.get(i).getSprite() == e23 || Enemy.allEnemies.get(i).getSprite() == e24) { Enemy.allEnemies.get(i).setSprite(e22); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e31 || Enemy.allEnemies.get(i).getSprite() == e33 || Enemy.allEnemies.get(i).getSprite() == e34) { Enemy.allEnemies.get(i).setSprite(e32); }
                        else if (Enemy.allEnemies.get(i).getSprite() == e41 || Enemy.allEnemies.get(i).getSprite() == e43 || Enemy.allEnemies.get(i).getSprite() == e44) { Enemy.allEnemies.get(i).setSprite(e42); }
                        if(Enemy.allEnemies.get(i).getRow() > 14) {
                            Enemy.allEnemies.get(i).setRow(0);
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