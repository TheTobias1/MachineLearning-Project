import java.lang.Math;

public class ScreenPosition
{
  public int x;
  public int y;
  public int height;
  public int width;

  public ScreenPosition(ObjectRect rect)
  {
    //Convert to pixel sizes
    Vector2 pixelPosition = Vector2.multiply(rect.position, 10);
    Vector2 pixelScale = Vector2.multiply(rect.scale, 10);

    //round
    x = Math.round(pixelPosition.x);
    y = Math.round(pixelPosition.y);
    height = Math.round(pixelScale.y);
    width = Math.round(pixelScale.x);
  }

  public ScreenPosition(int newX, int newY, int newHeight, int newWidth)
  {
    x = newX;
    y = newY;
    height = newHeight;
    width = newWidth;
  }

  public String toString()
  {
    return "" + x + "," + y + "," + width + "," + height;
  }
}
