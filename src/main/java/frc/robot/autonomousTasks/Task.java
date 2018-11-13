package frc.robot.autonomousTasks;

import java.util.ArrayList;

public class Task{
    ArrayList<Object[]> params;
    String taskName;

    public Task(ArrayList<Object[]> params){
        this.params = params;
    }

    public void initTask(){}
    public boolean runTask(){return true;}
}