import java.awt.*;
import javax.swing.*;

public class GameObject implements IObject
{
  int ID;
  String name;

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
}
