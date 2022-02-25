// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class Shooter extends SubsystemBase {
  private CANSparkMax lFlywheel = new CANSparkMax(Constants.MotorConstants.lFlywheelCANPort, MotorType.kBrushless);
  private CANSparkMax rFlywheel = new CANSparkMax(Constants.MotorConstants.rFlywheelCANPort, MotorType.kBrushless);
  private RelativeEncoder lFlywheelEncoder = lFlywheel.getEncoder();
  private RelativeEncoder rFlywhelEncoder = rFlywheel.getEncoder();



  /** Creates a new Shooter. */
  public Shooter() {
  //  this.lFlywheel = new CANSparkMax(Constants.MotorConstants.lFlywheelCANPort, MotorType.kBrushless);
  //  this.rFlywheel = 

  }

  public void runLMotor(double speed){
    lFlywheel.set(speed);
  }
  public void runRMotor(double speed){
    rFlywheel.set(speed);
  }
  public void runFlywheel(double speed){
    lFlywheel.set(speed);
    rFlywheel.follow(lFlywheel, true);
  }
  public double getLFlywheelRPM(){
    return lFlywheelEncoder.getVelocity();
    
  }
  public double getRFlywheelRPM(){
    return rFlywhelEncoder.getVelocity();
  }
  public double getFlywheelRPM(){
    return (Math.abs(rFlywhelEncoder.getVelocity()) + Math.abs(lFlywheelEncoder.getVelocity()))/2;
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
