package org.example.models;

public class ParkingSlot {
    private Integer parkingSlotId;
    private VehicleType vehicleType;
    private Vehicle vehicle;

    public ParkingSlot() {
    }

    public ParkingSlot(Integer parkingSlotId, VehicleType vehicleType) {
        this.parkingSlotId = parkingSlotId;
        this.vehicleType = vehicleType;
    }

    public Integer getParkingSlotId() {
        return parkingSlotId;
    }

    public void setParkingSlotId(Integer parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
