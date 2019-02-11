import java.lang.Math;

public class ScreenPosition
{
  public int x;
  public int y;
  public int height;
  public int width;

  public ScreenPosition(ObjectRect rect, float ScreenHeight)
  {
    //go from the objects center in world space
    Vector2 pixelPosition = Vector2.add(rect.position, new Vector2(-rect.scale.x / 2, rect.scale.y / 2));
    //Convert to pixel sizes
    pixelPosition = Vector2.multiply(pixelPosition, 10);
    //invert height so 0,0 is bottom left
    pixelPosition.y = ScreenHeight - pixelPosition.y;
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
