import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class GameLoop
{
  GameRenderer renderer;
  ObjectHandler objectHandler;

  public void play()
  {
    //init the object handler
    objectHandler = new ObjectHandler();
    //create a frame
    JFrame frame = new JFrame("Learn To Jump");
    frame.setVisible(true);
    frame.setSize(new Dimension(1000, 500));
    //init renderer
    renderer = new GameRenderer(objectHandler, frame);
    //set the game loop
    new Timer(100, tick).start();

  }

  ActionListener tick = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        renderer.render();
        objectHandler.updateObjects(1);
    }
  };
}
