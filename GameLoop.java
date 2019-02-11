import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class GameLoop
{
  GameRenderer renderer;
  ObjectHandler objectHandler;

  private final int FRAME_RATE = 100;
  double deltaTime;

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
    deltaTime = 100 / FRAME_RATE;
    new Timer(1000 / FRAME_RATE, tick).start();

  }

  ActionListener tick = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        renderer.render();
        objectHandler.updateObjects(deltaTime);
    }
  };
}
