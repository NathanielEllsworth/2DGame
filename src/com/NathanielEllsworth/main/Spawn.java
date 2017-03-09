/**
 * 
 */
package com.NathanielEllsworth.main;

/**
 * @author NathanielEllsworth
 *
 */
public class Spawn {
	
	private Handler handler;
	private HUD hud;
	
	
	
	public Spawn(Handler handler, HUD hud){
		
		this.handler = handler;
		this.hud = hud; // can make h.u.d. static but static variables can be kind of if'y, so it's better to make 
		//the variables in the h.u.d class private and just import it into the class
		
		
	}
	
	public void tick(){
		
	}
	
}
