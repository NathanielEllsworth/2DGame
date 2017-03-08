/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author NathanielEllsworth
 *
 */
public class KeyInput extends KeyAdapter{ 
	//inside the key adapter has key pressed and key released functions 
	//like the getters and setters inside the game object class
	
	private Handler handler;//*this*
	
	public KeyInput(Handler handler){
		this.handler = handler; //basically whatever I put into the constructor of the handler will equal *this* (the above)
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode(); //sets that key to a letter, binding the two together
		//so when you press a key on the keyboard it will display the number value that corresponds to the key value pressed
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i); //temp object is now like every object in the game
			
			if(tempObject.getId() == ID.Player){
				// in here will be all the key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				
				//so basically what this is saying is 
				//"if the object has the ID of Player, then check if the keyboard key W is being hit, then pretend 
				//temoObject is ID.Player because that's what it is.
			}
			
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); //you can push escape and the game will end
	}
	
	public void keyReleased(KeyEvent e){ //so for the key release the keys need to stop so velocity is 0
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i); 
			
			if(tempObject.getId() == ID.Player){
				// in here will be all the key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				
			}
			
		}
		
	}

}
