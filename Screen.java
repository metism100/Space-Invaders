import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Screen extends JPanel implements KeyListener
{
    private BufferedImage buffered;
	private BufferedImage background1;
	private BufferedImage background2;
    private Projectile p1;
    private Projectile p2;
    private Fighter f;
	private Enemy e1;
	private Enemy e2;
	private Enemy e3;
	private Enemy e4;
	private Enemy e5;
	private Enemy e6;
	private Enemy e7;
	private Enemy e8;
	private Enemy e9;
	private Enemy e10;
    private int level = 1;
    private int score;
    private int enemiesLeft;
	private String status = "";
    
    public Screen()
    {
        try
		{
			background1 = ImageIO.read(new File("background1.png"));
			background2 = ImageIO.read(new File("background2.png"));
		} 
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
        f = new Fighter(350,500);
        p1 = new Projectile(100,100);
        p2 = new Projectile(100,100);
		e1 = new Enemy(50,50, f);
		e2 = new Enemy(150,50, f);
		e3 = new Enemy(250,50, f);
		e4 = new Enemy(350,50, f);
		e5 = new Enemy(450,50, f);
		e6 = new Enemy(550,50, f);
		e7 = new Enemy(650,50, f);
		e8 = new Enemy(750,50, f);
		e9 = new Enemy(850,50, f);
		e10 = new Enemy(950,50, f);
         
        setFocusable(true);
        addKeyListener(this);
    }
 
    public Dimension getPreferredSize() 
    {
        return new Dimension(800,600);
    }

   public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
     
        if( buffered == null )
        {
            buffered = (BufferedImage)(createImage( getWidth(), getHeight() ) );
        }
        
        Graphics gBuff = buffered.createGraphics();
		
		if(level == 1)
		{
			gBuff.drawImage(background1, 0, 0, null);
		}
        else
		{
			gBuff.drawImage(background2, 0, 0, null);
        }
         
        f.drawMe(gBuff);
        p1.drawMe(gBuff);
        p2.drawMe(gBuff);
		e1.drawMe(gBuff);
		e2.drawMe(gBuff);
		e3.drawMe(gBuff);
		e4.drawMe(gBuff);
		e5.drawMe(gBuff);
		e6.drawMe(gBuff);
		e7.drawMe(gBuff);
		e8.drawMe(gBuff);
		e9.drawMe(gBuff);
		e10.drawMe(gBuff);
		
        g.drawImage(buffered, 0, 0, null);
		
		Font arialFont = new Font("Arial", Font.PLAIN, 30);
        g.setFont( arialFont );
         
        Color red = new Color(255,0,0);
        g.setColor(red);
         
        g.drawString("Level: " + level, 30, 580);
		g.drawString("Lives: " + f.getLives(), 30, 530);
		g.drawString("Score: " + score, 30, 480);
		g.drawString(status, 300, 350);
		
		if(f.getLives() <= 0)
        {
           g.drawString("Score: " + score, 300, 400);
        }
    } 

	public void levelReset(int level)
	{
		this.level = level;
		int speed = 2;
		boolean othersVisible = false;
		enemiesLeft = 5;
		
		if(level == 2)
		{
			speed = 10;
			othersVisible = true;
			enemiesLeft = 10;
		}
		e1.reset(speed, true);
		e2.reset(speed, true);
		e3.reset(speed, true);
		e4.reset(speed, true);
		e5.reset(speed, true);
		e6.reset(speed, othersVisible);
		e7.reset(speed, othersVisible);
		e8.reset(speed, othersVisible);
		e9.reset(speed, othersVisible);
		e10.reset(speed, othersVisible);
	}
	
	public void gameReset()
	{
		f.reset();
		levelReset(1);
		score = 0;
	}
	
    public void updateScoreEnemies()
    {
    	score++;
    	enemiesLeft--;
    }
    
    public void animate()
    {
    	gameReset();
         
        while(true)
        {
            try 
            {
                Thread.sleep(10);
            } 
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
             
            if( p1.getY() < 0 )
            {
                p1.setVisible(false);
            }
             
            if( p2.getY() < 0 )
            {
                p2.setVisible(false);
            }
             
            if( p1.getVisible() == true )
            {
                p1.moveUp();
            }
            
            if( p2.getVisible() == true )
            {
                p2.moveUp();
            }
            
            if((e1.checkCollision(p1)) || (e1.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            if((e2.checkCollision(p1)) || (e2.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            if((e3.checkCollision(p1)) || (e3.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            if((e4.checkCollision(p1)) || (e4.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            if((e5.checkCollision(p1)) || (e5.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            if((e6.checkCollision(p1)) || (e6.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
			
			if((e7.checkCollision(p1)) || (e7.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
			
			if((e8.checkCollision(p1)) || (e8.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
			
			if((e9.checkCollision(p1)) || (e9.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
			
			if((e10.checkCollision(p1)) || (e10.checkCollision(p2)))
            {
            	updateScoreEnemies();
            }
            
            boolean doLevelReset = false;
            if((e1.checkCollision(f)) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
			
			else if(e2.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
            
            else if(e3.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
            
            else if(e4.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
            
            else if(e5.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
            
            else if(e6.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
			
			else if(e7.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
			
			else if(e8.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
			
			else if(e9.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
			
			else if(e10.checkCollision(f) && (f.getLives() > 0))
            {
            	doLevelReset = true;
            }
            
            else if(e1.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            else if(e2.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            else if(e3.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            else if(e4.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            else if(e5.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            else if(e6.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
            
			else if(e7.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
			
			else if(e8.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
			
			else if(e9.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
			
			else if(e10.updatePosition())
            {
            	doLevelReset = true;
				f.kill();
            }
			
            if(f.getLives() <= 0)
            {
				status = "You Lost!";
				askPlayAgain();
            	gameReset();
            }
            
            else if(enemiesLeft <= 0 && level == 1)
            {
            	levelReset(2);
            }
			
			else if(enemiesLeft <= 0 && level == 2)
            {
				status = "You Win!";
				askPlayAgain();
            }
 			        
		   else if(doLevelReset)
            {
            	levelReset(level);
            }

			if(f.iAmBombed())
			{
				levelReset(level);
			}  
            repaint();
        }
    }
	public boolean askPlayAgain()
	{
		repaint();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Play again?");
        System.out.println("[y] Yes");
		System.out.println("[n] No");
		String answer = keyboard.next();
		if(answer.equals("n"))
		{
			System.exit(0);
		}
		else if(answer.equals("y"))
		{
			status = "";
			gameReset();
		}
		return true;
	}
	
    public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 37)
        {
            f.moveLeft();        
        }
        if(e.getKeyCode() == 38)
        {
            f.moveUp();        
        }
		if(e.getKeyCode() == 39)
        {
            f.moveRight();   
        }
        if(e.getKeyCode() == 40)
        {
            f.moveDown();        
        }
		if(e.getKeyCode() == 32)
        {
            if( p1.getVisible() == false )
            {
                p1.moveTo( f.getX() , f.getY() );
				p1. playLaserSound();
                p1.setVisible(true);
            }
            else if( p2.getVisible() == false )
            {
                p2.moveTo( f.getX() , f.getY() );
				p2. playLaserSound();
                p2.setVisible(true);
            }
        }
        if(e.getKeyCode() == 80)
        {
            levelReset(2);   
        }
        repaint();
    }
 
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}