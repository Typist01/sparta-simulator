package com.sparta.simulator.model;

import java.util.ArrayList;

public class Client {
	private final int maxTrainees;
	private final ArrayList<Trainee> traineeList;
	private final CourseType courseType;

	public Client(CourseType courseType, int amountOfTrainees){ //amountOfTrainees should be >= 15
		this.courseType = courseType;
		this.traineeList = new ArrayList<>();
		maxTrainees = amountOfTrainees;

	}
	public int numToTake(int numTrainees){ //returns number of free spaces this Client can take
		if (numTrainees + traineeList.size() > maxTrainees){
			numTrainees = numTrainees - (traineeList.size() - numTrainees);
		}
		return numTrainees;
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

}
