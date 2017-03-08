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
public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);

		velX = 5;
		velY = 5;
		
	}

	public void tick() {

		x += velX;
		y += velY;
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; // start:   velY = -5    Z = velY x -1     Z = -5 x -1 = 5    velY = 5   :finish (continuously looping)
		//when the basic enemy reaches the top of the screen, it's reversing whatever its velocity Y was  
		//so if z is what you want to create, you take velocity Y and multiply it by negative one
		//so if y is -5 that means it's going upward then by multiplying it by negative one 
		//two negatives equal a positive so the velocity changes to a positive making the object 
		//travel downwards at the same rate because 5 never changed which is the velocity
		// + and - are the directions (up and down)
		
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	}

	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
	}

}
