package org.usfirst.frc.team687.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Square extends CommandGroup {

    public Square() {
       addSequential(new DriveForward(100000));
       addSequential(new DriveTurn(90));
    	
       addSequential(new DriveForward(100000));
       addSequential(new DriveTurn(90));
       }
}
