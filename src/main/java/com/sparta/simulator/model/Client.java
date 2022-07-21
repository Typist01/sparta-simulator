package com.sparta.simulator.model;

import java.util.ArrayList;
import java.util.Random;

// class for creating Clients, each requiring trainees of a specific course type.
public class Client {
	private final int maxTrainees;
	private final ArrayList<Trainee> traineeList;
	private final CourseType courseType;
	private int monthCount = 0;

	public Client(){ //amountOfTrainees should be >= 15
		this.courseType = RandGenerator.generateRandomCourse();
		this.traineeList = new ArrayList<>();
		this.maxTrainees = RandGenerator.generateClientRequest();

	}
	public int numToTake(int numTrainees){ //returns number of free spaces this Client can take
		if (numTrainees + traineeList.size() > maxTrainees){
			numTrainees = numTrainees - (traineeList.size() - numTrainees);
		}
		return numTrainees;
	}
	public void increaseMonth(){
		monthCount++;
	}
	public CourseType getCourseType(){
		return courseType;
	}

	public void clearTrainees(){
		traineeList.clear();
	}

	public boolean checkSatisfaction(){	//check after a year //if true they come back next year
		return traineeList.size() == maxTrainees;
	}

	public boolean addTrainee(Trainee trainee){
		if (maxTrainees >= traineeList.size() && trainee.getType().equals(courseType)) {
			return traineeList.add(trainee);
		} else {
			return false;
		}

	}
	public int getMonthCount() {
		return monthCount;
	}

}
