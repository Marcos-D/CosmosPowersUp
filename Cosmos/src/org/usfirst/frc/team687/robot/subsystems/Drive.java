package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	private TalonSRX m_rightMaster; 
	private TalonSRX m_rightSlave1; 
	private TalonSRX m_rightSlave2; 
	private TalonSRX m_leftMaster; 
	private TalonSRX m_leftSlave1; 
	private TalonSRX m_leftSlave2;
	
	private AHRS gyro;
	
	public Drive() {
		m_rightMaster = new TalonSRX(RobotMap.krightMasterPort);
		m_rightSlave1 = new TalonSRX(RobotMap.krightSlave1Port);
		m_rightSlave2 = new TalonSRX(RobotMap.krightSlave2Port);
		m_leftMaster = new TalonSRX(RobotMap.kleftMasterPort);
		m_leftSlave1 = new TalonSRX(RobotMap.kleftSlave1Port);
		m_leftSlave2 = new TalonSRX(RobotMap.kleftSlave2Port);
		
		gyro = new AHRS(SPI.Port.kMXP);
		
		m_rightSlave1.follow(m_rightMaster);
		m_rightSlave2.follow(m_rightMaster);
	
		m_leftSlave1.follow(m_leftMaster);
		m_leftSlave2.follow(m_leftMaster);
	
	}
	
	//void used to manipulate power supplied at motors
	public void setPower(double leftPower, double rightPower){
		m_rightMaster.set(ControlMode.PercentOutput, -rightPower);
		m_rightSlave1.set(ControlMode.PercentOutput, -rightPower);
		m_rightSlave2.set(ControlMode.PercentOutput, -rightPower);
		m_leftMaster.set(ControlMode.PercentOutput, leftPower);
		m_leftSlave1.set(ControlMode.PercentOutput, leftPower);
		m_leftSlave2.set(ControlMode.PercentOutput, leftPower);
	}
	
	public double getLeftPos() {
		return m_leftMaster.getSelectedSensorPosition(0);
		
	}
	
	public double getRightPos() { 
		return m_rightMaster.getSelectedSensorPosition(0);
		
	}
	
	//Average Position is actual position when driving forward
	public double getAvgPos() {
		return ((getLeftPos() + getRightPos()) /2); 
	}
	
	//Used in DriveTurn to turn for an angle given by the gyro
	public double getYaw() { 
		return gyro.getYaw();
		
	}
	
	public void resetEncoders() {
		m_rightMaster.setSelectedSensorPosition(0, 0, 0);
		m_leftMaster.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public double getLeftVelocity() {
		return m_leftMaster.getSelectedSensorVelocity(0);   
	}

	public double getRightVelocity() {
		return m_rightMaster.getSelectedSensorVelocity(0);
	}

	public double getAvgVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
	
	public void reportToDash() {
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TankDrive());
        
    }
}

