package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

public class TechCenter extends Centre{

    private final String techType;
    private int trainees = 0;

    private final ArrayList<Trainee> list = getTraineeList();

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
        return trainees >= 200;
    }

    @Override
    boolean acceptsTrainee(Trainee trainee) {
        // DEBUG CODE
        //try {

            if (trainee.getType().equalsIgnoreCase(this.techType)) {
                return true;
            } else return false;
        //}
//        catch (Exception e){
//            System.out.println(trainee);
//            System.out.println(trainee.getType());
//            e.printStackTrace();
//        }
//        //return false;
    }

    public String getTechType(){
        return techType;
    }
}
