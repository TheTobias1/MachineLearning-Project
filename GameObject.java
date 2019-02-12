import java.awt.*;
import javax.swing.*;

public class GameObject implements IObject
{
  protected int ID;
  protected String name;

  public Color colour;
  public ObjectRect transform;

  public GameObject(String newName, Color newColour, ObjectRect transformData)
  {
    transform = transformData;
    colour = newColour;
    name = newName;
    ID = ObjectHandler.generateObjectID();

    ObjectHandler.RenderQueue.add(this);
    ObjectHandler.CurrentObjects.put(ID, this);
  }

  public void Destroy()
  {
    ObjectHandler.CurrentObjects.remove(ID);
    ObjectHandler.RenderQueue.remove(this);
  }

  public int getID()
  {
    return ID;
  }

  public String getName()
  {
    return name;
  }

  protected Vector2 TopRightPoint()
  {
    return Vector2.add(transform.position, new Vector2(transform.scale.x / 2, transform.scale.y / 2));
  }

  protected Vector2 BottomRightPoint()
  {
    return Vector2.add(transform.position, new Vector2(transform.scale.x / 2, -transform.scale.y / 2));
  }

  protected Vector2 BottomLeftPoint()
  {
    return Vector2.add(transform.position, new Vector2(-transform.scale.x / 2, -transform.scale.y / 2));
  }

  protected Vector2 TopLeftPoint()
  {
    return Vector2.add(transform.position, new Vector2(-transform.scale.x / 2, transform.scale.y / 2));
  }
}
