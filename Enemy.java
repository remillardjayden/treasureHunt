import java.util.ArrayList;
public class Enemy extends GamerObject{
    public static ArrayList<Enemy> allEnemies;
    public Enemy(int row, int col) {
        super(row, col);
        if(allEnemies == null) { allEnemies = new ArrayList<Enemy>(); }
        allEnemies.add(this);
    }
}
