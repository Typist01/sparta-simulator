package com.sparta.simulator.model;

import java.util.ArrayList;

public class TrainingHub extends Centre{
//can train a maximum of 100 trainees, but 3 (randomly 1-3) can be opened at a time each month.
    private ArrayList<Trainee> list = getTraineeList();

    public void addTrainee(Trainee t){
            //TrainingHub.super.traineeList.add(t);
            list.add(t);
    }

    boolean isFull(){ return list.size() >= 100 ; }

}
