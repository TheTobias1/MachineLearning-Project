import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

public class Player extends GameObject implements IUpdatable
{
  float speed = 0.6f;
  float acceleration = 0.05f;
  boolean Grounded;
  boolean hasSecondJump = false;
  public Vector2 currentVelocity;
  Input thisInput;

  boolean grounded;
  long nextJump;

  public Player(String newName, Color newColour, ObjectRect transformData, Input input)
  {
    super(newName, newColour, transformData);
    ObjectHandler.UpdateQueue.put(ID, this);
    currentVelocity = new Vector2(0,-0.5f);

    thisInput = input;
  }

  public void update(double deltaTime)
  {
    grounded = groundCheck();
    applyInput();
    updatePosition(deltaTime);
  }

  void applyInput()
  {
    InputData inputData = thisInput.getInput();

    if(grounded)
      currentVelocity.x = (float)inputData.horizontalInput * speed;
    else
    {
      boolean sameDir = (currentVelocity.x < 0 && inputData.horizontalInput < 0) ||
      (currentVelocity.x > 0 && inputData.horizontalInput > 0);

      if(Math.abs(currentVelocity.x) < speed || !sameDir)
      {
        currentVelocity.x += acceleration * (float)inputData.horizontalInput;
      }
      else
      {
        currentVelocity.x = (float)inputData.horizontalInput * speed;
      }
    }

    if(inputData.jump)
    {
      doJump();
    }
  }

  boolean groundCheck()
  {
    Vector2[] rays = new Vector2[2];

    rays[0] = Vector2.add(transform.position, new Vector2(-transform.scale.x / 1.9f, 0));
    rays[1] = Vector2.add(transform.position, new Vector2(transform.scale.x / 1.9f, 0));

    for(Vector2 ray : rays)
    {
      IntersectHit hit = Physics.Raycast(ray, new Vector2(0, -transform.scale.y * 0.6f));
      if(hit.hit && Vector2.equals(hit.normal, new Vector2(0,1)))
      {
        hasSecondJump = true;
        return true;
      }
    }

    return false;
  }

  void updatePosition(double deltaTime)
  {
    if(!grounded)
      currentVelocity.y -= 0.05f;

    Vector2 frameVelocity = Vector2.multiply(currentVelocity, (float)deltaTime);
    //points to raycast from
    ArrayList<Vector2> raycatsPoints = calcualteRayPoints();

    //store the closest point info
    float closestDistance = 10000000;
    int closestPoint = -1;
    IntersectHit closestIntersect = new IntersectHit();

    //iterate through and check for collisions
    int i = 0;
    for(Vector2 point : raycatsPoints)
    {
      IntersectHit curHit = Physics.Raycast(point, frameVelocity);
      if(curHit.hit)
      {

        {
          clampVelocity(curHit.normal);
          float dist = Vector2.distance(curHit.point, point);
          if(dist < closestDistance)//new closest
          {
            closestPoint = i;
            closestIntersect = curHit;
            closestDistance = dist;

            if(!curHit.isParralel)
            {
              Vector2 newVelocity = Vector2.subtract(point, curHit.point);
              frameVelocity = Vector2.setMagnitude(frameVelocity, Vector2.magnitude(newVelocity));
            }
            else
            {
              if(curHit.normal.y == 0)
              {
                System.out.println("bad hit x1: " + curHit.normal.x + " x2: " + frameVelocity.x);
                //just clamp the x
                if((curHit.normal.x < 0 && frameVelocity.x > 0) || (curHit.normal.x > 0 && frameVelocity.x < 0))
                  frameVelocity.x = 0;
              }
            }
          }
        }
      }
      ++i;
    }

    transform.position = Vector2.add(transform.position, frameVelocity);
  }

  ArrayList<Vector2> calcualteRayPoints()
  {
    ArrayList<Vector2> raycatsPoints = new ArrayList<Vector2>();
    if(currentVelocity.y > 0)
    {
      raycatsPoints.add(TopRightPoint());
      raycatsPoints.add(TopLeftPoint());
      return raycatsPoints;
    }
    else if(currentVelocity.y < 0)
    {
      raycatsPoints.add(BottomRightPoint());
      raycatsPoints.add(BottomLeftPoint());
      return raycatsPoints;
    }
    else if(grounded)
    {
      if(currentVelocity.x > 0)
      {
        raycatsPoints.add(TopRightPoint());
        raycatsPoints.add(BottomRightPoint());
        return raycatsPoints;
      }
      else if(currentVelocity.x < 0)
      {
        raycatsPoints.add(TopLeftPoint());
        raycatsPoints.add(BottomLeftPoint());
        return raycatsPoints;
      }
    }

    raycatsPoints.add(TopLeftPoint());
    raycatsPoints.add(BottomLeftPoint());
    raycatsPoints.add(TopRightPoint());
    raycatsPoints.add(BottomRightPoint());
    return raycatsPoints;
  }

  void clampVelocity(Vector2 normal)
  {
    if(Vector2.equals(normal, new Vector2(1,0)))
    {
      if(currentVelocity.x < 0)
        currentVelocity.x = 0;
    }

    if(Vector2.equals(normal, new Vector2(-1,0)))
    {
      if(currentVelocity.x > 0)
        currentVelocity.x = 0;
    }

    if(Vector2.equals(normal, new Vector2(0,1)))
    {
      if(currentVelocity.y < 0)
        currentVelocity.y = 0;
    }

    if(Vector2.equals(normal, new Vector2(0,-1)))
    {
      if(currentVelocity.y > 0)
        currentVelocity.y = 0;
    }
  }

  void doJump()
  {
    if((grounded || hasSecondJump) && System.currentTimeMillis() > nextJump)
    {
      if(!grounded)
        hasSecondJump = false;
      nextJump = System.currentTimeMillis() + 200;
      currentVelocity.y = 1.3f;
    }
  }
}
