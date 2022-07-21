package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.Trainee;
import com.sparta.simulator.model.centres.BootCamp;
import com.sparta.simulator.model.centres.Centre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootCampTest {
	private Centre centre;
	@BeforeEach
	void setup(){
		centre = new BootCamp();
	}

	@Test
	void isClosable() {
		centre = new BootCamp();
		//Test is closable for 3 consecutive months.
		for (int i = 0; i < 2; i++) {
			//Shouldnt be closable for first two months.
			assertFalse(centre.isClosable());
		}
		//Will be closable on the third month.
		assertTrue(centre.isClosable());

		centre = new BootCamp();
		for (int i = 0; i < 25; i++) {
			assertTrue(centre.addTrainee(new Trainee()));
		}
		for (int i = 0; i < 2; i++) {
			//Shouldnt be closable for first 2 months
			assertFalse(centre.isClosable());
		}
		assertTrue(centre.addTrainee(new Trainee()));
		//Shouldnt be closable since it has hit the minimum amount of trainees.
		assertFalse(centre.isClosable());

	}

	@Test
	void isFull(){
		//Bootcamp is empty so shouldnt be full.
		assertFalse(centre.isFull());
		for (int i = 0; i<500;i++){
			if (i<499){
				assertFalse(centre.isFull());
			}
			//can add up to 500 trainees
			assertTrue(centre.addTrainee(new Trainee()));
		}
		//bootcamp should be full
		assertTrue(centre.isFull());
		//Shouldnt be able to add another employee.
		assertFalse(centre.addTrainee(new Trainee()));
		//Check is still full
		assertTrue(centre.isFull());
	}
	@Test
	void acceptsTrainee(){
		Trainee trainee = new Trainee();
		assertTrue(centre.acceptsTrainee(trainee));

	}

}