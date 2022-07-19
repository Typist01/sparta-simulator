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

    //TODO change to boolean? if cant add trainee return false otherwise if trainee was added return true.
    abstract void addTrainee(Trainee trainee);

    int getNumOfTrainees(){
        return traineeList.size();
    }

    abstract boolean isFull();

    boolean isClosable(){
        return traineeList.size() <= 25;
    }
}
