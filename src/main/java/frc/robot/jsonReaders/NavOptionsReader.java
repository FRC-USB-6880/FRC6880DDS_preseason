package frc.robot.jsonReaders;

import org.json.simple.JSONObject;

public class NavOptionsReader extends JsonReader{
    JSONObject imuObj=null;
    JSONObject rangeObj=null;
    JSONObject encoderVarsObj=null;
    JSONObject driveSysParamsObj=null;

    public NavOptionsReader(String navOption){
        super(JsonReader.navigationFile);
        setRootObj(getJSONObject(baseObj, navOption));
        String[] keys = (String[])baseObj.keySet().toArray();
        for(String key : keys){
            if(key.equalsIgnoreCase("IMU"))
                imuObj = getJSONObject(baseObj, key);
            else if(key.equalsIgnoreCase("RangeSensor"))
                rangeObj = getJSONObject(baseObj, key);
            else if(key.equalsIgnoreCase("EncoderVars"))
                encoderVarsObj = getJSONObject(baseObj, key);
            else if(key.equalsIgnoreCase("DriveSysParameters"))
                driveSysParamsObj = getJSONObject(baseObj, key);
        }
    }

    public boolean imuExists(){
        return this.imuObj != null;
    }

    public boolean rangeSensorExists(){
        return this.rangeObj != null;
    }

    public boolean encoderVarsExist(){
        return this.encoderVarsObj != null;
    }

    public String getIMUName(){
        if(imuExists())
            return getString(imuObj, "name");
        return null;
    }

    public String getIMUPortType(){
        if(imuExists())
            return getString(imuObj, "porttype");
        return null;
    }

    public double getIMUAngleTolerance(){
        if(imuExists())
            return getDouble(imuObj, "angleTolerance");
        return 0.0;
    }

    public double getIMUKp(){
        if(imuExists())
            return getDouble(imuObj, "Kp");
        return 0.0;
    }

    public double getDriveSysTurningMaxSpeed(){
        return getDouble(driveSysParamsObj, "TurningMaxSpeed");
    }

    public double getDriveSysStraightMaxSpeed(){
        return getDouble(driveSysParamsObj, "StraightLineMaxSpeed");
    }

    public double getDriveSysTeleOpMaxSpeed(){
        return getDouble(driveSysParamsObj, "DriveSysTeleOpMaxSpeed");
    }

    public Object getEncoderVariable(String variable){
        return getObject(encoderVarsObj, variable);
    }

    public Object getRangeSensorVariable(String variable){
        return getObject(rangeObj, variable);
    }


}