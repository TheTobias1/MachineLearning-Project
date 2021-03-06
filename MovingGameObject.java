import java.awt.*;
import javax.swing.*;

public class MovingGameObject extends GameObject implements IUpdatable, Runnable
{
  public Thread updateThread;
  double currentDeltaTime;

  public Vector2 objectVelocity;
  boolean Moving = true;

  public MovingGameObject(String newName, Color newColour, ObjectRect transformData, Vector2 velocity)
  {
    super(newName, newColour, transformData);
    objectVelocity = velocity;
    ObjectHandler.UpdateQueue.put(ID, this);
  }

  public void update(double deltaTime)
  {
    updateThread = new Thread(this, name + ID);
    currentDeltaTime = deltaTime;
    updateThread.start();
  }

  public void run()
  {
    Vector2 frameVelocity = Vector2.multiply(objectVelocity, (float)currentDeltaTime);
    transform.position = Vector2.add(transform.position, frameVelocity);

    if(Moving)
    {
      if(Physics.Raycast(transform.position, Vector2.multiply(objectVelocity, 2)).hit)
      {
        System.out.println("HIT SOMETHING");
        objectVelocity = new Vector2();
        Moving = false;
      }
    }
  }

  public void Destroy()
  {
    super.Destroy();
    ObjectHandler.UpdateQueue.remove(ID);
  }
}
