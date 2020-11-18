package com.sqli.test.elevators;

public class Elevator {

    private String id;
    private int currentFloor;
    private String state;
    private boolean hasPriority = true;

    public boolean hasPriority() {
        return hasPriority;
    }

    public void setHasPriority(boolean hasPriority) {
        this.hasPriority = hasPriority;
    }

    public static class Builder {

        private String id;
        private int currentFloor;
        private String state;

        public Builder(String id, int currentFloor) {
            this.id = id;
            this.currentFloor = currentFloor;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withCurrentFloor(int currentFloor) {
            this.currentFloor = currentFloor;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }


        public Elevator build() {
            Elevator elevator = new Elevator();
            elevator.id = this.id;
            elevator.currentFloor = this.currentFloor;
            elevator.state = this.state;
            return elevator;
        }
    }


    private Elevator() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
