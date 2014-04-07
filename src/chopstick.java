import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;



public class chopstick
{	
	
	int available = 1; //-1 = is avaliable
	Rectangle location;//ocation variable is an instance of a Rectangle object (startX, startY, dX, dY). Note that the ending X and Y values is  startX+dX and startY+dY respectively.
	private volatile boolean taken = false;
	int owner = 100;
	
	public chopstick (Rectangle location)
	{
		this.location=location;
	}
	
	public chopstick() 
	{
		
	}

	public synchronized boolean pickUp(int id)
	{
		if (!taken)
		{
			taken = true;
			owner=id;
			return true;
		}
		else 
			return false;
	}
	
	  public  void putDown()
	  {
		owner=100;
	        taken = false;
	        available=1;
	  }
	
	
	  
	  
	 public void draw(Graphics page)
	   {  	 
		 if (available!=0) 
	      {  
			page.setColor(main.chopStickColor);
	      		main.fatLine(page, location, new Dimension(5,5));
	      } 
	   }
}
