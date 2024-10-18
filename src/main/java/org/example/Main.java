package org.example;

import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        String input = "create_parking_lot PR1234 2 6\n" +
                "display free_count CAR";


        for(int i=0; i< input.split("\n").length; i ++) {
            String[] command = input.split("\n")[i].split(" ");
            if(command[0].equals("create_parking_lot")) {
                createParkingLot(parkingLot, command[1], Integer.valueOf(command[2]), Integer.valueOf(command[3]));
            } else if(command[0].equals("park_vehicle")) {
                parkVehicle(parkingLot, VehicleType.valueOf(command[1]), command[2], command[3]);
            } else if(command[0].equals("unpark_vehicle")) {
                unparkVehicle(parkingLot, command[1]);
            } else if(command[0].equals("display")) {
                display(parkingLot, command[1], VehicleType.valueOf(command[2]));
            } else if(command[0].equals("exit")) {
                break;
            }
        }

    }


    private static void createParkingLot(ParkingLot parkingLot, String parkingLotId, Integer numberOffloor, Integer numberOfSlotPerFloor) {
        parkingLot.setParkingLotId(parkingLotId);
        List<ParkingLotFloor> parkingLotFloorList = new ArrayList<>();
        for(int i = 1; i< numberOffloor; i++) {
            List<ParkingSlot> parkingSlotList = new ArrayList<>();
            for(int j = 1; j < numberOfSlotPerFloor; j++) {
                VehicleType vehicleType = VehicleType.CAR;
                if(j == 1) {
                    vehicleType = VehicleType.TRUCK;
                }else if(j == 2 || j == 3) {
                    vehicleType = VehicleType.BIKE;
                }
                ParkingSlot parkingSlot = new ParkingSlot(
                        j, vehicleType

                );
                parkingSlotList.add(parkingSlot);
            }

            ParkingLotFloor parkingLotFloor = new ParkingLotFloor(
                    i, parkingSlotList
            );
            parkingLotFloorList.add(parkingLotFloor);
        }
        parkingLot.setParkingLotFloorList(parkingLotFloorList);
    }

    private static void parkVehicle(ParkingLot parkingLot, VehicleType vehicleType, String registrationNumber, String color) {
        boolean flag = false;

        for(int i = 0; i < parkingLot.getParkingLotFloorList().size(); i++) {
            ParkingLotFloor parkingLotFloor = parkingLot.getParkingLotFloorList().get(i);
            for(int j = 0; j < parkingLotFloor.getParkingSlotList().size(); j++) {
                ParkingSlot parkingSlot = parkingLotFloor.getParkingSlotList().get(j);
                if(parkingSlot.getVehicleType().equals(vehicleType) && parkingSlot.getVehicle() == null) {
                    Vehicle vehicle = new Vehicle(vehicleType, registrationNumber, color);
                    parkingLotFloor.getParkingSlotList().get(j).setVehicle(vehicle);
                    System.out.println("Parked vehicle. Ticket ID:" + parkingLot.getParkingLotId() +"_" + String.valueOf(i + 1) + "_" + String.valueOf(j + 1));
                    flag = true;
                }
            }
        }

        if(!flag) {
            System.out.println("Parking Lot Full");
        }
    }

    private static void unparkVehicle(ParkingLot parkingLot, String ticketId) {

        boolean flag = false;
        Integer floor = Integer.valueOf(ticketId.split("_")[1]);
        Integer slot = Integer.valueOf(ticketId.split("_")[2]);
        if(parkingLot.getParkingLotFloorList().get(floor - 1).getParkingSlotList().get(slot - 1).getVehicle() != null) {
            String registrationNumber = parkingLot.getParkingLotFloorList().get(floor - 1).getParkingSlotList().get(slot - 1).getVehicle().getRegistrationNumber();
            String color = parkingLot.getParkingLotFloorList().get(floor - 1).getParkingSlotList().get(slot - 1).getVehicle().getColor();
            parkingLot.getParkingLotFloorList().get(floor - 1).getParkingSlotList().get(slot - 1).setVehicle(null);
            System.out.println("Unparked vehicle with Registration Number: "+registrationNumber+" and Color: "+color);
            flag = true;
        }

        if(!flag) {
            System.out.println("Invalid Ticket");
        }
    }

    private static void display(ParkingLot parkingLot, String displayType, VehicleType vehicleType) {
        for(int i = 0 ; i < parkingLot.getParkingLotFloorList().size(); i++) {
            List<Integer> slotNo = new ArrayList<>();

            ParkingLotFloor parkingLotFloor = parkingLot.getParkingLotFloorList().get(i);
            for(int j = 0; j < parkingLotFloor.getParkingSlotList().size(); j++) {
                ParkingSlot parkingSlot = parkingLotFloor.getParkingSlotList().get(j);
                if(parkingSlot.getVehicleType().equals(vehicleType)) {
                    if((displayType.equals("free_count") || displayType.equals("free_slots")) && parkingSlot.getVehicle() == null) {
                        slotNo.add(j+1);
                    } else if(displayType.equals("occupied_slots") && parkingSlot.getVehicle() != null) {
                        slotNo.add(j+1);
                    }
                }

                if(displayType.equals("free_count")) {
                    System.out.println("No. of free slots for "+vehicleType.name()+" on Floor "+String.valueOf(i+1)+": " +String.valueOf(slotNo.size()));
                } else if(displayType.equals("free_slots")) {
                    String slotNoString = slotNo.stream().map(String::valueOf)
                            .collect(Collectors.joining(","));
                    System.out.println("Free slots for "+vehicleType.name()+" on Floor "+String.valueOf(i+1)+" : " + slotNoString);
                } else if(displayType.equals("occupied_slots")) {
                    String slotNoString = slotNo.stream().map(String::valueOf)
                            .collect(Collectors.joining(","));
                    System.out.println("Occupied slots for "+vehicleType.name()+" on Floor "+String.valueOf(i+1)+" : " + slotNoString);
                }
            }
        }
    }
}