// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Chassis;

public class Drive extends CommandBase {
  /** Creates a new Drive2Joystick. */

  CommandXboxController controller;
	Chassis chassis;

  public Drive(Chassis chassis, CommandXboxController controller) {

    this.chassis = chassis;
		this.controller = controller;
		addRequirements(this.chassis);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double rightY = controller.getRightY()*-0.5;
		double leftY = controller.getLeftY()*-0.5;
		chassis.setPower(leftY, rightY);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.setPower(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
