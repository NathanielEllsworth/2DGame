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

	//add constructor
	public Player(int x, int y, ID id) {
		super(x, y, id);

		
	}

	//since we had an abstract class we need unimplemented methods 
	//now that I made it abstract I can code within the abstract tick and render method that I created
	//in the game object class

	public Rectangle getBounds(){
		return new Rectangle(x, y, 32, 32); //rectangle collision bounds for player (loss of health)
	}
	
	
	public void tick() {

		x += velX; //if velocity x is, say 1, it will just make x plus or equal to 1 every time
		y += velY;
		
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		//now the player can't fall out of the game's screen thanks to the above two lines
	}


	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g; // instead of new just cast it over 
		
		g.setColor(Color.green);
		g2d.draw(getBounds());
		
		
		//if(id == ID.Player) g.setColor(Color.white);
		//g.fillRect(x, y, 32, 32);
		

		
	}

}
