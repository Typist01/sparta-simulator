package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.Trainee;

import java.util.ArrayList;

public abstract class Centre {

    private final int CAPACITY;
    private final ArrayList<Trainee> traineeList;

    public ArrayList<Trainee> getTraineeList() {
        return traineeList;
    }
    protected Centre(int CAPACITY){
        this.CAPACITY = CAPACITY;
        traineeList = new ArrayList<>();
    }


    public boolean addTrainee(Trainee trainee){
        if (!isFull()){
            return  traineeList.add(trainee);
        }
        return false;
    }
    public Trainee removeTrainee(int index){
        return traineeList.remove(index);
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
