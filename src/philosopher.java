import java.awt.*;




public class philosopher  extends Thread implements Runnable 
{

	Color faceColor = Color.GRAY;
	Color noseColor = Color.BLACK;
	Color eyeColor = Color.blue;
	Color mouthColor = Color.red;
	Color pupilColor = Color.green;
	Color chopStickColor = new Color(61,8,8);
	main paint;
	int available;
	chopstick chopstick = new chopstick();
	
    int statusNumber = 0;
	int SIZE = 40;
	private Point location;
	private String status = "THINK";
	
	private final chopstick left;
	private final chopstick right;
	private int id;
	
	public philosopher (chopstick l, chopstick r, String id, Point location, main painting)
	{
		this.left=l;
		this.right=r;
		this.id=Integer.parseInt(id);
		this.location=location;
		this.paint = painting;
	}
	
	public  void start()
	{
		Thread thread = new Thread (this);
		thread.start();
	}
	
	public void update()
	{

		paint.repaint();		
	}
	
	public void run()
	{
		int tries = 0;
		while (true)
		{
			try
				{
				double wait = (Math.random() * 8000) + 3000;	
				
				Thread.sleep((long) wait);					
				
				if (statusNumber != 0 )					
				{				
					if (left.pickUp(id) || left.owner == id) 
						{
						System.out.println("in left");
						left.available=0;
					
					if (right.pickUp(id) || right.owner == id) 
						{
						right.available=0;

						
							System.out.println("eating" + id);
							statusNumber = 2;
							update();
							Thread.sleep((long) wait);
							update();
							left.putDown();
							right.putDown();
							statusNumber = -1;
													
						
					}
						}
						else
						{
							
							Thread.sleep((long) wait);
						}
					}
					
				
				
			
			if (statusNumber == 1) statusNumber += 2;
			else if (statusNumber == 3 && tries < 6) tries++;
			else
				statusNumber+=1;
			
			update();
			
			if (statusNumber == 4)
			{ 
				left.putDown();
				right.putDown(); 
			     break;
			}
			
		}			
		catch (InterruptedException e) {System.out.println("exception");}
	}
	
		update();

}
	
	
	public void draw(Graphics page)
	   {  		
		switch (statusNumber)
		{
			case 0: status = "THINK"; break;
			case 1: status = "HUNGRY"; break;
			case 2: status = "EAT"; break;
			case 3: status = "FAMISHED"; break;
			case 4: status = "STARVE"; break;
		}
		
		   
		   int       startX, startY, height, width;
	      Rectangle spot;
	     
	  
	// Draw face.

	      page.setColor(faceColor);      
	      page.fillOval(location.x, location.y, SIZE, SIZE );

	// Draw eyes.

	      startX = location.x + SIZE/5; 
	      startY = location.y + SIZE/5;
	      width  = SIZE/5;
	      height = SIZE/5;
	      page.setColor(eyeColor);   
	      page.fillOval(startX, startY, width, height );
	      startX = location.x + 3*SIZE/5; 
	      page.fillOval(startX, startY, width, height);

	// Draw nose.

	      startX = location.x + SIZE/2-3; 
	      startY = location.y + SIZE/5+5;
	      height = 10;                    
	      width  = 6; 
	      page.setColor(noseColor);       
	      page.fillRoundRect(startX, startY,width,height,3,3);

	// Draw mouth.

	      startX = location.x + SIZE/5;
	      startY = location.y + 3 * SIZE /5;
	      page.setColor(mouthColor);
	      height = 3;
	      if (status == "HUNGRY") height = 5;
	      if (status == "FAMISHED") height = 8;  
	      page.fillRoundRect(startX, startY, 3*SIZE/5, height, 3, 3); 

	      switch (status)
	      {  case "EAT":
	            page.setColor(main.chopStickColor);
	            startX = location.x + 2*SIZE/5;
	            startY = location.y + 7*SIZE/10;
	            width  = -10;
	            height = 30;
	            spot   = new Rectangle(startX,startY,width,height);
	            main.fatLine(page,spot,new Dimension(3,3));
	            startX = location.x + 3*SIZE /5;
	            width  = 10;
	            height = 30;
	            spot   = new Rectangle(startX,startY,width,height);
	            main.fatLine(page,spot,new Dimension(3,3)); 
	         case "THINK":
	            startX = location.x + SIZE/4;   // Draw pupils.
	            startY = location.y + SIZE/4;
	            width  = SIZE/10;
	            height = SIZE/10;
	            page.setColor(pupilColor);   
	            page.fillOval(startX, startY, width, height );
	            startX = location.x + 2*SIZE/5+SIZE/4;
	            page.fillOval(startX, startY, width, height );
	            break;
	         case "STARVE":
	            startX = location.x + SIZE/5;   // Draw x for pupils.
	            startY = location.y + SIZE/5;
	            width  = SIZE/5;
	            height = SIZE/5;
	            page.setColor(pupilColor);   
	            page.drawLine(startX,startY,startX+width,startY+height);
	            page.drawLine(startX+width,startY,startX,startY+height);
	            startX = location.x + 3*SIZE/5; 
	            page.drawLine(startX,startY,startX+width,startY+height);
	            page.drawLine(startX+width,startY,startX,startY+height);
	            break;
	         case "HUNGRY":
	            startX = location.x + SIZE/4;   // Draw pupils.
	            startY = location.y + 3*SIZE/10;
	            width  = SIZE/7;
	            height = SIZE/7;
	            page.setColor(pupilColor);   
	            page.fillOval(startX, startY, width, height );
	            startX = location.x + 2*SIZE/5+SIZE/4;
	            page.fillOval(startX, startY, width, height );
	            break;
	         case "FAMISHED":
	            startX = location.x + 5*SIZE/16;   // Draw pupils.
	            startY = location.y + 4*SIZE/24;
	            width  = SIZE/7;
	            height = SIZE/7;
	            page.setColor(pupilColor);   
	            page.fillOval(startX, startY, width, height );
	            startX = location.x + 6*SIZE/16+SIZE/4;
	            page.fillOval(startX, startY, width, height );
	            break;
	         default:
	            System.out.println("Illegal philosopher status");
	            System.exit(0);
	      }
	   }


	  

}
