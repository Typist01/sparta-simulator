package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

public class TechCenter extends Centre{

    private final CourseType techType;
    private int trainees = 0;

    private final ArrayList<Trainee> list = getTraineeList();

    TechCenter() {
        Random rand = new Random();
        int num = rand.nextInt(5);
        this.techType = CourseType.values()[num];
    }

    @Override
    void addTrainee(Trainee trainee) {
        if(trainee.getType().equals(techType)){
           list.add(trainee);
           trainees ++;
        }
    }

    @Override
    boolean isFull() {
        return trainees >= 200;
    }

    @Override
    boolean acceptsTrainee(Trainee trainee) {
        return trainee.getType().equals(this.techType);
    }

    public CourseType getTechType(){
        return techType;
    }
}
