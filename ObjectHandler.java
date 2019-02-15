import java.util.*;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.util.Random;

public class ObjectHandler
{
  public static int currentIdValue;
  public static HashMap<Integer, IObject> CurrentObjects = new HashMap<Integer, IObject>();
  public static ArrayList<GameObject> RenderQueue;
  public static HashMap<Integer, IUpdatable> UpdateQueue;
  public static HashMap<Integer, ICollidable> CollidableObjects;
  public ArrayList<Player> players;

  public ObjectHandler()
  {
    generateLevel();
  }

  void generateLevel()
  {
    ObjectHandler.RenderQueue = new ArrayList<GameObject>();
    ObjectHandler.UpdateQueue = new HashMap<Integer, IUpdatable>();
    ObjectHandler.CollidableObjects = new HashMap<Integer, ICollidable>();
    players = new ArrayList<Player>();

    ObjectRect newTransform = new ObjectRect(new Vector2(1,25), new Vector2(3,3));
    Player player = new Player("Player", Color.BLUE, newTransform, new PlayerInput());
    players.add(player);

    newTransform = new ObjectRect(new Vector2(15,5), new Vector2(30,10));
    StaticGameObject ground = new StaticGameObject("ground", Color.WHITE, newTransform);

    Random rand = new Random();

    float randomX = rand.nextFloat();
    float Y = rand.nextFloat();
    if(randomX > 0.6f)
    {
      Y *= 13;
    }
    else
    {
      Y *= 35;
    }
    newTransform = new ObjectRect(new Vector2(40 + randomX  * 55,5 + Y), new Vector2(16,10));
    StaticGameObject goalGround = new StaticGameObject("goalGround", Color.GREEN, newTransform);
  }

  public void updateObjects(double deltaTime)
  {
    for(IUpdatable obj : UpdateQueue.values())
    {
      obj.update(deltaTime);
    }

    deathCheck();
  }

  void deathCheck()
  {
    for(Player pl : players)
    {
      if(pl.transform.position.y > 0)
        return;
    }

    generateLevel();
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
