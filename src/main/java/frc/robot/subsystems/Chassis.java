// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase implements Sendable{
  /** Creates a new Chassis. */
  public TalonFX motorLB;
  public TalonFX motorLF;
  public TalonFX motorRF;
  public TalonFX motorRB;
  public double ki;
  public double kp;
  public double getKi() {return ki;}
  public double getKp() {return kp;}
  public void setKi(double ki) {this.ki = ki;}
  public void setKp(double kp) {this.kp = kp;}


  public Chassis() {
      motorLB = new TalonFX(Constants.leftBackMotorID);
      motorLF = new TalonFX(Constants.leftFrontMotorID);
      motorRF = new TalonFX(Constants.rightFrontMotorID);
      motorRB = new TalonFX(Constants.rightBackMotorID);
      motorRF.setInverted(true);
      motorRB.setInverted(true);
      motorLB.config_kP(0, kp);
      motorRB.config_kP(0, kp);
      motorLB.config_kI(1, ki);
      motorRB.config_kI(1, ki);
      motorLF.follow(motorLB);
      motorRF.follow(motorRB);
  }

  public void setVelocity(double velocity) {
    motorLB.set(ControlMode.Velocity, Constants.countsPerMeterPerDeciSecond*velocity);
    motorRB.set(ControlMode.Velocity, Constants.countsPerMeterPerDeciSecond*velocity);
  }

  public void setPower(double leftMotors, double rightMotors) {
    motorLB.set(ControlMode.PercentOutput, leftMotors);
    motorRB.set(ControlMode.PercentOutput, rightMotors);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("P", this::getKp, this::setKp);
    // builder.addDoubleProperty("set P", null, this::setKp);

    builder.addDoubleProperty("I", this::getKi, this::setKi);
    // builder.addDoubleProperty("set I", null, this::setKi);
  }  
}
