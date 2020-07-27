
import processing.core.*;

/**
 * Custom class for points.
 * @author Xintong Zhu (xz1991)
 * @version 0.1
 */
public class Point {

	// will hold reference to the App object, which inherits from PApplet and therefore handles all processing-specific staff
	private App app;
	
	// define the points
	private int points = -5;
	
	// fix its position at the bottom middle of the screen
	public int x = 270; // x position
	public int y = 570; // y position
	
	/**
	 * Constructor of points.
	 * @param app A PApplet object app.
	 * @param apple An apple object apple.
	 */
	public Point(PApplet app) {		
		// keep a reference to the PApplet class to handle processing-specific functions and variables
		this.app = (App) app;		
		// determine the text size
		this.app.textSize(50); 
	} // Point
	
	/**
	 * Add 5 points if this apple is caught.
	 */
	public void appleEarn(Apple apple) {
		boolean checkAppleEarn = apple.getAppleCaught();
		if (checkAppleEarn) {
			// add 5 points for each apple catch
			this.points += 5;
		}
	} // appleEarn
	
	/**
	 * Minus 10 points if this bomb is mistakenly caught.
	 */
	public void bombLose(Bomb bomb) {
		boolean checkBombEarn = bomb.getBombCaught();
		if (checkBombEarn) {
			// minus 10 points for each bomb catch
			this.points -= 10;
		}
	} // appleEarn
	
	/**
	 * Getter of this points.
	 * @return An integer of the points.
	 */
	public int getPoint() {
		return this.points;
	} // getPoint
	
	/**
	 * Draw the points onto screen.
	 */
	public void draw() {
		this.app.fill(0, 0, 0); // print it in black
		this.app.text(points, x, y); // draw out the text
	} // draw
	
} // class
