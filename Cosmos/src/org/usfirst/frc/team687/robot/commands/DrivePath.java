package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.Constants;
import org.usfirst.frc.team687.robot.util.NerdyMath;

import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("unused")
public class DrivePath extends Command {

	private double m_XPos;
	private double m_YPos;
	private double m_startDistance;
	private double m_heading;
	private double m_actualPos;	
	private double m_distTraveled;
	
	private double a;
	private double b;
	private double c;
	
	private double m_endPointX;
	private double m_endPointY;
	
	private double m_deltaX;
	private double m_deltaY;
	
	private double m_prevXPos;
	private double m_prevYPos;
	
	private double m_theta;
	private double m_perpPath;
	
	private double m_straightPow;
	private double m_rotError;
	private double m_rotPow;
	
	private double m_lookAhead;
	private double m_velocity;
	
	public DrivePath(double straightPow, double EndPointX, double EndPointY) {
		requires(Robot.drive);
		m_straightPow = straightPow;
		m_endPointX = EndPointX;
		m_endPointY = EndPointY;
	}
	
    protected void initialize() {
    	m_XPos = 0;
    	m_YPos = 0;
    	m_prevXPos = 0;
    	m_prevYPos = 0;
    	m_startDistance = 0;
    }

    protected void execute() {
    	//Get Robot X and Y 
    	m_heading = Robot.drive.getYaw();
    	m_actualPos = Robot.drive.getAvgPos();
    	m_distTraveled = m_actualPos - m_startDistance;
   
    	m_XPos += m_distTraveled * NerdyMath.degreesToRadians(Math.cos(m_heading));
    	m_YPos += m_distTraveled * NerdyMath.degreesToRadians(Math.sin(m_heading));
    	//Get Change of X and change of Y
    	m_deltaX = m_XPos - m_prevXPos; 
    	m_deltaY = m_XPos - m_prevYPos;
 	
	m_actualPos = m_startDistance;
    	m_XPos = m_prevXPos;
    	m_YPos = m_prevYPos;	
    	
    	//Trig 
    	m_theta = Math.atan2(m_deltaX, m_deltaY); 
    	m_perpPath = Math.cos(m_theta) * Robot.drive.getAvgVelocity();
    	
    	m_lookAhead = Robot.drive.getAvgVelocity() + Constants.kDeciSecond;
    
    	//Kindergarten
    	a = Math.pow(m_endPointX - m_XPos, 2);
    	b = Math.pow(m_endPointY - m_YPos, 2);
    	c = Math.pow(a + b, 0.5);
    	
		//drive
    	double robotAngle = NerdyMath.boundAngle(m_lookAhead);
	double rotError = -m_lookAhead - robotAngle;
	rotError = NerdyMath.boundAngleError(rotError);
	double rotPower = Constants.kDriveForwardP * rotError;		
	Robot.drive.setPower(m_straightPow - rotPower, m_straightPow + rotPower);
    
    }
    


    protected boolean isFinished() {
        return c <= Constants.kDrivePathTolerance || (Math.abs(m_YPos - m_endPointY) <= Constants.kDrivePathTolerance && Math.abs(m_XPos - m_endPointX) <= Constants.kDrivePathTolerance);
    //Condition May not be correct ))):
    
    }

    protected void end() {
    }

  
    protected void interrupted() {
    }
}
