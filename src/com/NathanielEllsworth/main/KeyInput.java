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
				
				if(key == KeyEvent.VK_W) tempObject.setY(tempObject.getY() - 1);
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
	}

}
