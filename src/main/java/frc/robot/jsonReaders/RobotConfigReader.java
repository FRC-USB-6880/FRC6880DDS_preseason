package frc.robot.jsonReaders;

import org.json.simple.JSONArray;

public class RobotConfigReader extends JsonReader{
    private String filePath = null;
    private String robotName;

    public RobotConfigReader(String robotName){
        super(JsonReader.robotsFile);
        this.robotName = robotName;
    }

    public String getDriveSysName(){
        return getString("drive_train");
    }

    public String getNavigationOption(){
        return getString("navigation");
    }

    public String getAutoPosition(){
        return getString("autonomous_position");
    }

    public String getAutoOption(){
        return getString("autonomous_option");
    }

    public String[] getAttachments(String opmode){
        JSONArray rootArr = null;
        if(opmode.equalsIgnoreCase("autonomous"))
            rootArr = getArray("autonomous_attachments");
        else if(opmode.equalsIgnoreCase("teleop"))
            rootArr = getArray("teleop_attachments");
        else
            System.out.println("frc6880:RobotConfigReader: Opmode "+opmode+" does not exist");

        String[] attachmentsArr = new String[rootArr.size()];
        for (int i=0;i<rootArr.size();i++){
            attachmentsArr[i] = (String) rootArr.get(i);
        }

        return attachmentsArr;
    }

    public double getRobotWidth(){
        return getDouble("robot_width");
    }

    public boolean isTankControl(){
        return getBoolean("tank_driver_station_config");
    }
}