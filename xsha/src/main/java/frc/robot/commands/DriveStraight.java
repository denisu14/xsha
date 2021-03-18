// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;

public class DriveStraight extends CommandBase {
  /** Creates a new DriveStraight. */
  private Drivetrain dt;
  private double relativeAngle;
  private double startingAngle;
  private double goal = 0;
  private double error;
  private double kP = 0.004;

  public DriveStraight(Drivetrain dt) {
    this.dt = dt;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dt);
  }
  /*
  Başlangıç açısından sondaki açı çıkarılacak çıkan sonuç + ise - tarafa o kadar döndürülecek yiğitin amk

  Başlangıç açısı - yeni açı = dönülmesi gereken açı, dönülmesi gereken açıya göre rotate atılacak
  */
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //başlangıç açısı alınacak -> yiğit
    startingAngle = dt.getAngle();
    // Başlangıç açısının sabit kalamsı gerektiği için final olarak tanımaldım haberiniz olsun. 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //dönülmesi gereken açı hesaplanacak -> ece ypck
    relativeAngle = dt.getAngle() - startingAngle;
    //error = goal - angle
    error = 1 * (goal - relativeAngle);
    //robotun şu anki açısı - startingAngle
    //dt.getAngle() - startingAngle
    //dönülmesi gereken açı robotun x, y, z'sinden rotate'e pass down edilecek -> yankı
    dt.driveMecanum(0.0, Constants.DriveStraightSpeed, error * kP);
    //x,y,z = {-1,1}
    //sa = 30
    //relative = 40-30 = 10
    //error = -10
    //PID, P = Proportional
    //-10 * 0.004
    //error * kP
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.driveMecanum(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
