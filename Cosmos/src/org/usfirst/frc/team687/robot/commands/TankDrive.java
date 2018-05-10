package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    //Sets power at motors based on values from joystick 
    protected void execute() {
    	Robot.drive.setPower(Robot.oi.leftjoy.getY(), Robot.oi.rightjoy.getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
