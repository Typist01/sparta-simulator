package com.sparta.simulator.model;

import java.util.*;

public class Intake {
	enum centres {TRAINEE_HUB, BOOTCAMP, TECH_CENTRE}

	private final Collection<Centre> trainingCentres;
	private final Collection<Centre> closedCentres;
	private final Queue<Trainee> waitingList;
	private final ArrayList<Client> clientList;
	private final HashMap<CourseType,List<Trainee>> benchList;

	public Intake(){
		trainingCentres = new ArrayList<>();
		closedCentres = new ArrayList<>();
		waitingList = new LinkedList<>();
		clientList = new ArrayList<>();
		benchList = new HashMap<>();
		for (CourseType course:	CourseType.values() ) {
			benchList.put(course,new LinkedList<>());
		}
	}


	public Collection<Centre> getClosedCentres() {
		return closedCentres;
	}

	public ArrayList<Client> getClientList(){ return clientList; }

	public void addClients(){
		int randTrainees = new Random().nextInt(15,30); //for num of trainees
		int randCourse = new Random().nextInt(5); //for

		clientList.add(new Client(CourseType.values()[randCourse], randTrainees));

	}

	public void incrementTimeTrained(){
		for (Centre centre : trainingCentres) {
			for (Trainee trainee: centre.getTraineeList()) {
				trainee.incrementTimeTrained();
			}
		}

	}

	public void benchTrainee(){
		for (Centre centre: trainingCentres ) {
			for (Trainee trainee: centre.getTraineeList()) {
				if (trainee.getTimeTrained() == 3 ){	//checks if training for 3 months
					benchList.get(trainee.getType()).add(trainee);
				}
			}
		}
	}

	public int getWaitingCount() {
		return waitingList.size();
	}


	//returns centre object
	Centre generateCentre(centres centreType) throws GenerateCentreException {
		return switch (centreType) {
			case TRAINEE_HUB -> new TrainingHub();
			case BOOTCAMP -> new BootCamp();
			case TECH_CENTRE -> new TechCenter();
			default -> throw new GenerateCentreException("Could not determine centre type");
		};
	}

	// adds centre to list
	public void addCentre(centres name) {
		try {
			trainingCentres.add(generateCentre(name));
		} catch (GenerateCentreException e) {
			e.printStackTrace();
		}
	}

	// create new centres
	public void createCentresRandomly() {
		int randNum = new Random().nextInt(3);
		centres name;
		switch (randNum) {
			case (0):
				name = centres.TRAINEE_HUB;
				int centreNum = new Random().nextInt(3) + 1; // randomly generates 1/2/3
				for (int i = 0; i < centreNum; i++) {
					addCentre(name);
				}
			case (1):
				name = centres.BOOTCAMP;
				addCentre(name);
			case (2):
				name = centres.TECH_CENTRE;
				addCentre(name);
		}
	}

	public int numOfTotalTrainees() {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			sum += centre.getNumOfTrainees();
		}
		return sum;
	}

	public int numOfFullCentres() {
		return getFullCentres().size();
	}

	public int numOfOpenCentres() {
		return getOpenCentres().size();
	}

	//Add new random trainees to the waiting list.
	public void addTraineeGroup() {

		Queue<Trainee> intakeList = new LinkedList<>();
		for (int i = 0; i < RandGenerator.randomTrainee(); i++) {
			intakeList.add(new Trainee());
		}
/*
		for (Centre centre : trainingCentres) {
			if (!centre.isFull() && intakeList.size() > 0) {
				for (int i = 0; i < RandGenerator.randomCenter(); i++) {
					if (!centre.isFull()) {
						centre.addTrainee(intakeList.remove());
					}
				}
			}
		}
		REDUNDANT REQUIREMENT FROM PHASE 1 TO PHASE 2---DONT DELETE
		*/
		waitingList.addAll(intakeList);
	}


	public void addWaitingTraineesToCentre() {
		Random random = new Random();
		boolean allFull = false;
		while (waitingList.size() > 0 && !allFull) {
			allFull = true;
			for (Centre centre : trainingCentres) {
				if (!centre.isFull()) {
					allFull = false;
					if (centre.acceptsTrainee(waitingList.peek())){
						if (random.nextBoolean() && waitingList.size() > 0) {
							centre.addTrainee(waitingList.remove());
						}
					}
				}
			}
		}
	}

	public void closingCenters() {
		ArrayList<Trainee> spareTrainees = new ArrayList<>();
		List<Centre> centresToBeClosed = new ArrayList<>();
		for (Centre centre : trainingCentres) {
			if (centre.isClosable()) {
				spareTrainees.addAll(centre.getTraineeList());
				closedCentres.add(centre);
				centresToBeClosed.add(centre);
			}
		}
		trainingCentres.removeAll(centresToBeClosed);
		waitingList.addAll(spareTrainees);
	}

	private List<Centre> getOpenCentres() {
		List<Centre> openCentres = new ArrayList();
		for (Centre centre : trainingCentres) {
			if (centre.isFull()) {
				openCentres.add(centre);
			}
		}
		return openCentres;
	}

	private List<Centre> getFullCentres() {
		List<Centre> fullCentres = new ArrayList();
		for (Centre centre : trainingCentres) {
			if (!centre.isFull()) {
				fullCentres.add(centre);
			}
		}
		return fullCentres;
	}

	public int getFullCentreNumByType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName) && centre.isFull()) {
				sum++;
			}
		}
		return sum;
	}

	public int getNumTraineesByCentreType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {

			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName)) {
				sum += centre.getNumOfTrainees();
			}
		}
		return sum;
	}

	public int getCentreNumByType(String centreName) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreName)) {
				sum++;
			}
		}
		return sum;
	}


	public int getClosedCentresNum() {
		return closedCentres.size();
	}
	public int getClosedCentresNumByType(String centreType){
		int sum = 0;
		for (Centre centre: closedCentres){
			if (centre.getClass().getSimpleName().equalsIgnoreCase(centreType)){
				sum ++;
			}
		}
		return sum;
	}

	public int getClosedTechCentreNumByType(CourseType techCentreType){
		int sum = 0;
		for (Centre centre: closedCentres){
			if (centre instanceof TechCenter techCenter) {
				if(techCenter.getTechType().equals(techCentreType)){
					sum ++;
				}
				}
			}
		return sum;
	}

	public int getTechCentresTraineeNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.getTechType().equals(centreType))
					sum += techCenter.getNumOfTrainees();
			}
		}
		return sum;
	}

	public int getFullTechCentresNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.isFull() && techCenter.getTechType().equals(centreType))
					sum++;
			}
		}
		return sum;
	}

	public int getTechCentresNumByType(CourseType centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {

				if (techCenter.getTechType().equals(centreType))
					sum++;
			}
		}
		return sum;
	}

}
