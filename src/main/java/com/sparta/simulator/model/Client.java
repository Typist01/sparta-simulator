package com.sparta.simulator.model;

import java.util.ArrayList;
// class for creating Clients, each requiring trainees of a specific course type.
public class Client {
	private final int maxTrainees;
	private final ArrayList<Trainee> traineeList;
	private final CourseType courseType;

	public Client(CourseType courseType, int amountOfTrainees){ //amountOfTrainees should be >= 15
		this.courseType = courseType;
		this.traineeList = new ArrayList<>();
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
	int numToTake(int numTrainees){ //returns number of free spaces this Client can take
		if (numTrainees + traineeList.size() > maxTrainees){
			numTrainees = numTrainees - (traineeList.size() - numTrainees);
		}
		return numTrainees;
	}

	CourseType getCourseType(){
		return courseType;
	}

	void clearTrainees(){
		traineeList.clear();
	}

	boolean checkSatisfaction(){	//check after a year //if true they come back next year
		return traineeList.size() == maxTrainees;
	}

	boolean addTrainee(Trainee trainee){
		if (maxTrainees >= traineeList.size() && trainee.getType().equals(courseType)) {
			return traineeList.add(trainee);
		} else {
			return false;
		}

	}

}
