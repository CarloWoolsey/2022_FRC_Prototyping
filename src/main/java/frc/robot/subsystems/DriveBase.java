// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveBase extends SubsystemBase {
  private final WPI_VictorSPX frontLeftMotor;
  private final WPI_VictorSPX frontRightMotor;
  private final WPI_VictorSPX backLeftMotor;
  private final WPI_VictorSPX backRightMotor;

  private final MotorControllerGroup leftMotors;
  private final MotorControllerGroup rightMotors;

  private final DifferentialDrive differentialDrive;

  public void arcadeDrive(double speed, double rot){
    rightMotors.setInverted(true);
    differentialDrive.arcadeDrive(speed, rot);
  }

  
  /** Creates a new ExampleSubsystem. */
  public DriveBase() {
    this.frontLeftMotor = new WPI_VictorSPX(Constants.DriveConstants.FRONT_LEFT_MOTOR_PORT);
    this.frontRightMotor = new WPI_VictorSPX(Constants.DriveConstants.FRONT_RIGHT_MOTOR_PORT);
    this.backLeftMotor = new WPI_VictorSPX(Constants.DriveConstants.BACK_LEFT_MOTOR_PORT);
    this.backRightMotor = new WPI_VictorSPX(Constants.DriveConstants.BACK_RIGHT_MOTOR_PORT);
  
    this.leftMotors = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
    this.rightMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);
  
    this.differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void foreward(){
  }
}
