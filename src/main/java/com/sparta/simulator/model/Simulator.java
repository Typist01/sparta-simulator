package com.sparta.simulator.model;

public class  Simulator {
	private Intake intake;
	private int totalTrainees;
	private int waitingList;
	private int openCenters;
	private int fullCenters;

	private final int CENTRE_GENERATION_INTERVAL = 2; // centre generation interval (months) times 4 we are working in weeks, 4 weeks in a month,
	// about the
	private final int totalDuration; // Total duration of the simulation (months).

	public Simulator(int totalDuration){
		this.intake = new Intake();
		this.totalDuration = totalDuration;
	}
	//Will probably return some results back to the controller so may not be void.
	public void tick(){
		//Generate new Trainees through intake.
		intake.addWaitingTraineesToCentre();
		intake.addTraineeGroup();
		intake.closingCenters();

	}

	public void run(){
		for (int i = 1; i< totalDuration+1; i++){
			if(i % CENTRE_GENERATION_INTERVAL == 0 && i > 1){
				//Generate new TrainingCentre through Intake then tick.
				intake.addCentre("centre name");
			}
			tick();
		}
		this.totalTrainees=intake.numOfTotalTrainees();
		this.openCenters=intake.numOfOpenCentres();
		this.waitingList=intake.getWaitingList();
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
}
