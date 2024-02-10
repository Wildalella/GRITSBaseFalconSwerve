// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  NetworkTable table;
  NetworkTableEntry tx, ty, tv, ta, ts;
  double x, y, v, area, s;

   // how many degrees back is your limelight rotated from perfectly vertical?
   double limelightMountAngleDegrees = 25.0; 
   // distance from the center of the Limelight lens to the floor
   double limelightLensHeightInches = 20.0; 

   // distance from the target to the floor
   double goalHeightInches = 60.0; 



  /** Creates a new LimeLight. */
  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");
    ts = table.getEntry("ts");
  }

 
    public void readValues(){
     tx.getDouble(0.0);
     ty.getDouble(0.0);
     tv.getDouble(0.0);
     ta.getDouble(0.0);
     ts.getDouble(0.0); 
      postToDashboard();
    // This method will be called once per scheduler run
  } 

 
  public void postToDashboard(){
    
   SmartDashboard.putNumber("LimelightX", x);
   SmartDashboard.putNumber("LimelightY", y);
   SmartDashboard.putNumber("LimelightV", v);
   SmartDashboard.putNumber("LimelightArea", area);
   SmartDashboard.putNumber("LimelightSkew", s);
   SmartDashboard.putNumber("LEDMODE", (int)table.getEntry("ledMode").getDouble(0.0));
   SmartDashboard.putBoolean("Is aimed", isAimed());
   SmartDashboard.putBoolean("See target", seesTarget());
  }
  
   boolean isAimed(){
    return getX()<=1 && getX()>=-1; 

}


 public double getX(){
    readValues();
    return x;
 } 
  public double getY(){
    readValues();
    return y;

  }
  public double getV(){
    readValues();  
    return v;
  }

  public double getArea(){
    readValues();
    return area;
  }

  public double getSkew(){
    readValues();
    return s;
  }

   public boolean seesTarget(){
    readValues(); 
    if( v == 0 )
      return false;
    else  
      return true;
  }

  //a value of 0 represents the vision processor
  //a value of 1 represents the driver camera
  public void setView(int n){
    table.getEntry("camMode").setNumber(n);
  }

  public int getView() {
    return (int)table.getEntry("camMode").getDouble(0.0);
  }

  //0 is side by side, 1 is PiP Main with limelight big
  //2 is PiP Secondary with Secondary big
  public void setStreamMode(int n ){
    table.getEntry("stream").setNumber(n);
  }

  public int getStreamMode() {
    return (int)table.getEntry("stream").getDouble(0.0);
  }

  //0: use pipeline default
  //1: force off, 2: force blink, 3: force on
  public void setLedMode(int n  ){
    table.getEntry("ledMode").setNumber(n);
  }

  public void setPipeline( int p){
    table.getEntry("pipeline").setNumber(p);
} 
  



}

  

  
