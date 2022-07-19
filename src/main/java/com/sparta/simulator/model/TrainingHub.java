package com.sparta.simulator.model;

public class TrainingHub extends Centre{
//can train a maximum of 100 trainees, but 3 (randomly 1-3) can be opened at a time each month.
    public void addTrainee(Trainee t){
            //TrainingHub.super.traineeList.add(t);
            traineeList.add(t);
    }

    boolean isFull(){ return traineeList.size() >= 100 ; }
}
