/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.DriveForward;
import org.usfirst.frc.team687.robot.commands.ResetEncoders;
import org.usfirst.frc.team687.robot.commands.ResetGyro;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftjoy = new Joystick(0);
	public Joystick rightjoy = new Joystick(1);
	
	//creates a new instance of drive time with given parameters
	public OI() {
		SmartDashboard.putData("Move Forward 20", new DriveForward(75000));
		SmartDashboard.putData("Reset Encoder Values" , new ResetEncoders());
		SmartDashboard.putData("Reset Gyro", new ResetGyro());
	}
}
