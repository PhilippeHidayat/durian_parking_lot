package org.example.models;

import java.util.List;

public class ParkingLotFloor {
    private Integer parkingLotId;
    private List<ParkingSlot> parkingSlotList;

    public ParkingLotFloor() {
    }

    public ParkingLotFloor(Integer parkingLotId, List<ParkingSlot> parkingSlotList) {
        this.parkingLotId = parkingLotId;
        this.parkingSlotList = parkingSlotList;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<ParkingSlot> getParkingSlotList() {
        return parkingSlotList;
    }

    public void setParkingSlotList(List<ParkingSlot> parkingSlotList) {
        this.parkingSlotList = parkingSlotList;
    }
}
