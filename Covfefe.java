package GradysRobots;
import robocode.*;
import java.awt.Color;

 public class Covfefe extends AdvancedRobot {
	 boolean notBroken = true;
     public void run() {
		 colors();
         while (notBroken == true) {
				move();
         }
     }
	
	public void move () {
		setAhead(180);
		setTurnGunRight(180);
		GunTurnCompleteCondition y = new GunTurnCompleteCondition (this);
		waitFor (y);
		setTurnGunLeft(-180);
		execute();
	}
  
     public void onScannedRobot(ScannedRobotEvent e) {
		 setFire(2);  
	 }
		 
     public void onHitWall (HitWallEvent e) {
		 notBroken = false;
		 back (20);   
		 turnRight (e.getBearing());
		 turnRight (90);
		 turnGunRight (-1.0 * e.getBearing());
		 notBroken = true; 
	 }
	 
	public void onHitRobot (HitRobotEvent e) {
		 turnRight (e.getBearing());
		 setBack(100);
		 fire (5);
		 execute();
	}
	
	public void colors () {
		setAllColors (Color.black);
		setScanColor (Color.red);
	}
 }
