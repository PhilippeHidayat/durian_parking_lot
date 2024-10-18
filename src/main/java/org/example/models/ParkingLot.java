package org.example.models;

import java.util.List;

public class ParkingLot {
    private String parkingLotId;
    private List<ParkingLotFloor> parkingLotFloorList;

    public ParkingLot() {
    }

    public ParkingLot(String parkingLotId, List<ParkingLotFloor> parkingLotFloorList) {
        this.parkingLotId = parkingLotId;
        this.parkingLotFloorList = parkingLotFloorList;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<ParkingLotFloor> getParkingLotFloorList() {
        return parkingLotFloorList;
    }

    public void setParkingLotFloorList(List<ParkingLotFloor> parkingLotFloorList) {
        this.parkingLotFloorList = parkingLotFloorList;
    }
}
