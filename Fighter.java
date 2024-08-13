import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
 
public class Fighter
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color myColor;
    private int lives;
	private BufferedImage fighter;
	private boolean bombed = false;
     
    public Fighter(int x, int y)
    {
        try
		{
			fighter = ImageIO.read(new File("fighter.png"));
		} 
		catch(IOException ex)
		{
			ex.printStackTrace();
		} 
        
		this.x = x;
        this.y = 491;
         
        this.width = fighter.getWidth();
        this.height = fighter.getHeight();
        
        this.lives = 0;
    }
   
    public void reset()
    {
    	lives = 3;
		bombed = false;
    }
     
    public int getLives()
    {
    	return lives;	
    }
    
    public void kill()
    {
    	if(lives > 0)
    	{
    		lives--;
    	}
    }
    
    public void drawMe(Graphics g)
    {
        g.drawImage(fighter, x, y, null);
    }
    
	public boolean iAmBombed()
	{
		boolean retValue = bombed;
		bombed = false;
		return retValue;
	}
	
	public boolean checkCollision(Bomb b)
	{	
		int bombX = b.getX();
        int bombY = b.getY();
        int bombWidth = b.getWidth();
        int bombHeight = b.getHeight();
         
        int fighterX = x;
        int fighterY = y;
        int fighterWidth = width;
        int fighterHeight = height;
     
        if( b.isVisible() )
        {
            if( bombX + bombWidth >= fighterX && bombX <= fighterX + fighterWidth
                && bombY + bombHeight >= fighterY && bombY <= fighterY + fighterHeight)
            {
            	kill();
				b.setInvisible();
				bombed = true;
            	return true;
        	}
        }
        return false;
    }
	
    public void moveLeft()
    {
        x = x - 5;
    }
    
	 public void moveUp()
    {
        if(y > 0)
		{
			y = y - 5;
		}
    }
	
    public void moveRight()
    {
        x = x + 5;
    }
	
	 public void moveDown()
    {
		if(y < 500)
		{
			y = y + 5;
		}
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