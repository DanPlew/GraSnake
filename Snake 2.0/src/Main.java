import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] arg){

        EventQueue.invokeLater(()->{
            JFrame frame = new JFrame();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/4,dim.height/4);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setTitle("Snake 2.0");

            SnakePanel snakePanel = new SnakePanel();
            frame.add(snakePanel);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
