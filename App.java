
import processing.core.*;

import java.util.ArrayList;

import edu.nyu.cs.xz1991.assignment6Practice.Basket;

/**
 * Solution to Assignment #6
 * Basic controller for game Catching Fruits.
 * @author Xintong Zhu (xz1991)
 * @version 0.1
 */
public class App extends PApplet {
	
	// variables define
	
	// window size of this app
	private final int w = 600;
	private final int h = 600;
	
	// make constants for some common colors
	public final int WHITE = this.color(255, 255, 255); // white color
	public final int BLACK = this.color(0, 0, 0); // black color
	
	// make constants for some common spacings
	public final static int APP_MARGIN = 60; // a margin value
	public final static int NUM_APPLES = 3; // number of apples
	public final static int NUM_BOMBS_VERTICAL = 2; // number of vertical falling bombs
	public final static int APPLE_SPACING = 120; // spacing between apples
	
	// define variables
	
	// a flag to control the game running
	private boolean runOrNot = true; 
	
	// variable to hold the basket
	private Basket basket;
	
	// variable to hold the point
	private Point points;
	
	// an array to hold apples
	private ArrayList<Apple> apples = new ArrayList<Apple>();
	
	// an array to hold vertically falling bombs
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	
	// variable to hold suddenly-appear bomb
	private Bomb bombSurprise;
	
	// variable to hold flying-across bomb
	private Bomb bombFly;
	
	
	
	
	
	
	// Getter and setter
	
	/**
	 * Getter of apple ArrayList.
	 * @return An ArrayList of apples.
	 */
	public ArrayList<Apple> getApples(){
		return this.apples;
	} // getApples
	
	/**
	 * Getter of bomb ArrayList.
	 * @return An ArrayList of bombs.
	 */
	public ArrayList<Bomb> getBombs(){
		return this.bombs;
	} // getBombs
	
	/**
	 * Setter of the apples.
	 * @param apples An array list of apples.
	 */
	public void setApples(ArrayList<Apple> apples) {
		this.apples = apples;
	} // setApples
	
