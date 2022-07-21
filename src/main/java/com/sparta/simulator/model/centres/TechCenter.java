package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.CourseType;
import com.sparta.simulator.model.RandGenerator;
import com.sparta.simulator.model.Trainee;

import java.util.Random;
// Class for creating centres of type tech centre
public class TechCenter extends Centre{

    private final CourseType techType;
    public TechCenter() {
        super(200);
        this.techType = RandGenerator.generateRandomCourse();
    }


    @Override
    public boolean acceptsTrainee(Trainee trainee) {
        return trainee.getType().equals(this.techType);
    }

    public CourseType getTechType(){
        return techType;
    }
}
