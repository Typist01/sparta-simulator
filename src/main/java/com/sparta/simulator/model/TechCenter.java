package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

public class TechCenter extends Centre{

    private final CourseType techType;
    TechCenter() {
        super(200);
        Random rand = new Random();
        int num = rand.nextInt(5);
        this.techType = CourseType.values()[num];
    }


    @Override
    boolean acceptsTrainee(Trainee trainee) {
        // DEBUG CODE
        //try {

           // if (trainee.getType().equalsIgnoreCase(this.techType)) {
             //   return true;
            //} else return false;
        //}
//        catch (Exception e){
//            System.out.println(trainee);
//            System.out.println(trainee.getType());
//            e.printStackTrace();
//        }
//        //return false;
        return trainee.getType().equals(this.techType);
    }

    public CourseType getTechType(){
        return techType;
    }
}
