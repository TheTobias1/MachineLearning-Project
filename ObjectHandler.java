import java.util.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;

public class ObjectHandler
{
  public static int currentIdValue;
  public static HashMap<Integer, IObject> CurrentObjects = new HashMap<Integer, IObject>();
  public static ArrayList<GameObject> RenderQueue;
  public static HashMap<Integer, IUpdatable> UpdateQueue;
  public static HashMap<Integer, ICollidable> CollidableObjects;

  public ObjectHandler()
  {
    ObjectHandler.RenderQueue = new ArrayList<GameObject>();
    ObjectHandler.UpdateQueue = new HashMap<Integer, IUpdatable>();
    ObjectHandler.CollidableObjects = new HashMap<Integer, ICollidable>();

    ObjectRect newTransform = new ObjectRect(new Vector2(80,25), new Vector2(3,3));
    Player player = new Player("Player", Color.BLUE, newTransform);

    newTransform = new ObjectRect(new Vector2(50,25), new Vector2(6,200));
    StaticGameObject blueSquare = new StaticGameObject("BlueSquare", Color.WHITE, newTransform);

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

  public static int generateObjectID()
  {
    int storedID = ObjectHandler.currentIdValue++;
    return storedID;
  }
}
