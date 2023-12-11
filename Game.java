import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
public abstract class Game implements KeyListener, MouseListener, MouseMotionListener {
    private JFrame frame;
    private GamePanel gamePanel;
    boolean running;
    private treasureSearch game;    
    protected void start(String title, int width, int height) {
    	this.game = (treasureSearch)this;
    	running = true;
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.setResizable(true);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.setBackground(Color.lightGray);
        run();
    }
    class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        @Override
        public void paintComponent(Graphics g) { game.draw(g); }
    }
    private void run() {
        while (running==true) {
           game.update();
            try { Thread.sleep(10); }
            catch (InterruptedException e) {}
            frame.repaint();
        }
        frame.repaint();
        frame.setBackground(Color.darkGray);
    }
}