package org.usfirst.frc.team6880.robot.attachments;
import org.usfirst.frc.team6880.robot.FRCRobot;
import org.usfirst.frc.team6880.robot.jsonReaders.AttachmentsReader;
import org.usfirst.frc.team6880.robot.jsonReaders.JsonReader;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift
{
	FRCRobot robot;
	WPI_TalonSRX liftMotor;
	private double height;
	public Encoder liftEncoder;
	
	public int[] lowRange = {0,0};
	public int[] midRange = {0,0};
	public int[] highRange = {0,0};
	public int rangeValue;
	
	private double spoolDiameter;
	private double spoolCircumference;
	private double distancePerCount;
	private double curPower;
	private long targetPos;

    AttachmentsReader configReader;
    boolean isMoving;

    /**
     * 
     */
    public Lift(FRCRobot robot)
    {
    	this.robot = robot;
    	configReader = new AttachmentsReader(JsonReader.attachmentsFile, "Lift");
    	int liftMotorCANid = configReader.getLiftControllerCANid();
    	double liftMotorRampTime = configReader.getLiftMotorOpenLoopRampTime();
        spoolDiameter = configReader.getLiftSpoolDiameter();
        lowRange = configReader.getLiftPos_encoderCounts("liftPos_lowRange");
        midRange =  configReader.getLiftPos_encoderCounts("liftPos_midRange");
        highRange = configReader.getLiftPos_encoderCounts("liftPos_highRange");
        rangeValue = highRange[1]/2;

        liftMotor = new WPI_TalonSRX(liftMotorCANid);

    	height = 0;
    	spoolCircumference = Math.PI * spoolDiameter;
    	distancePerCount = spoolCircumference / 360;
    	curPower = 0.0;

    	liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
    	

        targetPos = 0;
        isMoving = false;
    }
    
    public void stop()
    {
    	liftMotor.set(0);
    }
    
    public boolean checkUpperLimit()
    {
        if(getCurPos()>=highRange[1])
    		return true;
    	return false;
    }
    
    public boolean checkLowerLimit()
    {
    	if(getCurPos()<=lowRange[0])
    		return true;
    	return false;
    }
    
    public long getCurPos()
    {
    	return -liftMotor.getSelectedSensorPosition(0);
    }
    
    public void moveWithPower(double power)
    {
//    	if(power<0)
//    		liftMotor.set(checkLowerLimit() ? 0.0 : power);
//    	else if(power>0)
//    		liftMotor.set(checkUpperLimit() ? 0.0 : power);
//    	displayCurrentPosition();
    	liftMotor.set(power);
    	curPower = power;
    }
    public void displayCurrentPosition()
    {
        double value = -liftMotor.getSelectedSensorPosition(0);
        System.out.format("frc6880: Current lift encoder value = %f\n", value);
    }
    
    public boolean moveToHeight(double power)
    {
		if(targetPos+100 < getCurPos()) 
		{
            moveWithPower(-power);
            isMoving = true;
			return false;
		}
		else if(targetPos-100 > getCurPos())
		{
            moveWithPower(power);
            isMoving = true;
			return false;
		}
		
		stop();
		return true;
    }
    
    public void setTargetHeight(long target)
    {
    	if(targetPos>highRange[1])
    		targetPos = highRange[1];
    	else if (targetPos<0)
    		targetPos = 0;
    	else
    		targetPos = target;
    }

	public boolean isMoving() {
		return isMoving;
	}

}