package com.sparta.simulator.model;

import java.util.*;

public class Intake {
	enum centres {TRAINEE_HUB, BOOTCAMP, TECH_CENTRE}

	private final Collection<Centre> trainingCentres = new ArrayList();
	private final Collection<Centre> closedCentres = new ArrayList();
	private final Queue<Trainee> waitingList = new LinkedList<>();


	public Collection<Centre> getClosedCentres() {
		return closedCentres;
	}


	public int getWaitingList() {
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
		for (Centre centre : trainingCentres) {
			if (!centre.isFull() && intakeList.size() > 0) {
				for (int i = 0; i < RandGenerator.randomCenter(); i++) {
					if (!centre.isFull()) {
						centre.addTrainee(intakeList.remove());
					}
				}
			}
		}
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
					if (random.nextBoolean() && waitingList.size() > 0) {
						centre.addTrainee(waitingList.remove());
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

	public int getTechCentresNum(String centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.getTechType().equalsIgnoreCase(centreType))
					sum++;
			}
		}
		return sum;
	}

	public int getClosedCentresNum() {
		return closedCentres.size();
	}

	public int getTechCentresTraineeNumByType(String centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.getTechType().equalsIgnoreCase(centreType))
					sum += techCenter.getNumOfTrainees();
			}
		}
		return sum;
	}


	public int getFullTechCentresNumByType(String centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {
				if (techCenter.isFull() && techCenter.getTechType().equalsIgnoreCase(centreType))
					sum++;
			}
		}
		return sum;
	}

	public int getTechCentresNumByType(String centreType) {
		int sum = 0;
		for (Centre centre : trainingCentres) {
			if (centre instanceof TechCenter techCenter) {

				if (techCenter.getTechType().equalsIgnoreCase(centreType))
					sum++;
			}
		}
		return sum;
	}

}
