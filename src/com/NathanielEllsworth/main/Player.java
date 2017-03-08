/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author NathanielEllsworth
 *
 */
public class Player extends GameObject {

	//add constructor
	public Player(int x, int y, ID id) {
		super(x, y, id);

		velX = 1;
		
	}

	//since we had an abstract class we need unimplemented methods 
	//now that I made it abstract I can code within the abstract tick and render method that I created
	//in the game object class

	public void tick() {

		x += velX; //if velocity x is, say 1, it will just make x plus or equal to 1 every time
		y += velY;
		
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		

		
	}

}
