// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class VelocityPID extends CommandBase implements Sendable {
  /** Creates a new MooviMoovi. */

  public Chassis chassis;
  public double velocity;

  public double getVelocity() {return velocity;}
  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("velocity", this::getVelocity, this::setVelocity);
    // builder.addDoubleProperty("set velocity", null, this::setVelocity);
  }

  public VelocityPID(Chassis chassis) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.chassis = chassis;
    addRequirements(chassis);
    SmartDashboard.putData(this);
    SmartDashboard.putData(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    chassis.setVelocity(velocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.setVelocity(0);
  }
}
