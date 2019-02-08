public class Vector2
{
  public float x;
  public float y;

  public Vector2(float newX, float newY)
  {
    x = newX;
    y = newY;
  }

  public static Vector2 add(Vector2 a, Vector2 b)
  {
    Vector2 output = new Vector2(a.x + b.x, a.y + b.y);
    return output;
  }

  public static Vector2 subtract(Vector2 a, Vector2 b)
  {
    Vector2 output = new Vector2(a.x - b.x, a.y - b.y);
    return output;
  }

  public static Vector2 multiply(Vector2 a, float scale)
  {
    Vector2 output = new Vector2(a.x * scale, a.y * scale);
    return output;
  }
}
