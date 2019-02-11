public interface ICollidable
{
  public Edge getTopEdge();
  public Edge getRightEdge();
  public Edge getBottomEdge();
  public Edge getLeftEdge();
  public Edge[] getAllEdges();
}
