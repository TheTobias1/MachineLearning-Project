import java.awt.*;
import javax.swing.*;

public class MovingGameObject extends GameObject implements IUpdatable
{

  public Vector2 objectVelocity;

  public MovingGameObject(String newName, Color newColour, ObjectRect transformData, Vector2 velocity)
  {
    super(newName, newColour, transformData);
    objectVelocity = velocity;
    ObjectHandler.UpdateQueue.put(ID, this);
  }

  public void update(double deltaTime)
  {
    Vector2 frameVelocity = Vector2.multiply(objectVelocity, (float)deltaTime);
    transform.position = Vector2.add(transform.position, frameVelocity);
  }

  public void Destroy()
  {
    super.Destroy();
    ObjectHandler.UpdateQueue.remove(ID);
  }
}
