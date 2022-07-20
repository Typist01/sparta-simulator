package com.sparta.simulator.model;

import java.util.ArrayList;

public class TrainingHub extends Centre{
    protected TrainingHub() {
        super(100);
    }

    public ArrayList<Trainee> getList(){
        return list;
    }


}
