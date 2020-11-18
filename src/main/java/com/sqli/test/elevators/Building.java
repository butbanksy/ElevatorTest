package com.sqli.test.elevators;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Building {

    /**
     * @param numberOfFloors: the number of floors in the building
     * @param elevators:      an array of descriptions of the elevators of the building. A description has
     * the following format "[elevator_id]:[elevator_current_floor]".
     */

    private int numberOfFloors;
    private String[] elevators;
    List<Elevator> elevatorsArray = new ArrayList<>();


    public Building(int numberOfFloors, String... elevators) {
        this.numberOfFloors = numberOfFloors;
        this.elevators = elevators;


        // We loop over every elevator, then create a new elevator object and add it to our list of elevators
        for (String elevatorString : elevators) {
            //the variable parts receive two parts, the id(string) and the currentFloor(int). The elevator is resting by default
            String[] parts = elevatorString.split(":");
            Elevator elevator = new Elevator.Builder(parts[0], Integer.parseInt(parts[1])).withState("REST").build();

            elevatorsArray.add(elevator);
        }
    }

    /**
     * Request an elevator at floor zero.
     *
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator() {

        Elevator elevator = elevatorsArray.stream()
                .filter(e -> e.getState().equals("REST") || e.getState().equals("DOWN") && e.hasPriority())
                .min(Comparator.comparing(Elevator::getCurrentFloor))
                .orElseThrow(NoSuchElementException::new);


        return elevator.getId();
    }

    /**
     * Request an elevator at floor indicate by the {@code floor} param.
     *
     * @param floor : the floor where the request is made.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator(int floor) {
        Elevator elevator = elevatorsArray.stream()
                .filter(Elevator::hasPriority)
                .min(Comparator.comparing(e -> Math.abs(e.getCurrentFloor() - floor)))
                .orElseThrow(NoSuchElementException::new);

        return elevator.getId();

    }

    /**
     * Request the elevator with the id {@code elevatorId} to stop at the floor indicated by the
     * {@code floor} param.
     *
     * @param elevatorId : the id of the elevator to whom give the order.
     * @param floor      : the floor at which the elevator should stop
     */
    public void stopAt(String elevatorId, int floor) {
        for (Elevator elevator : elevatorsArray) {
            if (elevator.getId().equals(elevatorId)) {
                elevator.setCurrentFloor(floor);
                elevator.setHasPriority(false);
                break;
            }
        }
    }

    /**
     * Move the elevator with the id {@code elevatorId} in the direction indicated by the {@code
     * direction} param.
     *
     * @param elevatorId : the id of the elevator to move.
     * @param direction  : the direction to go. Can be "UP" or "DOWN".
     */
    public void move(String elevatorId, String direction) {
        for (Elevator elevator : elevatorsArray) {
            if (elevator.getId().equals(elevatorId)) {
                switch (direction) {
                    case "UP":
                        elevator.setState("UP");
                        break;
                    case "DOWN":
                        elevator.setState("DOWN");
                        break;
                }
                break;
            }
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String[] getElevators() {
        return elevators;
    }

    public void setElevators(String[] elevators) {
        this.elevators = elevators;
    }


}
