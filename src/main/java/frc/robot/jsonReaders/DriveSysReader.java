package frc.robot.jsonReaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DriveSysReader extends JsonReader {
    private JSONObject driveSysObj=null;
    private String driveSysName;

    public DriveSysReader(String driveSysName){
        super(JsonReader.driveTrainsFile);
        driveSysObj = getJSONObject(driveSysName);
        setRootObj(driveSysObj);
        this.driveSysName = driveSysName;
    }

    public String getMotorType(String motorName){
        JSONObject motorObj = getJSONObject(motorName);
        setRootObj(motorObj);
        String motorType = getString("motorType");
        setRootObj(driveSysObj);
        return motorType;
    }

    public int getChannelNum(String motorName){
        JSONObject motorObj = getJSONObject(motorName);
        setRootObj(motorObj);
        int channelNum = getInt("channel");
        setRootObj(driveSysObj);
        return channelNum;
    }

    public int getDeviceID(String motorName){
        JSONObject motorObj = getJSONObject(motorName);
        setRootObj(motorObj);
        int deviceID = getInt("deviceID");
        setRootObj(driveSysObj);
        return deviceID;
    }

    public boolean isFollower(String motorName){
        JSONObject motorObj = getJSONObject(motorName);
        setRootObj(motorObj);
        boolean isFollower = getBoolean("follower");
        setRootObj(driveSysObj);
        return isFollower;
    }

    public String getLeadController(String motorName){
        JSONObject motorObj = getJSONObject(motorName);
        setRootObj(motorObj);
        String leadController = getString("leadController");
        setRootObj(driveSysObj);
        return leadController;
    }

    public int[] getEncoderChannels(String encoderName){
        JSONObject encoderObj = getJSONObject(encoderName);
        setRootObj(encoderObj);
        JSONArray channelList = getArray("encoderChannels");
        int[] encoderChannels = new int[2];
        encoderChannels[0] = (int) ((Long)channelList.get(0)).intValue();
        encoderChannels[1] = (int) ((Long)channelList.get(1)).intValue();
        setRootObj(driveSysObj);
        return encoderChannels;
    }

    public String getEncoderType(String encoderName){
        JSONObject encoderObj = getJSONObject(encoderName);
        setRootObj(encoderObj);
        String encoderType = getString("encoderType");
        setRootObj(driveSysObj);
        return encoderType;
    }

    public int getEncoderValue(String encoderName, String encoderKey){
        JSONObject encoderObj = getJSONObject(encoderName);
        setRootObj(encoderObj);
        String encoderType = getString("encoderType");
        JsonReader encoderSpecsReader = new JsonReader(JsonReader.encoderSpecsFile);
        JSONObject encoderTypeObj = encoderSpecsReader.getJSONObject(encoderType);
        encoderSpecsReader.setRootObj(encoderTypeObj);
        int counts = encoderSpecsReader.getInt(encoderKey);
        setRootObj(driveSysObj);
        return counts;
    }

    public String getWheelType(){
        return getString("wheelType");
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
        ratio = getDouble(key);
        return ratio;
    }
}