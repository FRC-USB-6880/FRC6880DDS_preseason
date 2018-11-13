package frc.robot.jsonReaders;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class AttachmentsReader extends JsonReader{
    String attachmentName;
    ArrayList<Object[]> attachments;

    public AttachmentsReader(){
        super(JsonReader.attachmentsFile);
        attachments = new ArrayList<>();
        String[] attachmentNames = (String[])baseObj.keySet().toArray();
        for(int i=0;i<attachmentNames.length;i++){
            Object[] obj = new Object[2];
            obj[0] = attachmentNames[i];
            JSONObject attachmentObj = getJSONObject(baseObj, attachmentNames[i]);
            ArrayList<Object[]> params = getParams(attachmentObj);
            obj[1] = params;
            attachments.add(obj);
        }
    }

    public ArrayList<Object[]> getAttachments(){
        return attachments;
    }
    
    private ArrayList<Object[]> getParams(JSONObject attachmentObj){
        ArrayList<Object[]> params = new ArrayList<>();
        String[] keys = (String[])attachmentObj.keySet().toArray();
        Object[] values = attachmentObj.values().toArray();
        for(int i=0;i<keys.length;i++){
            Object[] obj = new Object[2];
            obj[0] = keys[i];
            obj[1] = values[i];
            params.add(obj);
        }

        return params;
    }
}