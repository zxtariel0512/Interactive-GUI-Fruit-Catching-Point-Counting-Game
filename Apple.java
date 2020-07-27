package edu.nyu.cs.xz1991.assignment6Practice;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * A custom class of apples.
 * @author Xintong Zhu (xz1991)
 * @version 0.1
 */
public class Apple {
	
	// variables define

	// will hold a reference to the App object, which inherits from PApplet and therefore handles all processing stuff
	private App app;
	
	// define the image and its path
	private final static String APPLE_IMAGE_PATH = "src/apple.png";
	private PImage img; // will hold the image to use for apple
	
	private final static int MAX_SPEED = 4; //maximum speed the apple would fall in vertical direction
	
	private boolean appleCaught = false; // flag to hold uncaught apples
	
	public int x, y; // position
	public int speedY; // speed in vertical direction
	
	
	
	
	
	
	// constructor
	
	/**
	 * The constructor of apple.
	 * @param x An integer of x coordinate of the apple.
	 * @param y An integer of y coordinate of the apple.
	 * @param app The PApplet object of app.
	 */
	public Apple(int x, int y, PApplet app) {
		
		// set up initial properties of this apple
		this.app = (App) app; // keep a reference to the PApplet class to handle all Processing-specific functions and variables
		
		// set position
		this.x = x; // x position
		this.y = y; // y position
		
		// set random speeds
		this.speedY = this.getRandomSpeed();
		
		// load the apple image and store it in the PIamge variable
		this.img = app.loadImage(Apple.APPLE_IMAGE_PATH);		
		
	} // Apple
	
	
	
	
	
	// getter and setter
	
	/**
	 * Get a random integer between 1 to 5 for the speed of this apple.
	 * @return An integer between 1 and 5.
	 */
	public int getRandomSpeed() {
		Random random = new Random(); // create the Random object
		int randomSpeed = random.nextInt(MAX_SPEED) + 1; // get a random number between 1 and 5
		return randomSpeed; // return the number
	} // getRandomSpeed
	
	/**
	 * Getter of the width of the image.
	 * @return An integer of the width of the image.
	 */
	public int getWidth() {
		return this.img.width;
	} // getWidth
	
	/**
	 * Getter of the height of the image.
	 * @return An integer of the height of the image.
	 */
	public int getHeight() {
		return this.img.height;
	} // getHeight
	
	/**
	 * Getter of the appleCaught.
	 * @return A boolean value of whether this apple is caught or not.
	 */
	public boolean getAppleCaught() {
		return this.appleCaught;
	} // getAppleCaught
	
	/**
	 * Setter of speedX.
	 * Sets by how much this apple moves in y direction per frame.
	 * @param speedX An integer of speedX to be set.
	 */
	public void setSpeed(int speedY) {
		this.speedY = speedY;
	} // setSpeed
	
	/**
	 * Setter of y position.
	 * @param newY An integer of new y position to be set.
	 */
	public void setPosition (int newY) {
		this.y = newY;
	} // setPosition
	
	
	
	
	
	// behavior
	
	/**
	 * Catch this apple.
	 * Simply change the y position of the caught apple back to the top again.
	 * Change this apple's appleCaught to true for points earning.
	 */
	public void catchApple() {
		// reset this apple's y position (negative in order to create some time between the two 'same' apples falling
		this.y = -200;
		// pick a random position for x
		Random randX = new Random();
		this.x = randX.nextInt(400) + 100;
		// set the appleCaught to be true
		this.appleCaught = true;
	} // catchApple
	
	/**
	 * Draw the apple.
	 * Each apple draws itself in its own way.
	 */
	public void draw() {		
		// draw the image using PApplet method
		this.app.image(this.img, this.x, this.y);
	} // draw
	
	/**
	 * Move this apple to fall down vertically.
	 */
	public void move() {
		
		int speedX = this.getRandomSpeed(); // new speed in y direction
		
		this.setSpeed(speedX); // update internal speed info
		
		int newY = this.y + speedY; // calculate new y position
		
		this.setPosition(newY); // update internal position info
		
		// keep the apples falling by changing its y position back to the top again, creating a scene of apples keep falling down
		if (this.y > 600) {
			this.y = 30;
			// reset the appleCaught for this 'new' falling apple
			this.appleCaught = false;
		}
		
	} // move

} // class
