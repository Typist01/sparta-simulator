package com.sparta.simulator;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Intake {
	Collection trainingCentres = new ArrayList();

	private int waitingList = 0;

	public int getWaitingList() {
		return waitingList;
	}

	public void addCentre(){
		TrainingCentre.add(new TrainingCentre());
	}
	public int getTrainees(){
		int sum = 0;
		for (TrainingCentre centre: trainingCentres){
			sum += centre.getTraineeCount();
		}
		return sum;
	}

	public int getFullCentresNum(){
		int sum = 0;
		for (TrainingCerntre centre: trainingCentres){
			if (centre.isFull()){
				sum ++;
			}
		}
		return sum;
	}

	public int openCentres(){
		return trainingCentres.size();
	}
	public int traineesInTraining(){
		int sum = 0;
		for (TrainingCerntre centre: trainingCentres){
			sum +=centre.getTraineeCount();
		}
		return sum;
	}


	public void addTraineeGroup(){
		for (TrainingCentre centre: trainingCentres){
			int randomNum = new Random().nextInt(50);
			if (randomNum<= waitingList){
				centre.addTrainees(randomNum);
				waitingList -= randomNum;
			}
		}
	}


}
