import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Projectile
{
    private int x;
    private int y;
     
    private int width;
    private int height;
     
    private boolean visible;
	private BufferedImage projectile;
 
    public Projectile(int x, int y)
    {
        try
		{
			projectile = ImageIO.read(new File("projectile.png"));
		} 
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		this.x = x;
        this.y = y;
         
        this.width = 10;
        this.height = 10;
         
        this.visible = false;   
    }
    
    public void drawMe(Graphics g)
    {
        if( visible == true )
        {
            g.drawImage(projectile, x, y, null);
        }
    }
     
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
     
    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
     
    public void moveUp()
    {
        y = y - 5;
    }
     
    public boolean getVisible()
    {
        return visible;
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
	
	public void playLaserSound()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("laser.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch( Exception ex )
        {
            ex.printStackTrace(System.out);
        }
    } 

	public void playScream()
    {
        try
        {
            URL url = this.getClass().getClassLoader().getResource("scream.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        }
        catch( Exception ex )
        {
            ex.printStackTrace(System.out);
        }
    }
}