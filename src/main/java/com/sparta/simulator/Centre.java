package com.sparta.simulator;

import java.util.ArrayList;

public class Centre {
    ArrayList<Trainee> traineeList = new ArrayList();
//    private int extraTrainees = 0;

    Centre(){
        //trainees = new ArrayList<Trainee>[];
    }
    /*void addTrainees(int numOfTrainees) { // insert random num between 0 and 50
        for (int i = 0; i < numOfTrainees; i++) {
            if (!this.isFull()) {    //add trainees if centre not full
                traineeList.add(new Trainee());
            } else {
                extraTrainees = (numOfTrainees - i); //stops loop and correctly sets num of extras
                break;
            }
        }
    }*/

    void addTrainee(Trainee trainee){
        traineeList.add(trainee);
    }

    public int getTraineeCount(){
        return traineeList.size();
    }

//    int getExtraTrainees(){
//        return extraTrainees;
//    }

    int getNumOfTrainees(){
        return traineeList.size();
    }

    boolean isFull(){
        return traineeList.size() >= 100 ;
    }

}
