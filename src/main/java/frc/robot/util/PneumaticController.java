package frc.robot.util;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PneumaticController{
    Compressor compressor;
    private int pcmCANid;

    public PneumaticController(int pcmCANid){
        this.pcmCANid = pcmCANid;
        compressor = new Compressor(pcmCANid);
        compressor.setClosedLoopControl(true);
    }

    public void turnOffCompressor(){compressor.stop();}
    public void turnOnCompressor(){compressor.start();}

    public void displayCompressorStatus(){
        System.out.println("frc6880:PneumaticController: compressor current = "+compressor.getCompressorCurrent()+
        ", pressur switch status = "+compressor.getPressureSwitchValue());
    }

    public DoubleSolenoid initializeDoubleSolenoid(int channelA, int channelB){
        DoubleSolenoid solenoid = new DoubleSolenoid(pcmCANid, channelA, channelB);
        solenoid.set(DoubleSolenoid.Value.kOff);
        return solenoid;
    }
}