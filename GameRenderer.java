import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameRenderer extends JPanel
{
  ObjectHandler objectHandler;

  public GameRenderer(ObjectHandler handler, JFrame frame)
  {
    super();
    objectHandler = handler;
    this.setPreferredSize(new Dimension(1000, 500));
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
    for(GameObject obj : objectHandler.RenderQueue)
    {
      ScreenPosition screenRect = new ScreenPosition(obj.transform);

      g.setColor(obj.colour);
      g.fillRect(screenRect.x, screenRect.y, screenRect.width, screenRect.height);
    }
  }
}
