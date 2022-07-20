package com.sparta.simulator.model;

import java.util.ArrayList;

public abstract class Centre {
    public int getCAPACITY() {
        return CAPACITY;
    }

    private final int CAPACITY;
    public ArrayList<Trainee> getTraineeList() {
        return traineeList;
    }
    protected Centre(int CAPACITY){
        this.CAPACITY = CAPACITY;
    }

    private ArrayList<Trainee> traineeList = new ArrayList();

    boolean addTrainee(Trainee trainee){
        if (!isFull()){
            return  traineeList.add(trainee);
        }
        return false;
    }

    int getNumOfTrainees(){
        return traineeList.size();
    }

    boolean isFull(){
        return CAPACITY == traineeList.size();
    }

    boolean isClosable(){
        return traineeList.size() <= 25;
    }
    abstract boolean acceptsTrainee(Trainee trainee);


}
