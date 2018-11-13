package frc.robot.jsonReaders;

public class WheelSpecsReader extends JsonReader{
    public WheelSpecsReader(String wheelType){
        super(JsonReader.wheelSpecsFile);
        setRootObj(getJSONObject(baseObj, wheelType));
    }

    public double getDiameter(){
        return getDouble(baseObj, "diameter");
    }

    public double getFrictionCoefficient(){
        return getDouble(baseObj, "frictionCoeff");
    }
}