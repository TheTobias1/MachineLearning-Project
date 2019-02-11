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
    return new Edge(TopLeftPoint(), TopRightPoint());
  }
  public Edge getRightEdge()
  {
    return new Edge(BottomRightPoint(), TopRightPoint());
  }
  public Edge getBottomEdge()
  {
    return new Edge(BottomRightPoint(), BottomLeftPoint());
  }
  public Edge getLeftEdge()
  {
    return new Edge(TopLeftPoint(), BottomLeftPoint());
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
