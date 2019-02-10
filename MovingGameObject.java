import java.awt.*;
import javax.swing.*;

public class MovingGameObject extends GameObject implements IUpdatable
{

  public Vector2 objectVelocity;

  public MovingGameObject(String newName, Color newColour, ObjectRect transformData,
  int newID, ObjectHandler handler, Vector2 velocity)
  {
    super(newName, newColour, transformData, newID, handler);
    objectVelocity = velocity;
  }

  public void update(double deltaTime)
  {
    Vector2 frameVelocity = Vector2.multiply(objectVelocity, (float)deltaTime);
    transform.position = Vector2.add(transform.position, frameVelocity);
    System.out.println("Move");
  }
}
