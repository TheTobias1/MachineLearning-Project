public class Physics
{
  public static IntersectHit Raycast(Vector2 point, Vector2 direction)
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
        boolean parralel = Vector2.isParralel(edge.direction, direction);

        boolean badHit = Vector2.distance(point, edge.pointA) < 0.2f ||
        Vector2.distance(point, edge.pointB) < 0.2f;

        if(badHit)
          continue;
        //check intersect
        hit = Vector2.LineIntersect(point, direction, edge.pointA, edge.direction);
        hit.isParralel = parralel;

        if(hit.hit)//it's a hit
        {
          float distance = Vector2.distance(point, hit.point);
          if(distance < closestDistance)//this is the closet one so far one
          {
            hit.normal = edge.normal;
            closestHit = hit;
          }
        }
      }
    }

    return closestHit;
  }

}
