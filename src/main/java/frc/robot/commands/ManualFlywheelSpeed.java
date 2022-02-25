// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;


public class ManualFlywheelSpeed extends CommandBase {
  private final Shooter m_shooterSubsystem; 

  private final NetworkTable shooterTable;
  private NetworkTableEntry absFlyEntry;
  private NetworkTableEntry flywheelSpeedVal;
  private NetworkTableEntry rMotorCurrentDraw;
  private NetworkTableEntry lMotorCurrentDraw;
  private NetworkTableInstance inst;
  private PowerDistribution examplePD = new PowerDistribution(0, ModuleType.kCTRE);


  /** Creates a new ManualFlywheelSpeed. */
  public ManualFlywheelSpeed(Shooter shooterSubsystem) {
    this.m_shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);

    inst = NetworkTableInstance.getDefault();
    shooterTable = inst.getTable("shooterTable");

    absFlyEntry = shooterTable.getEntry("Flywheel RPM");
    rMotorCurrentDraw = shooterTable.getEntry("Right Motor Current Draw in Amps");
    lMotorCurrentDraw = shooterTable.getEntry("Left Motor Current Draw in Amps");
  
    

    flywheelSpeedVal = shooterTable.getEntry("FlywheelSpeed");
    flywheelSpeedVal.setDouble(0);
  
  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooterSubsystem.runFlywheel(flywheelSpeedVal.getDouble(0));

    absFlyEntry.setDouble(m_shooterSubsystem.getFlywheelRPM());
    rMotorCurrentDraw.setDouble(examplePD.getCurrent(15));
    lMotorCurrentDraw.setDouble(examplePD.getCurrent(3));
    
    SmartDashboard.putNumber("Right Motor Current Draw", examplePD.getCurrent(15));
    SmartDashboard.putNumber("Left Motor Current Draw", examplePD.getCurrent(3));

    SmartDashboard.putNumber("Right Motor RPM", Math.abs(m_shooterSubsystem.getRFlywheelRPM()));
    SmartDashboard.putNumber("Left Motor RPM", Math.abs(m_shooterSubsystem.getLFlywheelRPM()));
    SmartDashboard.putNumber("Shooter Motor RPM Disparity", Math.abs(
      m_shooterSubsystem.getLFlywheelRPM() - m_shooterSubsystem.getRFlywheelRPM()));

    //System.out.println(m_shooterSubsystem.getFlywheelRPM());
    System.out.println("Right Motor Current Draw: " + examplePD.getCurrent(15) + " Left Motor Current Draw: " + examplePD.getCurrent(3));
    
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
