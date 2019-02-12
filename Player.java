import java.awt.*;
import javax.swing.*;

public class Player extends GameObject implements IUpdatable
{

  boolean Grounded;
  public Vector2 currentVelocity;

  public Player(String newName, Color newColour, ObjectRect transformData)
  {
    super(newName, newColour, transformData);
    ObjectHandler.UpdateQueue.put(ID, this);
    currentVelocity = new Vector2(-0.4f,0);
  }

  public void update(double deltaTime)
  {
    updatePosition(deltaTime);
  }

  void updatePosition(double deltaTime)
  {
    Vector2 frameVelocity = Vector2.multiply(currentVelocity, (float)deltaTime);
    //points to raycast from
    Vector2[] raycatsPoints = new Vector2[4];
    raycatsPoints[0] = TopRightPoint();
    raycatsPoints[1] = BottomRightPoint();
    raycatsPoints[2] = TopLeftPoint();
    raycatsPoints[3] = BottomLeftPoint();
    //store the closest point info
    float closestDistance = 10000000;
    int closestPoint = -1;
    IntersectHit closestIntersect;

    //iterate through and check for collisions
    int i = 0;
    for(Vector2 point : raycatsPoints)
    {
      IntersectHit curHit = Physics.Raycast(point, frameVelocity);
      if(curHit.hit)
      {

        float dist = Vector2.distance(curHit.point, point);
        if(dist < closestDistance)//new closest
        {
          closestPoint = i;
          closestIntersect = curHit;
          closestDistance = dist;

          Vector2 newVelocity = Vector2.subtract(point, curHit.point);
          frameVelocity = Vector2.setMagnitude(frameVelocity, Vector2.magnitude(newVelocity));
        }
      }
      ++i;
    }

    transform.position = Vector2.add(transform.position, frameVelocity);
  }

}
