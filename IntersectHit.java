public class IntersectHit
{
  public boolean hit;
  public Vector2 point;
  public Vector2 normal;
  public boolean isParralel;

  public IntersectHit()
  {
    hit = false;
    point = new Vector2();
    normal = new Vector2();
    isParralel = false;
  }
}
