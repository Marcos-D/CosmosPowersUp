package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command {
	private double m_startTime;
	private double m_time;
	private double m_currentTime;
	private double m_output;
	

    public DriveTime(double time, double output) {
       requires(Robot.drive);
       m_time = time;
       m_output = output;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    Robot.drive.setPower(m_output, m_output);
    m_currentTime = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (m_currentTime - m_startTime) > m_time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
