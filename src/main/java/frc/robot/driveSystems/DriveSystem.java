package frc.robot.driveSystems;

public interface DriveSystem{
    enum Gears {LOW, HIGH};
    void tankDrive(double leftSpeed, double rightSpeed);
    void arcadeDrive(double speed, double direction);
    void resetEncoders();
    double getEncoderDist();
    void setLowSpeed();
    void setHiSpeed();
    boolean isMoving();
    Gears getCurGear();
    void changeMultiplier(double multiplier);
}