import java.awt.*;
import javax.swing.*;

public class StaticGameObject extends GameObject implements ICollidable
{

  public StaticGameObject(String newName, Color newColour, ObjectRect transformData)
  {
    super(newName, newColour, transformData);

    ObjectHandler.CollidableObjects.put(ID, this);
  }

  public Edge getTopEdge()
  {
    return new Edge(TopLeftPoint(), TopRightPoint(), new Vector2(0,1));
  }
  public Edge getRightEdge()
  {
    return new Edge(BottomRightPoint(), TopRightPoint(), new Vector2(1,0));
  }
  public Edge getBottomEdge()
  {
    return new Edge(BottomRightPoint(), BottomLeftPoint(), new Vector2(0,-1));
  }
  public Edge getLeftEdge()
  {
    return new Edge(TopLeftPoint(), BottomLeftPoint(), new Vector2(-1,0));
  }

  public Edge[] getAllEdges()
  {
    Edge[] edges = new Edge[4];

    edges[0] = getTopEdge();
    edges[1] = getRightEdge();
    edges[2] = getBottomEdge();
    edges[3] = getLeftEdge();

    return edges;
  }
}
