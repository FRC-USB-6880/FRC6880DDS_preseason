package frc.robot.jsonReaders;

public class MotorSpecsReader extends JsonReader{
    public MotorSpecsReader(String motorType){
        super(JsonReader.motorSpecsFile);
        setRootObj(getJSONObject(baseObj, motorType));
    }

    public int getCPR(){
        return getInt(baseObj, "CPR");
    }
}