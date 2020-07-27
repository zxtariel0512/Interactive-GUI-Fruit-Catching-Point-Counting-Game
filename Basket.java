
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The basket custom class.
 * @author Xintong Zhu (xz1991)
 * @version 0.1
 */
public class Basket {
	
	// variables
	
	// will hold a reference to the App object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
	PApplet app;
	
	// define the image and its path
	private final static String BASKET_IMAGE_PATH  = "src/basket.png"; //basket image file
	private PImage img; // will hold the image of this basket
	
	
	
	
	
	
	// constructor
	
	/**
	 * The constructor of basket.
	 * @param app PApplet this application.
	 */
	public Basket(PApplet app) {		

		// set up initial properties for this basket
		this.app = app; // keep a reference to the PApplet class to handle all Processing-specific functions and variables
		
		// position it on screen
		// x is the mouseX
		// y is the mouseY
		
		// load the image and store it in PImage variable
		this.img = this.app.loadImage(Basket.BASKET_IMAGE_PATH);	
		
	} // Basket
	
	
	
	
	
	
	// getter and setter
	
	/**
	 * Getter of the width of basket.
	 * @return An integer of the basket's width.
	 */
	public int getWidth() {
		return this.img.width;
	} // getWidth
	
	/**
	 * Getter of the height of basket.
	 * @return An integer of the basket's height.
	 */
	public int getHeight() {
		return this.img.height;
	} // getHeight
	
	
	
	
	
	
	// behavior
	
	/**
	 * Check whether the apple is caught by the basket.
	 * @param basket A Basket object of this basket.
	 * @param apple An Apple object of this apple.
	 * @return A boolean value of whether this apple is caught.
	 */
	public static boolean appleIsCaught(Basket basket, Apple apple) {
		boolean appleIsCaught = false;
		// check whether the apple is 'in' the basket
		// check x position
		if (apple.x >= basket.app.mouseX - basket.getWidth() / 2 && apple.x <= basket.app.mouseX + basket.getWidth() / 2) {
			// check y position
			if (apple.y >= basket.app.mouseY - basket.getHeight() / 2 && apple.y <= basket.app.mouseY + basket.getHeight() / 2) {
				// now it is caught@
				appleIsCaught = true;
			} // if y
		} // if x
		return appleIsCaught; // return the value
	} // appleIsCaught
	
	/**
	 * Check whether the bomb is caught by the basket.
	 * @param basket A Basket object of this basket.
	 * @param bomb A Bomb object of this bomb.
	 * @return A boolean value of whether this bomb is caught.
	 */
	public static boolean bombIsCaught(Basket basket, Bomb bomb) {
		boolean bombIsCaught = false;
		// check whether the bomb is 'in' the basket
		// check x position
		if (bomb.x >= basket.app.mouseX - basket.getWidth() / 2 && bomb.x <= basket.app.mouseX + basket.getWidth() / 2) {
			// check y position
			if (bomb.y >= basket.app.mouseY - basket.getHeight() / 2 && bomb.y <= basket.app.mouseY + basket.getHeight() / 2) {
				// now it is caught@
				bombIsCaught = true;
			} // if y
		} // if x
		return bombIsCaught; // return the value
	} // bombIsCaught
	
	/**
	 * draw the image using PApplet method, defining the image, x, y position.
	 */
	public void draw() {
		
		// draw the image using PApplet method
		this.app.image (this.img, this.app.mouseX - 64, this.app.mouseY - 64); // make the mouse at the center
		
	} // draw
	
	
	
} // class
