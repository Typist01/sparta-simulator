package com.sparta.simulator;

import java.util.ArrayList;

public class Centre {
    ArrayList<Trainee> traineeList[] = new ArrayList[100];
    private int numOfTrainees = 0;
    private int extraTrainees = 0;

    Centre(){
        //trainees = new ArrayList<Trainee>[];
    }
    void addTrainees(int numOfTrainees) { // insert random num between 0 and 50

        for (int i = 0; i < numOfTrainees; i++) {
            if (this.numOfTrainees < 100) {    //add trainees if centre not full
                traineeList[this.numOfTrainees].add(new Trainee());
                this.numOfTrainees++;
            } else {
                extraTrainees = (numOfTrainees - i);
                break;
            }
        }
    }

    int getExtraTrainees(){
        return extraTrainees;
    }

    int getNumOfTrainees(){
        return numOfTrainees;
    }

    boolean isFull(){
        if (numOfTrainees < 100) return false;
        else return true;
    }

}
