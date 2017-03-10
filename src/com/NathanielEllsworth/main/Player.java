/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 * @author NathanielEllsworth
 *
 */
public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;

	//add constructor
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	//since we had an abstract class we need unimplemented methods 
	//now that I made it abstract I can code within the abstract tick and render method that I created
	//in the game object class

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y, 32, 32); //rectangle collision bounds for player (loss of health)
	}
	
	
	public void tick() {

		x += velX; //if velocity x is, say 1, it will just make x plus or equal to 1 every time
		y += velY;
		
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		//now the player can't fall out of the game's screen thanks to the above two lines
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
		//player's trail
		
		collision();
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){//basically what this says is if our tempObject is our BasicEnemy, then this code below this point deals with BasicEnemy
				if(getBounds().intersects(tempObject.getBounds())){//if the bounds of the enemy intersects, lower the health by 2
					//collision code - anything that happens in here will collide with it
					HUD.HEALTH -=2; // this code dictates the amount the health bar goes down when the player comes in contact with an enemy
					
					
					
				}
				
				
				
			}
			
			
			
		}
	}


	public void render(Graphics g) {
		
		//Graphics2D g2d = (Graphics2D) g; // instead of new just cast it over 
		
		//g.setColor(Color.green);
		//g2d.draw(getBounds());
		//above not necessary but will help conceptualize (show) the bounds of the collision bounds of the player
		
		//if(id == ID.Player) 
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		

		
	}

}
