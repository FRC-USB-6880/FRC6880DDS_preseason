package frc.robot.jsonReaders;

import org.json.simple.JSONArray;

public class RobotConfigReader extends JsonReader{

    public RobotConfigReader(String robotName){
        super(JsonReader.robotsFile);
        setRootObj(getJSONObject(baseObj, robotName));
    }

    public String getDriveSysName(){
        return getString(baseObj, "driveTrain");
    }

    public String getNavigationOption(){
        return getString(baseObj, "navigation");
    }

    public String getAutoPosition(){
        return getString(baseObj, "autonomousPosition");
    }

    public String getAutoOption(){
        return getString(baseObj, "autonomousOption");
    }

    public String[] getAttachments(String opmode){
        JSONArray rootArr = null;
        if(opmode.equalsIgnoreCase("autonomous"))
            rootArr = getArray(baseObj, "autonomousAttachments");
        else if(opmode.equalsIgnoreCase("teleop"))
            rootArr = getArray(baseObj, "teleopAttachments");
        else
            System.out.println("frc6880:RobotConfigReader: Opmode "+opmode+" does not exist");

        String[] attachmentsArr = new String[rootArr.size()];
        for (int i=0;i<rootArr.size();i++){
            attachmentsArr[i] = (String) rootArr.get(i);
        }

        return attachmentsArr;
    }

    public double getRobotWidth(){
        return getDouble(baseObj, "robotWidth");
    }

    public boolean isTankControl(){
        return getBoolean(baseObj, "tankDriveStationConfig");
    }
}