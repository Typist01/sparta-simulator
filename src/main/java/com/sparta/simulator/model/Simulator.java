package com.sparta.simulator.model;

import java.util.Arrays;
import java.util.Queue;

public class  Simulator {
	private final Intake intake;

	private int totalTrainees;
	private int waitingList;
	private int openCenters;
	private int fullCenters;

	private final int CENTRE_GENERATION_INTERVAL = 2; // centre generation interval (months) times 4 we are working in weeks, 4 weeks in a month,
	// about the
	private final int totalDuration; // Total duration of the simulation (months).
	private int currentMonth=1;



	public Simulator(int totalDuration){
		this.intake = new Intake();
		this.totalDuration = totalDuration;
	}
	//Will probably return some results back to the controller so may not be void.
	public void tick(){
		//Generate new Trainees through intake.
		intake.addTraineeGroup();
		intake.addWaitingTraineesToCentre();
		intake.closingCenters();
		if (currentMonth > 12){
			//generate 1 to 5 Clients
			intake.addClients();
		}
		if (currentMonth % 12 == 0 && currentMonth !=12){//runs at the end of every year except year 1
			//remove unsatisfied clients here
			intake.removeUnsatClients();
		}

	}

	public void run(){
		if(currentMonth<totalDuration+1)
			if(currentMonth % CENTRE_GENERATION_INTERVAL == 0 && currentMonth > 1){
				//Generate new TrainingCentre through Intake then tick.
//				System.out.println("generating centres");
				intake.createCentresRandomly();
			}
		tick();
		currentMonth++;
		intake.incrementTimeTrained(); //for incrementing trainee months trained
		intake.benchTrainee();

		this.totalTrainees=intake.numOfTotalTrainees();
		this.openCenters=intake.numOfOpenCentres();
		this.waitingList=intake.getWaitingCount();
		this.fullCenters=intake.numOfFullCentres();
	}

	//_______________GETTERS_________________
	public int getTotalTrainees() {
		return totalTrainees;
	}

	public int getWaitingList() {
		return waitingList;
	}

	public int getOpenCenters() {
		return openCenters;
	}

	public int getFullCenters() {
		return fullCenters;
	}
	public int getTotalDuration() {
		return totalDuration;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}
	public Intake getIntake() {
		return intake;
	}

}
