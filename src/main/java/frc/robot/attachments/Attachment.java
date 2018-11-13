package frc.robot.attachments;
import java.util.ArrayList;

import frc.robot.jsonReaders.AttachmentsReader;

public class Attachment {
    String attachmentName;
    AttachmentsReader reader;
    ArrayList<Object[]> params;

    public Attachment(ArrayList<Object[]> params){
        this.params = params;
    }

    public String getAttachmentName(){
        return attachmentName;
    }

    public void engage(){}

    public void disengage(){}
}