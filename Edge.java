public class Edge
{
  public Vector2 pointA;
  public Vector2 pointB;
  public Vector2 direction;

  public Edge(Vector2 a, Vector2 b)
  {
    pointA = a;
    pointB = b;
    direction = Vector2.subtract(b, a);
  }
}
