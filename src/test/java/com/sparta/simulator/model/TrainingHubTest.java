package com.sparta.simulator.model;

import com.sparta.simulator.model.centres.TrainingHub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrainingHubTest {

    @Test
    void isFull() {
        TrainingHub tHub = new TrainingHub();
        tHub.addTrainee(new Trainee());
        Assertions.assertFalse(tHub.isFull());
    }
}