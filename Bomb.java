import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Bomb
{
	private int x;
    private int y;
	private int width;
	private int height;
	private BufferedImage bomb;
	private Fighter f;
	private Enemy e;
	private boolean visible;
	private Color myColor;
	
	public Bomb(Enemy e, Fighter f)
	{
		this.e = e;
		this.f = f;
		
		try
		{
			bomb = ImageIO.read(new File("bomb.png"));
		} 
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		visible = false;
		width = bomb.getWidth();
		height = bomb.getHeight();
	}
	
	public void reset(int x, int y, boolean visible)
	{
		this.x = x;
		this.y = y;
		this.visible = visible;
	}
	
	public void drawMe(Graphics g)
    {
        if( visible == true )
        {
            g.drawImage(bomb, x, y, null);
        }
    }
	
	public void updatePosition()
	{
		f.checkCollision(this);
		if(visible)
		{
			y++;
			if(y > 600)
			{
				visible = false;
			}
		}
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setInvisible()
	{
		visible = false;
	}
	
	public int getX()
    {
        return x;
    }
     
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
    	return width;
    }
    
    public int getHeight()
    {
    	return height;
    }
}