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
	
	public int bounds = 0;
	public static float HEALTH = 100; //instead of having to initialize the instance of the HUD class, I can now just say HUD.HEALTH
	// I don't have to initialize it, I don't have to say HUD = new HUD, just makes things easier and it can be static since
	//this will be the only instance HEALTH will be needed by an object (the player)
	
	private float greenValue = 255f; //r.g.b. (red green blue) color runs the full spectrum of colors at 255
	//100% green (255) will transition into red (75)
	
	private int funds = 0;
	
	private int level = 1;
	
	
	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2)); // clamps the health inside the health bar, the health also won't be able to go above 100 (max)
		// bounds is divided by 2 because in the fillRect the health is multiplied by 2
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue, 0, 255); // clamp the green to transition into red when low
		
		
		
		funds++; //makes the score go up, level changes every 1,000 points
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int)greenValue, 0)); //red is 75, green is greenValue, blue is 0 (r.g.b.)
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32); //just puts a boarder around the health bar
		
		
		//drawing out the score and level displays
		g.drawString("Funds: " + funds, 17, 64); // x variable is 17, y variable is 64
		g.drawString("Level: " + level, 17, 80);// x variable is 17, y variable is 80
		g.drawString("Space for Shop", 17, 94);
		
		
	}
	
	public void setFunds(int funds){
		this.funds = funds;
	}
	
	public int getFunds(){
		return funds;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	

}
