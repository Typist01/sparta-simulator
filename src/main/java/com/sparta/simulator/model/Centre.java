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

    public boolean addTrainee(Trainee trainee){
        if (!isFull()){
            return  traineeList.add(trainee);
        }
        return false;
    }

    public int getNumOfTrainees(){
        return traineeList.size();
    }

    public boolean isFull(){
        return CAPACITY == traineeList.size();
    }

    public boolean isClosable(){
        return traineeList.size() <= 25;
    }
    public boolean acceptsTrainee(Trainee trainee){
        return true;
    }


}
