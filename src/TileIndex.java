public class TileIndex
{
	private int x,y;//index
	private int tile_i;
	public TileIndex(int x,int y,int tile_i)
	{
		this.x = x;
		this.y = y;
		this.tile_i = tile_i;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getIndex()
	{
		return tile_i;
	}
}