/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * @author NathanielEllsworth 
 * 
 * this class will maintain, update and render all of the objects in room
 * 
 * in the game will be different objects and what this will do is handle and process each of those objects.
 * So what this will do is loop through all the objects in the game and individually update them and render
 * them to the screen
 *
 */
public class Handler {
	// do not know how many objects the game will have so I need a linked list
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// need basic methods
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i); // allows to get the ID of the object and put it in this loop
			
			tempObject.tick();
		}
	}
	//loops through all of the tick objects
	
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	//loops through all of the render objects
	
	
	public void addObject(GameObject object){
		this.object.add(object); //.add adds the object to the list
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object); //.remove removes the object from the list
	}
	
	
}
