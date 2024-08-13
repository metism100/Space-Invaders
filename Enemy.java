import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy
{
    private int x;
    private int y;
    private int initialX;
    private int initialY;
    private int width;
    private int height;
	private int option = 1;
	private boolean visible;
	private int speed;
	private static int count = 1;
	private int enemyNum = 0;
	private BufferedImage enemy;
	private int interval;
	private int intervalCounter; 
	private Fighter f;
	private Bomb b;
	
	public Enemy(int x, int y, Fighter f)
	{    
		try
		{
			enemy = ImageIO.read(new File("enemy.png"));
		} 
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		this.x = x;
        this.initialX = x;
        this.y = y;
        this.initialY = y;
		this.f = f;
         
        this.width = enemy.getWidth();
        this.height = enemy.getHeight();
		this.visible = false;
		this.speed = 1;
		enemyNum = count++;
		
		interval = (int)(Math.random() * 200 + 100);
		intervalCounter = 1;
		b = new Bomb(this, f);
	}
	
	public void reset(int speed, boolean visible)
	{
		this.speed = speed;
		this.visible = visible;
		b.reset(0,0,false);
		x = initialX;
		y = initialY;
		option = 1;
		intervalCounter = 1;
	}
	
	public void drawMe(Graphics g)
	{
		if(!visible)
		{
			return;
		}
		
		if( this.visible == true )
        {
			g.drawImage(enemy, x, y, null);
		}
		b.drawMe(g);
	}
	
	public void setVisible(boolean visible)
    {
        this.visible = false;
    }
	
	public boolean checkCollision(Projectile p)
    {
        int ballX = p.getX();
        int ballY = p.getY();
        int ballWidth = p.getWidth();
        int ballHeight = p.getHeight();
         
        int targetX = x;
        int targetY = y;
        int targetWidth = width;
        int targetHeight = height;
     
        if( this.visible == true && p.getVisible() == true)
        {
            if( ballX+ballWidth >= targetX && ballX <= targetX + targetWidth
                && ballY + ballHeight >= targetY && ballY <= targetY + targetHeight)
            {
                p.playScream();
				this.visible = false;
                p.setVisible( false );
                return true;
            }
        }
        return false;
    }
	
	public boolean checkCollision(Fighter f)
    {
        int ballX = f.getX();
        int ballY = f.getY();
        int ballWidth = f.getWidth();
        int ballHeight = f.getHeight();
         
        int targetX = x;
        int targetY = y;
        int targetWidth = width;
        int targetHeight = height;
     
        if( this.visible == true )
        {
            if( ballX+ballWidth >= targetX && ballX <= targetX + targetWidth
                && ballY + ballHeight >= targetY && ballY <= targetY + targetHeight)
            {
            	f.kill();
            	return true;
        	}
        }
        return false;
    }
	
	public boolean updatePosition()
	{
		boolean hitBottom = false;
		b.updatePosition();
		if ((((intervalCounter++) % interval) == 0) && visible)
		{
			if(!b.isVisible())
			{
				b.reset(x, y, true);
			}
		}
		
		switch(option)
		{
			case 1:
				x = x - speed;
				if(x < 0)
				{
					y = y + 175; 
					option = 2;
					
					if(y > 600)
					{
						visible = false;
						hitBottom = true;
					}
				}
				break;	
			
			case 2:
				x = x + speed;
				if(x > 800)
				{
					y = y + 175; 
					option = 1; 
					
					if(y > 600)
					{
						visible = false;
						hitBottom = true;
					}
				}
				break;
		}
		return hitBottom;
	}
	
	public int getX()
	{
		return x;
	}
    
	public int getY()
	{
		return y;
	}
}