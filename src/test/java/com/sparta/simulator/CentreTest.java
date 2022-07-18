package com.sparta.simulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentreTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testIsFull(){
        Centre centre = new Centre();
        centre.addTrainee(new Trainee());
        centre.addTrainee(new Trainee());
        centre.addTrainee(new Trainee());

        Assertions.assertFalse(centre.isFull());
    }

    @Test
    void testNumOfTrainees(){
        Centre centre = new Centre();
        centre.addTrainee(new Trainee());
        centre.addTrainee(new Trainee());
        centre.addTrainee(new Trainee());

        Assertions.assertEquals(3, centre.getNumOfTrainees());
    }
}