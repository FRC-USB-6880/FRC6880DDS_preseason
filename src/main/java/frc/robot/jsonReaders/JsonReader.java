package frc.robot.jsonReaders;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader{
    public static String baseDir = "/home/lvuser/team6880/";
    public static String sensorSpecsFile, wheelSpecsFile, motorSpecsFile, encoderSpecsFile,
                        robotsFile, attachmentsFile, driveTrainsFile, navigationFile, autonomousRedDir,
                        autonomousBlueDir, autonomousOptFile;
    private String filePath;
    public String jsonStr;
    private JSONParser parser = null;
    protected JSONObject baseObj = null;

    public JsonReader(String filePath){
        this.filePath = filePath;
        this.parser = new JSONParser();

        FileReader fileReader = null;
        try{
            fileReader = new FileReader(this.filePath);
        } catch(IOException e){
            System.out.println("frc6880:JsonReader: Error while trying to open "+filePath+". Error: "+e.getMessage());
        }

        try{
            Object obj = parser.parse(fileReader);
            baseObj = (JSONObject) obj;
        } catch(Exception e){
            System.out.println("frc6880:JsonReader: Error while parsing "+filePath+". Error: "+e.getMessage());
        }

        try{
            fileReader.close();
        } catch(IOException e){
            System.out.println("frc6880:JsonReader: Error while trying to close "+filePath+". Error: "+e.getMessage());
        }
    }
    
    public static void setBaseDir(String baseDir){
        JsonReader.baseDir = baseDir;
        
        //Main robot configs
        JsonReader.robotsFile = baseDir + "robots.json";

        //Specs files
        JsonReader.sensorSpecsFile = baseDir + "specs/sensor_specs.json";
        JsonReader.wheelSpecsFile = baseDir + "specs/wheel_specs.json";
        JsonReader.encoderSpecsFile = baseDir + "specs/encoder_specs.json";
        JsonReader.motorSpecsFile = baseDir + "specs/motor_specs.json";

        JsonReader.attachmentsFile = baseDir + "attachments.json";
        JsonReader.navigationFile = baseDir + "navigation_options.json";
        JsonReader.autonomousOptFile = baseDir + "autonomous_options.json";
        JsonReader.driveTrainsFile = baseDir + "drive_trains.json";
        JsonReader.autonomousRedDir = baseDir + "autonomous/red/";
        JsonReader.autonomousBlueDir = baseDir + "autonomous/blue/";
    }

    protected void setRootObj(JSONObject obj){
        baseObj = obj;
    }

    protected String getString(JSONObject obj, String key){
        String value = null;

        try{
            value = (String) obj.get(key);
        } catch (Exception e){
            e.printStackTrace();;
            System.out.println("frc6880:JsonReader: Error getting String for the key "+key);
        }
        return value;
    }

    protected double getDouble(JSONObject obj, String key){
        double value = 0.0;

        try{
            value = (double) obj.get(key);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting double for the key "+key);
        }
        return value;
    }

    protected boolean getBoolean(JSONObject obj, String key){
        boolean value = false;
        
        try{
            value = (boolean) obj.get(key);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting boolean for the key "+key);
        }
        return value;
    }

    protected int getInt(JSONObject obj, String key){
        int value = 0;

        try{
            value = (int) ((Long)obj.get(key)).intValue();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting int for the key "+key);
        }
        return value;
    }

    protected JSONArray getArray(JSONObject obj, String key){
        JSONArray array = null;
        try{
            array = (JSONArray) obj.get(key);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting JSONArray for the key "+key);
        }
        return array;
    }

    protected JSONObject getJSONObject(JSONObject rootObj, String key){
        JSONObject obj = null;
        try{
            obj = (JSONObject) rootObj.get(key);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting JSONObject for the key "+key);
        }
        return obj;
    }

    protected Object getObject(JSONObject rootObj, String key){
        Object obj = null;
        try {
            obj = (Object) rootObj.get(key);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("frc6880:JsonReader: Error getting Object for key "+key);
        }
        return obj;
    }
}