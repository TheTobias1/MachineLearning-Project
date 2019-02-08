import java.util.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class ObjectHandler
{
  int currentIdValue;
  HashMap<Integer, IObject> CurrentObjects = new HashMap<Integer, IObject>();
  public ArrayList<GameObject> RenderQueue = new ArrayList<GameObject>();

  public ObjectHandler()
  {
    ObjectRect objTrans = new ObjectRect(new Vector2 (100, 100), new Vector2 (90, 50));
    SpawnGameObject(objTrans, "Player", Color.BLUE);
  }

  public void addObject(IObject newObject, int key)
  {
    CurrentObjects.put(key, newObject);
  }

  public IObject getObject(int ID)
  {
    return CurrentObjects.get(ID);
  }

  public void dereferenceObject(int ID)
  {
    CurrentObjects.remove(ID);
  }

  public int generateObjectID()
  {
    int storedID = currentIdValue;
    ++currentIdValue;
    return storedID;
  }

  public GameObject SpawnGameObject(ObjectRect newTransform, String name, Color colour)
  {
    int newID = generateObjectID();
    GameObject newGameObject = new GameObject(name, colour, newTransform, newID, this);
    RenderQueue.add(newGameObject);
    CurrentObjects.put(newID, newGameObject);
    System.out.println("NEW OBJECT: " + name);
    return newGameObject;
  }
}
