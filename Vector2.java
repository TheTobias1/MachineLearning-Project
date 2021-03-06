import java.lang.Math;

public class Vector2
{
  public float x;
  public float y;

  public Vector2()
  {
    x = 0;
    y = 0;
  }

  public Vector2(float newX, float newY)
  {
    x = newX;
    y = newY;
  }

  public static Vector2 negate(Vector2 in)
  {
    return new Vector2(-in.x, -in.y);
  }

  public static float dot(Vector2 a, Vector2 b)
  {
    Vector2 aN = Vector2.normalise(a);
    Vector2 bN = Vector2.normalise(b);

    return aN.x * bN.x + aN.y * aN.y;
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

  public static float cross2D(Vector2 a, Vector2 b)
  {
    return a.x * b.y - a.y * b.x;
  }

  public static float distance(Vector2 a, Vector2 b)
  {
    double ax = (double)a.x;
    double bx = (double)b.x;
    double ay = (double)a.y;
    double by = (double)b.y;

    double dist =  Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2));
    return Math.abs((float)dist);
  }

  public static float magnitude(Vector2 a)
  {
    return (float)Math.sqrt(Math.pow((double)a.x, 2) + Math.pow((double)a.y, 2));
  }

  public static Vector2 normalise(Vector2 a)
  {
    float mag = Vector2.magnitude(a);
    return new Vector2(a.x / mag, a.y / mag);
  }

  public static boolean equals(Vector2 a, Vector2 b)
  {
    return a.x == b.x && a.y == b.y;
  }

  public static Vector2 setMagnitude(Vector2 a, float newMagnitude)
  {
    return Vector2.multiply(Vector2.normalise(a), newMagnitude);
  }

  public static boolean isParralel(Vector2 a, Vector2 b)
  {
    float d = Vector2.dot(a, b);
    return d == 1f || d == -1f;
  }

  public static IntersectHit LineIntersect(Vector2 aPoint, Vector2 aDir, Vector2 bPoint, Vector2 bDir)
  {
    float kIntersect = Vector2.cross2D(Vector2.subtract(bPoint, aPoint), bDir) / Vector2.cross2D(aDir, bDir);
    float jIntersect = Vector2.cross2D(Vector2.subtract(aPoint, bPoint), aDir) / Vector2.cross2D(bDir, aDir);

    IntersectHit hit = new IntersectHit();

    //got a hit
    if(kIntersect >= 0 && kIntersect <= 1 && jIntersect >= 0 && jIntersect <= 1)
    {
      hit.point = Vector2.add(aPoint, Vector2.multiply(aDir, kIntersect));
      hit.hit = true;
    }

    return hit;
  }
}
