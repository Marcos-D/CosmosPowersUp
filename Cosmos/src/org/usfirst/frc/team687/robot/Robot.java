/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
public class Robot extends TimedRobot {
	public static Drive drive = new Drive();
	public static OI oi;

	public void robotInit() {
		oi = new OI();
		
		
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

		public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
	}
}
