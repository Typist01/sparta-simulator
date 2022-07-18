package com.sparta.simulator;

import java.util.ArrayList;

public class Centre {
    ArrayList<Trainee> traineeList[] = new ArrayList[100];
    static int numOfTrainees = 0;

    Centre(){
        //trainees = new ArrayList<Trainee>[];
    }
    int makeNewTrainees(int newnum) { // insert random num between 0 and 50
        int remainingTrainees = 0;

        for (int i = 0; i < newnum; i++) {
            if (numOfTrainees < 100) {    //add trainees if centre not full
                traineeList[numOfTrainees].add(new Trainee());
                numOfTrainees++;
            } else {
                remainingTrainees = (newnum - i);
                return remainingTrainees;
                // have overflow thing in here
            }
        }
        return remainingTrainees;
    }

}
