package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

public class Client {
	private int maxTrainees;
	private final ArrayList<Trainee> currentTrainees = new ArrayList();
	private CourseType courseType;

	public Client(CourseType courseType, int amountOfTrainees){ //amountOfTrainees should be >= 15
		this.courseType = courseType;
		maxTrainees = amountOfTrainees;

	}

	/*void takeTrainees(){ //takes between 1 and maxTrainees
		Random rand = new Random();
		int numTaking = rand.nextInt(1, maxTrainees);
		if (numTaking + currentTrainees.size() > maxTrainees){
			numTaking = numTaking - (currentTrainees.size() - numTaking); //this makes sure it doesn't take more than it should
		}
		for (int i = 0; i < numTaking; i++) {
			//call addTrainee here
		}
	}*/

	boolean checkSatisfaction(){	//check after a year //if true they come back next year
		return currentTrainees.size() == maxTrainees;
	}

	boolean addTrainee(Trainee trainee){
		if (maxTrainees >= currentTrainees.size() && trainee.getType().equals(courseType)) {
			return currentTrainees.add(trainee);
		}else {
			return false;
		}

	}

}
