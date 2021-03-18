// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  //motor declaration -> ece
  // tipi WPI_TalonSRX
  // dört motor var (rear_left, rear_right, front_left, front_right)
  // erişilebilirlik, oluşturduğun şeyin tipi, verdiğin isim = new, oluşturmak istediğin şey
  // (genellikle) oluşturmak istediğin şey ile oluşturduğun şey aynı

  public WPI_TalonSRX rear_left = new WPI_TalonSRX(Constants.rear_left);
  public WPI_TalonSRX rear_right = new WPI_TalonSRX(Constants.rear_right);
  public WPI_TalonSRX front_left = new WPI_TalonSRX(Constants.front_left);
    public WPI_TalonSRX front_right = new WPI_TalonSRX(Constants.front_right);
  private AHRS gyro;
  //mecanum declaration -> yankı
  public static MecanumDrive mecanumDrive;

  public void driveMecanum(double x,double y,double z){
    mecanumDrive.driveCartesian(x, y, z);
  }

  public void zeroHeading(){
    gyro.zeroYaw();
  }

  public double getAngle(){
    return Math.IEEEremainder(gyro.getAngle(), 360) * (Constants.kGyroReversed ? 1.0: -1.0);
    //return Math.IEEEremainder(gyro.getAngle(), 360) * isGyroReversed();
    //39. satır = 40. satır
  }

  public double isGyroReversed(){
    if(Constants.kGyroReversed){
      return -1.0;
    }
    else{
      return 1.0;
    }
  }

  //drive function yazın (input: x,y,z) -> arda 
  
  public Drivetrain(AHRS gyro){
    this.gyro = gyro;
    mecanumDrive = new MecanumDrive(rear_left, rear_right, front_left, front_right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
