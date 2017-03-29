/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.NathanielEllsworth.main.Game.STATE;

/**
 * @author NathanielEllsworth
 *
 */
public class KeyInput extends KeyAdapter{ 
	//inside the key adapter has key pressed and key released functions 
	//like the getters and setters inside the game object class
	
	private Handler handler;//*this*
	private boolean[] keyDown = new boolean[4]; //fixing the 'sticky' keyboard keys when the player is moving 
	
	Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler; //basically whatever I put into the constructor of the handler will equal *this* (the above)
		
		this.game = game;
		
		keyDown[0]=false; //key W     \
		keyDown[1]=false; //key S      \ Basically these boolean values are asking "are the keys pressed? yes or no."
		keyDown[2]=false; //key D      /
		keyDown[3]=false; //key A     /
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode(); //sets that key to a letter, binding the two together
		//so when you press a key on the keyboard it will display the number value that corresponds to the key value pressed
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i); //temp object is now like every object in the game
			
			if(tempObject.getId() == ID.Player){
				// in here will be all the key events for player 1
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.spd); keyDown[0]=true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.spd); keyDown[1]=true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.spd); keyDown[2]=true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.spd); keyDown[3]=true; }
				
				//so basically what this is saying is 
				//"if the object has the ID of Player, then check if the keyboard key W is being hit, then pretend 
				//temoObject is ID.Player because that's what it is.
			}
			
		}
		if(key == KeyEvent.VK_P)
		{
			if(game.gameState == STATE.Game)
			{
				if(Game.paused) Game.paused = false; //if the game is already paused, then paused equals false
				else Game.paused = true;
			}
			
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); //you can push escape and the game will end
		if(key == KeyEvent.VK_SPACE){ //this will only be activated if in the game or in the shop
			if(Game.gameState == STATE.Game) Game.gameState = STATE.Shop;//if space bar is pressed and in game, player will switch to shop
			else if(Game.gameState == STATE.Shop) Game.gameState = STATE.Game; //if space bar is pressed and in shop, player will switch back to game
		}
	}
	
	public void keyReleased(KeyEvent e){ //so for the key release the keys need to stop so velocity is 0
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i ++){
			GameObject tempObject = handler.object.get(i); 
			
			if(tempObject.getId() == ID.Player){
				// in here will be all the key events for player 1
				
				if(key == KeyEvent.VK_W) keyDown[0]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1]=false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2]=false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3]=false; //tempObject.setVelX(0);
				
				//for the vertical movement (y axis)  
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//for the horizontal movement (x axis)  
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}
			
		}
		
	}

}
