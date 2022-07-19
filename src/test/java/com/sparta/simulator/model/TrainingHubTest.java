package com.sparta.simulator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainingHubTest {

    @Test
    void isFull() {
        TrainingHub tHub = new TrainingHub();
        tHub.addTrainee(new Trainee());
        Assertions.assertFalse(tHub.isFull());
    }
}