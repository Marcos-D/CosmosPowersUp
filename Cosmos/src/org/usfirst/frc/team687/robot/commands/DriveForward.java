package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.Constants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	private double m_error1;
	private double m_error2;
	
	private double m_output;
	private double m_distance;
	
	private double m_time1;
	private double m_time2;
	
	private double m_derivative;
	
	private double m_counter;
	
    public DriveForward(double distance) {
        requires(Robot.drive);
        m_distance = distance;
        
    }
    protected void initialize() {
    m_counter = 0;
    m_error1 = m_distance;
    m_time1 = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_error2 = m_distance - Robot.drive.getAvgPos();
    	m_time2 = Timer.getFPGATimestamp();
    	
    	m_derivative = (m_error2 - m_error1) / (m_time2 - m_time1);
    
    	m_output  = (m_error2 * Constants.kDriveForwardP) + (m_derivative * Constants.kDriveForwardD);
    	Robot.drive.setPower(m_output, m_output);
    
    	m_error2 = m_error1;
    	m_time2 = m_time1;
    
    	if(Math.abs(m_error2) < Constants.kDriveForwardTolerance) {
    		m_counter++; 
    	}
    	else {
    		m_counter = 0;
    	}
    }

    protected boolean isFinished() {
        return m_counter >= 2;  
    }

    protected void end() {
    Robot.drive.setPower(0, 0);
    }
    
    protected void interrupted() {
    }
}
