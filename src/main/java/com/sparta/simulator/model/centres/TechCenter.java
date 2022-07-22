package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.CourseType;
import com.sparta.simulator.model.RandGenerator;
import com.sparta.simulator.model.Trainee;

// Class for creating centres of type tech centre
public class TechCenter extends Centre{

    private final CourseType techType;
    public TechCenter() {
        super(200);
        this.techType = RandGenerator.generateCourse();
    }


    @Override
    public boolean acceptsTrainee(Trainee trainee) {
        return trainee.getType().equals(this.techType);
    }

    public CourseType getTechType(){
        return techType;
    }
}
