package OOD;

import java.util.Date;

/**
 * Created by brianzhang on 7/30/19.
 */
enum VehicleSize {
    SMALL, // size 1,  Motorcycle, car
    COMPACT, // size 2, SUV
    LARGE, // size 3, BUS
}

abstract class Vehicle {
    String plateNumber;
    VehicleSize size;
    int spaceNeed;
    ParkingSpot parkingSpot;

    public Vehicle(int spaceNeed, VehicleSize size){
        this.spaceNeed = spaceNeed;
        this.size = size;
    }

    public void enterIn(ParkingSpot spot) {
        this.parkingSpot = spot;
    }
    public void moveOut (){
        this.parkingSpot = null;
    }

    public ParkingSpot getParkingSpot (){
       return this.parkingSpot;
    }
    public abstract boolean canFitInSpot (ParkingSpot spot);
}

class Large extends Vehicle{
    public Large () {
        super(3, VehicleSize.LARGE);
    }
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.LARGE;
    }
}

class Compact extends Vehicle {
    public Compact() {
        super(2, VehicleSize.COMPACT);
    }
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.COMPACT;
    }
}

class Small extends Vehicle {
    public Small () {
        super(1, VehicleSize.SMALL);
    }
    public boolean canFitInSpot(ParkingSpot spot){
        return true;
    }
}

class ParkingSpot {
    Vehicle vehicle;
    VehicleSize size;
    int row;
    Level level;
    int spotNumber;
/*
    Date move_in;
    Date move_out;
*/
    ParkingSpot (Level level, int row, int spotNumber, VehicleSize size) {
        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        this.size = size;
    }

    public boolean park (Vehicle vehicle) {
        if (!canFitVehicle(vehicle)) {
            return false;
        }
        this.vehicle = vehicle;
        vehicle.enterIn(this);
        return true;
    }

    public VehicleSize getSize () {
        return size;
    }

    public void removeVehicle () {
        level.spotFreed();
        vehicle = null;
    }

    public boolean isAvailable () {
        return vehicle == null;
    }
    public boolean canFitVehicle (Vehicle vehicle) {
        return isAvailable() && vehicle.canFitInSpot(this);
    }
}

class Level {
    int floor;
    ParkingSpot[] spots;
    int availableSpot = 0;

    public Level (int floor, int num_row, int spots_per_row) {
        this.floor = floor;
        int spot_Index = 0;
        spots = new ParkingSpot[num_row * spots_per_row];

        //init size for each spot in array spots
        for (int row = 0; row < num_row; row++) {
            for (int spot = 0; spot < spots_per_row / 4; spot++) {
                VehicleSize mc = VehicleSize.SMALL;
                spots[spot_Index] = new ParkingSpot(this, row, spot_Index, mc);
                spot_Index += 1;
            }
            for (int spot = spots_per_row / 4; spot < spots_per_row / 4 * 3; ++spot) {
                VehicleSize car = VehicleSize.COMPACT;
                spots[spot_Index] = new ParkingSpot(this, row, spot_Index, car);
                spot_Index ++;
            }
            for (int spot = spots_per_row / 4 * 3; spot < spots_per_row; ++spot) {
                VehicleSize bus = VehicleSize.LARGE;
                spots[spot_Index] = new ParkingSpot(this, row, spot_Index, bus);
                spot_Index ++;
            }
        }
        availableSpot = spot_Index;
    }

    public void spotFreed () {
        availableSpot += 1;
    }

}

public class ParkingLot {
    Level[] levels;
    int num_Levels;

    public ParkingLot (int n, int num_rows, int spots_pre_row) {
        this.num_Levels = n;
        levels = new Level[n];
        for (int i = 0; i < n; i++) {
            levels[i] = new Level(i, num_rows, spots_pre_row);
        }
    }

    public boolean parkVehicle (Vehicle vehicle) {
        for (int i = 0; i < levels.length; i++) {
            for(int j=0; j < levels[i].availableSpot; j++){
                if (levels[i].spots[j].park(vehicle)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void unParkVehicle (Vehicle vehicle) {
        vehicle.getParkingSpot().removeVehicle();
        vehicle.moveOut();
    }
}