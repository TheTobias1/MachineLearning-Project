import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class GameLoop
{
  GameRenderer renderer;

  public void play()
  {
    ObjectHandler handler = new ObjectHandler();
    JFrame frame = new JFrame("Learn To Jump");
    frame.setVisible(true);
    frame.setSize(new Dimension(1000, 500));
    renderer = new GameRenderer(handler, frame);
    new Timer(1000, tick).start();

  }

  ActionListener tick = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        renderer.render();
        System.out.println("tick");
    }
  };
}
