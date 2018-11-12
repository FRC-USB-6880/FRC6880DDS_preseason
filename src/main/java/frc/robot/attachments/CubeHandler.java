package org.usfirst.frc.team6880.robot.attachments;
import org.usfirst.frc.team6880.robot.FRCRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class CubeHandler
{
    FRCRobot robot;
    DoubleSolenoid cubeHandler;
    int forwardChannel;
    int reverseChannel;
    boolean isOpen;

    public CubeHandler(FRCRobot robot, int fwdChannel, int rvrsChannel) //need to remove 2nd and 3rd parameters
    {
        this.robot = robot;
        forwardChannel = fwdChannel;
        reverseChannel = rvrsChannel;
        cubeHandler = new DoubleSolenoid(forwardChannel, reverseChannel);
        cubeHandler.set(Value.kOff);
        isOpen = false;
    }

    public void grabCube()
    {
        cubeHandler.set(DoubleSolenoid.Value.kForward);
        isOpen = false;
    }

    public void releaseCube()
    {
        cubeHandler.set(DoubleSolenoid.Value.kReverse);
        isOpen = true;
    }

    public boolean isOpen()
    {
        return isOpen;
    }
}