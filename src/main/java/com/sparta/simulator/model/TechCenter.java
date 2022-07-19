package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

public class TechCenter extends Centre{

    private String techType;
    private int trainees = 0;

    private ArrayList<Trainee> list = getTraineeList();

    TechCenter(){
        Random rand = new Random();
        int num = rand.nextInt(5);
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("C#");
        list.add("Data");
        list.add("DevOps");
        list.add("Business");
        this.techType = list.get(num);
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
        if(trainees < 200){
            return false;
        }
        else return true;
    }

    public String getTechType(){
        return techType;
    }
}
