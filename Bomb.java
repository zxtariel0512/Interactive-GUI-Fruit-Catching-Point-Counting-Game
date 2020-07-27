package edu.nyu.cs.xz1991.assignment6Practice;

import processing.core.PImage;
import java.util.Random;
import processing.core.PApplet;

/**
 * Custom class of bomb.
 * @author Xintong Zhu (xz1991)
 * @version 0.1
 */
public class Bomb{
	
	// variables define
	
	// hold a reference to the App class which inherits the PApplet class and thus handles all processing-specific staff
	private App app;
	
	// define the image and its path
	private final static String BOMB_IMAGE_PATH = "src/bomb.png";
	private final static String BOMB_SURPRISE_IMAGE_PATH = "src/bomb_surprise.png";
	private final static String BOMB_FLY_IMAGE_PATH = "src/bomb_fly.png";
	private PImage img; // hold the image to use for bomb
	
	private final static int MAX_SPEED = 9; // maximum speed bomb will move
	
	private final static int FLY_SPEED = 18; // 'fly' speed of a horizontal moving bomb
	
	private final static int MAX_POSITION = 500; // maximum position of both x and y
	
	private boolean bombCaught = false; // flag to hold the uncaught bomb
	
	public int x, y; // x, y position
	public int speedX, speedY; // speed in x direction, y direction
	
	
	
	
	
	
	
	// constructor
	
	/**
	 * Constructor of bomb (vertically falling).
	 * @param x An integer of x position.
	 * @param y An integer of y position.
	 * @param app A PApplet object app.
	 */
	public Bomb (int x, int y, PApplet app) {
		
		// keep a reference to the PApplet class to handle all processing-specific functions and variables
		this.app = (App) app;
		
		// set up the initial properties of this bomb
		
		// set position
		this.x = x;
		this.y = y;
		
		// set random speeds
		this.speedY = this.getRandomSpeed();
		
		// load the bomb image and store it in the PIamge variable
		this.img = app.loadImage(Bomb.BOMB_IMAGE_PATH);
		
	} // Bomb
	
	/**
	 * Overloaded Constructor of bomb (suddenly appearing).
	 * @param app A PApplet object of app.
	 */
	public Bomb (PApplet app) {
		
		// keep a reference to the PApplet class to handle all processing-specific functions and variables
		this.app = (App) app;
		
		// set up initial properties
		
		// choose random x, y positions
		this.x = this.getRandomPosition();
		this.y = this.getRandomPosition();
		
		// load the bomb image and store it to the PImage variable
		this.img = app.loadImage(Bomb.BOMB_SURPRISE_IMAGE_PATH);
		
	} // Bomb
	
	/**
	 * Overloaded constructor of bomb (horizontal 'flying'.
	 * @param x An integer of x position only.
	 * @param app A PApplet object app.
	 */
	public Bomb (int x, PApplet app) {
		
		// keep a reference to the PApplet class to handle all processing-specific functions and variables
		this.app = (App) app;
		
		// set up initial properties
		this.x = x;
		this.y = this.getRandomPosition();
		
		// load the bomb image and store it to the PImage variable
		this.img = app.loadImage(Bomb.BOMB_FLY_IMAGE_PATH);
		
	} // Bomb
	
	
	
	
	
	
	// Getter and setter
	
	/**
	 * Get a random integer between 1 to 7 for the speed of this bomb.
	 * @return An integer between 1 and 7.
	 */
	public int getRandomSpeed() {
		Random random = new Random(); // create the Random object
		int randomSpeed = random.nextInt(MAX_SPEED) + 1; // get a random number between 1 and 7
		return randomSpeed; // return the number
	} // getRandomSpeed
	
	/**
	 * Get a random number between 30 and 530 for the position of this bomb.
	 * @return An integer of this bomb's random position.
	 */
	public int getRandomPosition() {
		Random random = new Random(); // create the Random object
		int randomPosition = random.nextInt(MAX_POSITION) + 30; // get a random number between 30 and 530
		return randomPosition; // return the number
	} // getRandomPosition
	
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
	 * Getter of the bombCaught.
	 * @return A boolean value of whether this bomb is caught or not.
	 */
	public boolean getBombCaught() {
		return this.bombCaught;
	} // getBombCaught
	
