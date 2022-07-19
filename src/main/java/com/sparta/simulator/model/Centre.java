package com.sparta.simulator.model;

import java.util.ArrayList;

public abstract class Centre {
    ArrayList<Trainee> traineeList = new ArrayList();

    Centre(){
    }

    abstract void addTrainee(Trainee trainee);

    int getNumOfTrainees(){
        return traineeList.size();
    }

   abstract boolean isFull();


}
