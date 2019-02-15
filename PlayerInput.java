import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class PlayerInput extends Input implements KeyListener
{
  public PlayerInput()
  {
    currentInputData = new InputData();
    GameLoop.gameFrame.addKeyListener(this);
  }

  InputData currentInputData;

  boolean RightPressed;
  boolean LeftPressed;
  boolean JumpPressed;

  public InputData getInput()
  {
    if(RightPressed)
    {
      currentInputData.horizontalInput = 1;
    }
    else if(LeftPressed)
    {
      currentInputData.horizontalInput = -1;
    }
    else
    {
      currentInputData.horizontalInput = 0;
    }

    currentInputData.jump = JumpPressed;

    return currentInputData;
  }

  public void keyPressed(KeyEvent p)
  {
    int key = p.getKeyCode();

    if(key == KeyEvent.VK_A)//moving left
    {
      LeftPressed = true;
    }
    else if(key == KeyEvent.VK_D)//moving right
    {
      RightPressed = true;
    }
    else if(key == KeyEvent.VK_W)
    {
      JumpPressed = true;
    }
  }

  public void keyReleased(KeyEvent r)
  {
    int key = r.getKeyCode();

    if(key == KeyEvent.VK_A)//moving left
    {
      LeftPressed = false;
    }
    else if(key == KeyEvent.VK_D)//moving right
    {
      RightPressed = false;
    }
    else if(key == KeyEvent.VK_W)
    {
      JumpPressed = false;
    }
  }

  public void keyTyped(KeyEvent t)
  {
    //nothing
  }
}
