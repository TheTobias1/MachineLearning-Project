import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameRenderer extends JPanel
{
  ObjectHandler objectHandler;
  public static int SCREEN_WIDTH = 1000;
  public static int SCREEN_HEIGHT = 500;

  public GameRenderer(ObjectHandler handler, JFrame frame)
  {
    super();
    objectHandler = handler;
    this.setPreferredSize(new Dimension(GameRenderer.SCREEN_WIDTH, GameRenderer.SCREEN_HEIGHT));
    //this.setVisible(true);
    this.setBackground(Color.BLACK);
    frame.add(this);
  }

  public void render()
  {
    this.repaint();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    float height = GameRenderer.SCREEN_HEIGHT;

    for(GameObject obj : objectHandler.RenderQueue)
    {
      ScreenPosition screenRect = new ScreenPosition(obj.transform, height);

      g.setColor(obj.colour);
      g.fillRect(screenRect.x, screenRect.y, screenRect.width, screenRect.height);
    }
  }
}
