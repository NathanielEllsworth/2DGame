/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author NathanielEllsworth
 *
 *This game Object will be ALL the game objects. Such as the player object and enemy object, (or say coins in the game) they are
 *considered a game object that will be built in this class
 *
 *so say you make a player class and make it extend to this class then type x = 30; you won't get an error because it
 *extends to this class where x has already been initialized
 */
public abstract class GameObject {
	
	protected float x, y; // protected, (like public/private) can only be accessed by which object inherits the game object
	protected ID id; //because of the ID enumeration 
	protected float velX, velY; //velocity x and y will create the speed in the game in the x direction and y direction
	

	//constructor
	public GameObject (float x, float y, ID id){ //the variables above will automatically be set by the variables below
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	//void = method
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); //health drops when struck by an enemy, rectangle collision true/false algorithm 
	
	//getters and setters
	
	public void setX(int x){this.x = x;}
	
	public void setY(int y){this.y = y;}
	
	public float getX(){return x;}
	
	public float getY(){return y;}
	
	public void setId(ID id){this.id = id;}
	
	public ID getId(){return id;}
	
	public void setVelX(int velX){this.velX = velX;}
	
	public void setVelY(int velY){this.velY = velY;}
	
	public float getVelX(){return velX;}
	
	public float getVelY(){return velY;}
	
	
	
	
	
}
