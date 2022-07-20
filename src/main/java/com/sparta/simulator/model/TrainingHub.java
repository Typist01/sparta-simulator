package com.sparta.simulator.model;

import java.util.ArrayList;

public class TrainingHub extends Centre{
//can train a maximum of 100 trainees, but 3 (randomly 1-3) can be opened at a time each month.
    private final ArrayList<Trainee> list = getTraineeList();

    protected TrainingHub() {
        super(100);
    }

    @Override
    boolean acceptsTrainee(Trainee trainee) {
        return false;
    }

}
