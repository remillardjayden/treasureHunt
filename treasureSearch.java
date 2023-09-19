import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;

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
    public treasureSearch() {
        // initialize variables here
        grid = new Object[15][15];
        p1 = new Player(7, 7);
        new Enemy((int)(Math.random()*15), (int)(Math.random()*15));
        t = new Treasure((int)(Math.random()*15), (int)(Math.random()*15), (int)((Math.random()*5)+1));
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
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        activeScreen.keyPressed(ke);
    }

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