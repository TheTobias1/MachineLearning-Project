public class Physics
{
  public static IntersectHit Raycast(Vector2 point, Vector2 Direction)
  {
    //define the closest point
    IntersectHit closestHit = new IntersectHit();
    float closestDistance = 1000000;

    IntersectHit hit = new IntersectHit();
    //iterate through all colliders
    for(ICollidable col : ObjectHandler.CollidableObjects.values())
    {
      //iterate through each edge
      Edge[] edges = col.getAllEdges();
      for(Edge edge : edges)
      {
        //check intersect
        hit = Vector2.LineIntersect(point, Direction, edge.pointA, edge.direction);

        if(hit.hit)//it's a hit
        {
          float distance = Vector2.distance(point, hit.point);
          if(distance < closestDistance)//this is the closet one so far one
          {
            closestHit = hit;
          }
        }
      }
    }

    return closestHit;
  }
}
