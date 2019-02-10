import java.awt.*;
import javax.swing.*;

public class GameObject implements IObject
{
  ObjectHandler objectHandler;

  int ID;
  String name;

  public Color colour;
  public ObjectRect transform;

  public GameObject(String newName, Color newColour, ObjectRect transformData, int newID, ObjectHandler handler)
  {
    transform = transformData;
    colour = newColour;
    name = newName;
    ID = newID;

    objectHandler = handler;
  }

  public void Destroy()
  {
    objectHandler.RenderQueue.remove(this);
  }

  public int getID()
  {
    return ID;
  }

  public String getName()
  {
    return name;
  }
}
