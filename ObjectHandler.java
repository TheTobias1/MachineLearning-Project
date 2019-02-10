import java.util.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class ObjectHandler
{
  int currentIdValue;
  HashMap<Integer, IObject> CurrentObjects = new HashMap<Integer, IObject>();
  public ArrayList<GameObject> RenderQueue = new ArrayList<GameObject>();
  public HashMap<Integer, IUpdatable> UpdateQueue = new HashMap<Integer, IUpdatable>();

  public ObjectHandler()
  {
    ObjectRect objTrans = new ObjectRect(new Vector2 (-2, 1), new Vector2 (5, 5));
    SpawnGameObject(objTrans, "Player", Color.BLUE);

    objTrans = new ObjectRect(new Vector2 (3, 9), new Vector2 (5, 5));
    SpawnMovingGameObject(objTrans, "Player2", Color.RED, new Vector2(2,-2));
  }

  public void updateObjects(double deltaTime)
  {
    for(IUpdatable obj : UpdateQueue.values())
    {
      obj.update(deltaTime);
    }
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

  public MovingGameObject SpawnMovingGameObject(ObjectRect newTransform, String name, Color colour, Vector2 velocity)
  {
    int newID = generateObjectID();
    MovingGameObject newGameObject = new MovingGameObject(name, colour, newTransform, newID, this, velocity);

    RenderQueue.add(newGameObject);
    CurrentObjects.put(newID, newGameObject);
    UpdateQueue.put(newID, newGameObject);
    System.out.println("NEW OBJECT: " + name);

    return newGameObject;
  }
}
