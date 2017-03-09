/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author NathanielEllsworth 
 * 
 * Heads-Up-Display class
 *
 */
public class HUD {
	
	public static int HEALTH = 100; //instead of having to initialize the instance of the HUD class, I can now just say HUD.HEALTH
	// I don't have to initialize it, I don't have to say HUD = new HUD, just makes things easier and it can be static since
	//this will be the only instance HEALTH will be needed by an object (the player)
	
	private int greenValue = 255; //r.g.b. (red green blue) color runs the full spectrum of colors at 255
	//100% green (255) will transition into red (75)
	
	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 100); // clamps the health inside the health bar, the health also won't be able to go above 100 (max)
		greenValue = Game.clamp(greenValue, 0, 255); // clamp the green to transition into red when low
		
		greenValue = HEALTH*2;
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0)); //red is 75, green is greenValue, blue is 0 (r.g.b.)
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32); //just puts a boarder around the health bar
	}

}
