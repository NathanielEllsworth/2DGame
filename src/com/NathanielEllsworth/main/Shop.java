/**
 * 
 */
package com.NathanielEllsworth.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author NathanielEllsworth
 * 
 * in shop you will be able to upgrade your health, upgrade your speed and upgrade your health all the way
 *
 */
public class Shop extends MouseAdapter{
	//Mouse Adapter allows you to add a mouse lister onto this class
	//because I'm going to add a mouse listener onto the Shop class, It's going to look for MouseEvent (mousePressed method) every time you click the mouse 
	
	Handler handler;// using the handler to change things that are pressed
	
	public Shop(Handler handler){
		this.handler = handler; 
	}
	
	public void render(Graphics g){
		
	}
	
	public void mousePressed(MouseEvent e){
		
	}

}
