package frc.robot.jsonReaders;

import org.json.simple.JSONArray;

public class RobotConfigReader extends JsonReader{

    public RobotConfigReader(String robotName){
        super(JsonReader.robotsFile);
        setRootObj(getJSONObject(robotName));
    }

    public String getDriveSysName(){
        return getString("driveTrain");
    }

    public String getNavigationOption(){
        return getString("navigation");
    }

    public String getAutoPosition(){
        return getString("autonomousPosition");
    }

    public String getAutoOption(){
        return getString("autonomousOption");
    }

    public String[] getAttachments(String opmode){
        JSONArray rootArr = null;
        if(opmode.equalsIgnoreCase("autonomous"))
            rootArr = getArray("autonomousAttachments");
        else if(opmode.equalsIgnoreCase("teleop"))
            rootArr = getArray("teleopAttachments");
        else
            System.out.println("frc6880:RobotConfigReader: Opmode "+opmode+" does not exist");

        String[] attachmentsArr = new String[rootArr.size()];
        for (int i=0;i<rootArr.size();i++){
            attachmentsArr[i] = (String) rootArr.get(i);
        }

        return attachmentsArr;
    }

    public double getRobotWidth(){
        return getDouble("robotWidth");
    }

    public boolean isTankControl(){
        return getBoolean("tankDriveStationConfig");
    }
}