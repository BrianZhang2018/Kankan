package OOD;

import java.util.*;
/**
 *
 * Created by brianzhang on 4/30/21.
 */
public class Elevator {
    public static void main(String[] args) {
        Elevator theElevator = new Elevator(10);
        theElevator.boardPassenger(2);
        theElevator.boardPassenger(2);
        theElevator.boardPassenger(3);

        System.out.println(theElevator);

        for(int i=1;i<=20;i++){
            theElevator.move();
        }
    }

    enum DirectionOfTravel {UP, DOWN, STOPPED}
    public static final int MAXIMUM_CAPACITY = 10;
    public static int NUMBER_OF_FLOORS = 7;
    public List<Floor> floors;

    private DirectionOfTravel _directionOfTravel;
    private Floor _currentFloor;
    private int _passengersOnboard;
    private Map _floorQueue;

    public Elevator(int stories) {
        this.NUMBER_OF_FLOORS = stories;
        this.floors = initiateFloors(stories);
        _currentFloor = this.floors.get(1);
        _passengersOnboard = 0;
        _directionOfTravel = DirectionOfTravel.UP;
    }

    public List<Floor> initiateFloors(int stories){
        List<Floor> floors = new ArrayList<>(stories+1);
        floors.add(0, null);
        for(int i=1; i<=stories; i++){
            floors.add(i, new Floor(i, 0, false));
        }
        return floors;
    }

    /**
     * The move method progresses the elevator up and down. If the elevator is on the first floor
     * it sets the direction of travel to UP.  If the elevator is on the top floor, it sets the
     * direction of travel to DOWN.  If there are pending destination requests, the elevator will stop.
     */
    public void move() {
        if (_currentFloor._number == 1) {
            _directionOfTravel = DirectionOfTravel.UP;
        }
        if (_currentFloor._number == floors.size()) {
            _directionOfTravel = DirectionOfTravel.DOWN;
        }

        if (_directionOfTravel == DirectionOfTravel.UP && _currentFloor._number +1 <= NUMBER_OF_FLOORS) {

            _currentFloor = floors.get(_currentFloor._number+1);
        } else if (_directionOfTravel == DirectionOfTravel.DOWN &&  _currentFloor._number - 1 >= 1) {
            _currentFloor = floors.get(_currentFloor._number-1);
        }

        if(_currentFloor.hasDestinationRequests()){
            stop();
        }
    }

    // Stop the elevator, disembarking passengers on board. Clears the flag that marks a floor as having queued passengers.
    private void stop() {
        System.out.println("Stopping on "+ _currentFloor._number +" floor " );
        _currentFloor.clearDestinationRequest();
        _passengersOnboard = _passengersOnboard - _currentFloor.queuedPassengers();
        System.out.println("disembark " + _currentFloor.queuedPassengers() + " passengers");
        _currentFloor.clearQueuedPassengers();
        System.out.println(this);
    }

    /**
     * boardPassenger
     * Adds the passenger to the list of passengers on the elevator.
     * @param floor		The integer representing the floor the passenger is requesting
     */
    public void boardPassenger(int floor){
        if(floor > NUMBER_OF_FLOORS || (_passengersOnboard +1 > MAXIMUM_CAPACITY))
            return; // or put it into queue

        _passengersOnboard++;
        Floor f = floors.get(floor);
        f.makeDestinationRequest();
        f.addQueuedPassenger();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Currently " + _passengersOnboard + " Passengers Onboard\r\n");
        output.append("On Floor   : " +_currentFloor._number  + "\r\n");
        return output.toString();

    }
}

// Floor encapsulates all the logic and data surrounding floors.
class Floor {

    public final int _number;
    public int _queuedPassengers;
    public boolean _hasDestinationRequests;
    public Floor _nextUp;
    public Floor _nextDown;

    /**
     * Default enumeration constructor
     * @param queuedPax				Number of passengers queued for that floor
     * @param hasDestinationRequests	Boolean indicator of whether the floor has destination requests pending
     */
    public Floor(int floorNum, int queuedPax, boolean hasDestinationRequests) {
        this._number = floorNum;
        this._queuedPassengers = queuedPax;
        this._hasDestinationRequests = hasDestinationRequests;
    }

    public int floorNumber() {
        return _number;
    }

    public int queuedPassengers() {
        return _queuedPassengers;
    }

    public void addQueuedPassenger(){
        this._queuedPassengers +=1;
    }

    public void clearQueuedPassengers(){
        this._queuedPassengers = 0;
    }

    public void makeDestinationRequest() {
        this._hasDestinationRequests = true;
    }

    public void clearDestinationRequest() {
        this._hasDestinationRequests = false;
    }

    public boolean hasDestinationRequests(){
        return this._hasDestinationRequests;
    }
}