import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.*;

public class main extends Applet 
{

	
	 static Color chopStickColor = new Color(61,8,8);
	 static int chopsticks = 5;
	 static int philosophersNumber = 5;
	 Point location;
	 Rectangle location2;
	 chopstick stick;
	 philosopher drawPHL;
	
	 chopstick[] chopstickArray = new chopstick[chopsticks];
	philosopher[] philosophers = new philosopher[philosophersNumber];

	public void init()
	{				
		setBackground (Color.black);
		setSize(500,500);
		setVisible(true);

		

		
		for (int i=0; i<chopsticks; i++)
		{
			switch(i)
			{
					case 0:  location2 = new Rectangle(100,75,20,20); break;
					case 1:  location2 = new Rectangle(100,125,20,20); break;
					case 2:  location2 = new Rectangle(100,25,20,20); break;
					case 3:  location2 = new Rectangle(100,175,20,20); break;
					case 4:  location2 = new Rectangle(100,225,20,20); break;
			}	
			stick = new chopstick(location2);
			chopstickArray[i] = stick;
			
			
		}
		
		for (int i=0; i<philosophersNumber; i++)
		{
			switch (i)
		      {
		      	case 0:  location = new Point(200,75); break;
		      	case 1:  location = new Point(200,125); break;
		      	case 2:  location = new Point(200,25); break;
		      	case 3:  location = new Point(200,175); break;
		      	case 4:  location = new Point(200,225); break;
		      	default: System.out.println("NO PHL"); break;
		      }
			if (i != 0)
			{
				drawPHL = new philosopher(chopstickArray[i],chopstickArray[(i+1)%chopsticks], i+"", location, this);
			}
			else
				drawPHL = new philosopher(chopstickArray[(i+1)%chopsticks], chopstickArray[i], i+"", location, this);
			
			philosophers[i] = drawPHL;			
			new Thread(philosophers[i]).start();
		}
	
		
		
	
		
	}
	
	

	
	public void paint(Graphics g)
	{			
		for (int i = 0; i < chopsticks; i++){chopstickArray[i].draw(g);}
		for (int i = 0; i < philosophersNumber; i++){philosophers[i].draw(g);}
		
	}


	
	 public static void fatLine(Graphics page, Rectangle r, Dimension d)
	   { int[] x = {r.x, r.x+d.width, r.x+r.width+d.width, r.x+r.width}; 
	     int[] y = {r.y, r.y, r.y+r.height+d.width, r.y+r.height+d.width};
	     page.fillPolygon(x, y, 4);
	   }


	 
}
