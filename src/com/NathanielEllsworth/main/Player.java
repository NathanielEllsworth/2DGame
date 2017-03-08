/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Graphics;

/**
 * @author NathanielEllsworth
 *
 */
public class Player extends GameObject {

	//add constructor
	public Player(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	//since we had an abstract class we need unimplemented methods 
	//now that I made it abstract I can code within the abstract tick and render method that I created
	//in the game object class

	public void tick() {

		
	}


	public void render(Graphics g) {

		
	}

}