	/**
	 * Setter of speedY.
	 * Sets by how much this apple moves in y direction per frame.
	 * @param speedY An integer of speedY to be set.
	 */
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	} // setSpeedY
	
	/**
	 * Setter of speedX.
	 * @param speedX An integer of speedX to be set.
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	} // setSpeedX
	
	/**
	 * Setter of y position.
	 * @param newY An integer of new y position to be set.
	 */
	public void setPositionY (int newY) {
		this.y = newY;
	} // setPosition
	
	/**
	 * Setter of x position.
	 * @param newX An integer of new x position to be set.
	 */
	public void setPositionX (int newX) {
		this.x = newX;
	} // setPositionX
	
	/**
	 * Reset this bomb's bombCaught to false.
	 */
	public void setBombCaughtFalse() {
		this.bombCaught = false;
	} // setBombCaughtFalse
	
	
	
	
	
	
	// behavior
	
	/**
	 * Catch this vertical falling bomb.
	 * Simply change the y position of the caught bomb back to the top again.
	 * Change this bomb's bombCaught to true for points reducing.
	 */
	public void catchVerticalBomb() {
		// reset this apple's y position (negative in order to create some time between the two 'same' apples falling
		this.y = -2000;
		// pick a random position for x
		Random randX = new Random();
		this.x = randX.nextInt(400) + 100;
		// set the bombCaught to be true
		this.bombCaught = true;
	} // catchVerticalApple
	
	/**
	 * Catch this surprise bomb.
	 * Simply set this bombCaught to be true for points lose.
	 */
	public void catchSurpriseBomb() {
		// set the bombCaught to be true
		this.bombCaught = true;
		// change the bomb's position
		this.x = getRandomPosition();
		this.y = getRandomPosition();
	} // catchSurpriseBomb
	
	/**
	 * Catch this flying-across bomb.
	 * Simply set this bombCaught to be true for points lose.
	 */
	public void catchFlyBomb() {
		// set the bombCaught to be true
		this.bombCaught = true;
		// change the bomb's position
		this.y = getRandomPosition();
		this.x = -5000;
	} // catchFlyBomb
	
	/**
	 * Draw the bomb.
	 * Each bomb draws itself in its own way.
	 */
	public void draw() {		
		// draw the image using PApplet method
		this.app.image(this.img, this.x, this.y);
	} // draw
	
	/**
	 * Move this bomb to fall down vertically.
	 */
	public void moveVertical() {
		
		int speedY = this.getRandomSpeed(); // new speed in y direction
		
		this.setSpeedY(speedY); // update internal speed info
		
		int newY = this.y + speedY; // calculate new y position
		
		this.setPositionY(newY); // update internal position info
		
		// keep the bombs falling by changing its y position back to the top again, creating a scene of bombs keep falling down
		if (this.y > 600) {
			this.y = 30;
			// pick a random position for x
			Random randX = new Random();
			this.x = randX.nextInt(400) + 100;
			// reset the bombCaught for this 'new' falling bomb
			this.bombCaught = false;
		}
		
	} // moveVertical
	
	/**
	 * Move this bomb fly across horizontally.
	 */
	public void moveHorizontal() {
		
		this.setSpeedX(FLY_SPEED); // set the speed as the FLY_SPEED
		
		int newX = this.x + speedX; // calculate the new x position
		
		this.setPositionX(newX); // update internal position info
		
		// keep the bomb flying by changing its x position back to the left again, creating a scene of bomb keeps flying across
		if (this.x > 600) {
			this.x = -5000; // move it to the left
			this.y = getRandomPosition(); // re-pick a random y position
			// reset the bombCaught for this 'new' flying bomb
			this.bombCaught = false;
		}
		
	} // moveHorizontal
	
} // class
