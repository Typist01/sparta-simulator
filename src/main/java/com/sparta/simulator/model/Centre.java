package com.sparta.simulator.model;

import java.util.ArrayList;

public abstract class Centre {
    public ArrayList<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(ArrayList<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    private ArrayList<Trainee> traineeList = new ArrayList();

    Centre(){
    }

    abstract void addTrainee();

    int getNumOfTrainees(){
        return traineeList.size();
    }

   abstract boolean isFull();


}
