package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.RandGenerator;
import com.sparta.simulator.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TechCenterTest {
	private Centre centre;
	@BeforeEach
	void setup(){
		centre = new TechCenter();
	}

	@Test
	void isClosable() {
		for (int i = 0; i < 25; i++) {
			if (i<24){
				assertTrue(centre.isClosable());
			}
			assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
		}
		assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
		//Shouldnt be closable since it has hit the minimum amount of trainees.
		assertFalse(centre.isClosable());
	}

	@Test
	void isFull(){
		//Bootcamp is empty so shouldnt be full.
		assertFalse(centre.isFull());
		for (int i = 0; i<200;i++){
			if (i<199){
				assertFalse(centre.isFull());
			}
			//can add up to 500 trainees
			assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
		}
		//bootcamp should be full
		assertTrue(centre.isFull());
		//Shouldnt be able to add another employee.
		assertFalse(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
		//Check is still full
		assertTrue(centre.isFull());
	}

	@Test
	void acceptsTrainee(){
		TechCenter techCenter = (TechCenter) centre;
		//Generate some trainees and see if the tech center will accept them.
		for (int i = 0; i < 50; i++) {
			Trainee trainee = new Trainee(RandGenerator.generateCourse());
			if (trainee.getType().equals(techCenter.getTechType())){
				assertTrue(techCenter.acceptsTrainee(trainee));
			}else {
				assertFalse(techCenter.acceptsTrainee(trainee));
			}
		}
	}


}