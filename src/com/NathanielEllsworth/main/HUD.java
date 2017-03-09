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
	
	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 100); // clamps the health inside the health bar, the health also won't be able to go above 100 (max)
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32); //just puts a boarder around the health bar
	}

}