	/**
	 * Setter of the bombs.
	 * @param bombs An array list of bombs.
	 */
	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	} // setBombs
	
	
	
	
	
	
	
	
	/**
	 * Called once to set up windows.
	 */
	public void settings() {
		this.size(this.w, this.h);
	} // settings
	
	/**
	 * Called once on load. 
	 * Used to create the window and "global" settings.
	 */
	public void setup() {
	
		this.background(this.WHITE); // set background color
		
		// initialize basket
		this.basket = new Basket(this); // pass reference to this App object
		
		// initialize point
		this.points = new Point(this); // pass reference to this App object
		
		// initialize all apples
		int xApple = 50; // x position of first apple
		int yApple = 0; // y position of first apple
		
		// initialize all bombs
		int xBomb = 30; // x position of first bomb
		int yBomb = -2000; // y position of first bomb
		int xBombFly = -5000; // a constant y position for the fly-across bomb
		
		// loop as many times as there are apples
		for (int a = 0; a < App.NUM_APPLES; a++) {
			
			// create a new apple for each element of the array
			Apple apple = new Apple (xApple, yApple, this); // pass the x, y coordinate and a reference to this App class
			this.apples.add(apple); // add this apple to the array list
			
			// update x so the next apple we draw appears further to the right
			xApple += apple.getWidth() + App.APPLE_SPACING;
			
		} // a for loop
		
		// loop as many times as there are vertically falling bombs
		for (int b = 0; b < App.NUM_BOMBS_VERTICAL; b++) {
					
			// create a new bomb for each element of the array
			Bomb bomb = new Bomb (xBomb, yBomb, this); // pass the x, y coordinate and a reference to this App class
			this.bombs.add(bomb); // add this bomb to the array list
					
			// update x so the next bomb we draw appears further to the right
			xBomb += bomb.getWidth() + App.APPLE_SPACING;
					
		} // b for loop
		
		// initialize surprising bomb (overloaded constructor)
		this.bombSurprise = new Bomb(this); // pass reference to this app
		
		// initialize flying-across bomb (overloaded constructor)
		this.bombFly = new Bomb(xBombFly, this);
				
	} // setup
	
	/**
	 * Called repeatedly many times per second.
	 * Used to update the animation and enforce game play logic.
	 */
	public void draw() {
		
		// run the game if the point is equal to or larger than 0
		if (runOrNot) {
			// run the game
			
			// wipe the screen blank
			this.background(this.WHITE);
			
			// draw the basket
			this.basket.draw();	

			// loop through array list of apples
			for (int a = 0; a < this.apples.size(); a++) {
				Apple apple = this.apples.get(a);
				apple.move(); // move the apple
				apple.draw(); // draw the apple
			} // a for loop
			
			// loop through array list of bombs
			for (int b = 0; b < this.bombs.size(); b++) {
				Bomb bomb = this.bombs.get(b);
				bomb.moveVertical(); // move the bomb vertically falling
				bomb.draw(); // draw the bomb
			} // b for loop

			// detect if the user catches the apple
			ArrayList<Apple> applesToRemove = new ArrayList<Apple>(); // will hold the next generation of apples
			
			// use a for loop to iterate and find caught apples
			for (Apple apple: this.apples) {
				if (Basket.appleIsCaught(basket, apple)) {
					// add caught apples to the applesToRemove array list
					applesToRemove.add(apple);
				}
			} // this.apples for loop
			
			// use a for loop to 'execute' each caught apple
			for (Apple apple: applesToRemove) {
				apple.catchApple();
				// add 5 points for each caught apple
				this.points.appleEarn(apple);
			} // applesToRemove for loop
			
			// detect if the user mistakenly catches the bomb
			ArrayList<Bomb> bombsToRemove = new ArrayList<Bomb>(); // will hold the next generation of bombs
					
			// use a for loop to iterate and find caught bombs
			for (Bomb bomb: this.bombs) {
				if (Basket.bombIsCaught(basket, bomb)) {
					// add caught bombs to the bombsToRemove array list
					bombsToRemove.add(bomb);
				}
			} // this.bombs for loop
					
			// use a for loop to 'execute' each caught bomb
			for (Bomb bomb: bombsToRemove) {
				bomb.catchVerticalBomb();
				// minus 15 points for each caught bomb
				this.points.bombLose(bomb);
			} // bombsToRemove for loop
			
			// get the current second
			int currentSecond = second();
			
			// use current second to control the surprising bomb to appear suddenly and disappear after a few seconds if it is not mistakenly caught by the user
			if (currentSecond % 10 < 5) {
				// appear once during a span of time
				this.bombSurprise.draw();
				// check if this bomb is mistakenly caught
				if (Basket.bombIsCaught(this.basket, this.bombSurprise)) {
					this.bombSurprise.catchSurpriseBomb(); // change the bomb's position (to let the user know they mistakenly caught this bomb)
					// lose the points
					this.points.bombLose(this.bombSurprise);
					// reset this bomb's bombCaught
					this.bombSurprise.setBombCaughtFalse();
				} // if bombIsCaught
			} // if currentSecond
			
			// draw the flying-across bomb
			this.bombFly.moveHorizontal(); // move the bomb horizontally
			this.bombFly.draw(); // draw itself onto screen
			
			// check if this flying-across bomb is mistakenly caught
			if (Basket.bombIsCaught(this.basket, this.bombFly)) {
				// this flying-across bomb is caught
				this.bombFly.catchFlyBomb();
				// lose the points
				this.points.bombLose(this.bombFly);
			} // if bombIsCaught
			
			// draw the points
			this.points.draw();
			
			// quit the game if the point is less than 0
			int currentPoints = this.points.getPoint();
			if (currentPoints < 0) {
				// reset the flag to false to shut down the game
				runOrNot = false;
			}
			
		} // if run the game
		
		else {
			
			// wipe the screen blank
			this.background(this.BLACK);
			String loseGame = "GAME OVER!";
			String explanation = "points less than 0 :(";
			this.fill(WHITE);
			this.textSize(50);
			this.text(loseGame, 150, 280);
			this.textSize(25);
			this.text(explanation, 180, 330);
			
		} // if game loses
		
	} // draw

	
	/**
	 * Automatically called to start my program.
	 * This method calls PApplet's main method and passes it the class name of this class
	 * @param args Any command-line argument provided while running the program.
	 */
	public static void main (String[] args) {
		PApplet.main("edu.nyu.cs.xz1991.assignment6Practice.App");
	} // main
	
} // class
