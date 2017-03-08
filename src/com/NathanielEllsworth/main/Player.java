/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Graphics;
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

	public void tick() {

		x += velX; //if velocity x is, say 1, it will just make x plus or equal to 1 every time
		y += velY;
		
	}


	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
		

		
	}

}
