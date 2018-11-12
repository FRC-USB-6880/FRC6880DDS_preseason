package frc.robot.jsonReaders;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AutonomousOptionsReader extends JsonReader{
    ArrayList<Object[]> tasks;

    public AutonomousOptionsReader(String autoPos, String autoOption){
        super(JsonReader.autonomousOptFile);
        tasks = new ArrayList<>();
        JSONObject autonomousOptObj = getJSONObject(baseObj, autoPos);
        setRootObj(autonomousOptObj);
        JSONArray tasksArr = getArray(baseObj, autoOption);
        for(int i=0;i<tasksArr.size();i++){
            Object[] taskObj = new Object[2];
            JSONObject obj = (JSONObject)tasksArr.get(i);
            taskObj[0] = getString(obj, "name");

            String[] keys = (String[])(obj.keySet().toArray());
            Object[] values = (Object[])(obj.values().toArray());
            ArrayList<Object[]> params = new ArrayList<>();
            for (int k=1;k<keys.length;k++) {
                Object[] paramObj = new Object[2];
                paramObj[0] = keys[i];
                paramObj[1] = values[i];
                params.add(paramObj);
            }
            taskObj[1] = params;

            tasks.add(taskObj);
        }
    }

    public ArrayList<Object[]> getTasks(){
        return tasks;
    }
}