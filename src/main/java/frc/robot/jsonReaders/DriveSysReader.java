package frc.robot.jsonReaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DriveSysReader extends JsonReader {
    private String driveSysName;

    public DriveSysReader(String driveSysName){
        super(JsonReader.driveTrainsFile);
        JSONObject driveSysObj = getJSONObject(baseObj, driveSysName);
        setRootObj(driveSysObj);
        this.driveSysName = driveSysName;
    }

    public String getMotorType(String motorName){
        JSONObject motorObj = getJSONObject(baseObj, motorName);
        String motorType = getString(motorObj, "motorType");
        return motorType;
    }

    public int getChannelNum(String motorName){
        JSONObject motorObj = getJSONObject(baseObj, motorName);
        int channelNum = getInt(motorObj, "channel");
        return channelNum;
    }

    public int getDeviceID(String motorName){
        JSONObject motorObj = getJSONObject(baseObj, motorName);
        int deviceID = getInt(motorObj, "deviceID");
        return deviceID;
    }

    public boolean isFollower(String motorName){
        JSONObject motorObj = getJSONObject(baseObj, motorName);
        boolean isFollower = getBoolean(motorObj, "follower");
        return isFollower;
    }

    public String getLeadController(String motorName){
        JSONObject motorObj = getJSONObject(baseObj, motorName);
        String leadController = getString(motorObj, "leadController");
        return leadController;
    }

    public int[] getEncoderChannels(String encoderName){
        JSONObject encoderObj = getJSONObject(baseObj, encoderName);
        JSONArray channelList = getArray(encoderObj, "encoderChannels");
        int[] encoderChannels = new int[2];
        encoderChannels[0] = (int) ((Long)channelList.get(0)).intValue();
        encoderChannels[1] = (int) ((Long)channelList.get(1)).intValue();
        return encoderChannels;
    }

    public String getEncoderType(String encoderName){
        JSONObject encoderObj = getJSONObject(baseObj, encoderName);
        String encoderType = getString(encoderObj, "encoderType");
        return encoderType;
    }

    public int getEncoderValue(String encoderName, String encoderKey){
        JSONObject encoderObj = getJSONObject(baseObj, encoderName);
        String encoderType = getString(encoderObj, "encoderType");
        JsonReader encoderSpecsReader = new JsonReader(JsonReader.encoderSpecsFile);
        JSONObject encoderTypeObj = encoderSpecsReader.getJSONObject(encoderSpecsReader.baseObj, encoderType);
        int counts = encoderSpecsReader.getInt(encoderTypeObj, encoderKey);
        return counts;
    }

    public String getWheelType(){
        return getString(baseObj, "wheelType");
    }

    public String getDriveSysName(){
        return driveSysName;
    }
    
    public double getGearRatio(String side){
        double ratio = 10.71;
        String key = "";
        switch(side){
            case "Left":
                key = "gearRatioL";
                break;
            case "Right":
                key = "gearRatioR";
                break;
            case "Left_LowSpeed":
                key = "gearRatioL_loSpd";
                break;
            case "Left_HiSpeed":
                key = "gearRatioL_hiSpd";
                break;
            case "Right_LowSpeed":
                key = "gearRatioR_loSpd";
                break;
            case "Right_HiSpeed":
                key = "gearRatioR_hiSpd";
                break;
            default:
                key = "gearRatioL";
                break;
        }
        ratio = getDouble(baseObj, key);
        return ratio;
    }
}