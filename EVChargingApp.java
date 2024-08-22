import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ChargingStation {
    private String name;
    private String location;
    private int totalSlots;
    private int availableSlots;

    public ChargingStation(String name, String location, int totalSlots) {
        this.name = name;
        this.location = location;
        this.totalSlots = totalSlots;
        this.availableSlots = totalSlots;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public boolean bookSlot() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        } else {
            return false;
        }
    }

    public void releaseSlot() {
        if (availableSlots < totalSlots) {
            availableSlots++;
        }
    }
}

class BookingSystem {
    private List<ChargingStation> stations;

    public BookingSystem() {
        this.stations = new ArrayList<>();
    }

    public void addStation(ChargingStation station) {
        stations.add(station);
    }

    public List<ChargingStation> findStationsByLocation(String location) {
        List<ChargingStation> filteredStations = new ArrayList<>();
        for (ChargingStation station : stations) {
            if (station.getLocation().equalsIgnoreCase(location)) {
                filteredStations.add(station);
            }
        }
        return filteredStations;
    }

    public boolean bookChargingSlot(String stationName) {
        for (ChargingStation station : stations) {
            if (station.getName().equalsIgnoreCase(stationName)) {
                return station.bookSlot();
            }
        }
        return false;
    }
}

public class EVChargingApp {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();

        // Adding some sample stations
        bookingSystem.addStation(new ChargingStation("Station A", "New York", 5));
        bookingSystem.addStation(new ChargingStation("Station B", "Los Angeles", 3));
        bookingSystem.addStation(new ChargingStation("Station C", "San Francisco", 4));
        bookingSystem.addStation(new ChargingStation("Station A", "India", 5));
        bookingSystem.addStation(new ChargingStation("Station B", "Andhra Pradesh", 3));
        bookingSystem.addStation(new ChargingStation("Station C", "Delhi", 4));


        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the EV Charging Station Finder and Slot Booking System");

        while (true) {
            System.out.println("1. Find Charging Stations by Location");
            System.out.println("2. Book a Charging Slot");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    List<ChargingStation> stations = bookingSystem.findStationsByLocation(location);
                    if (stations.isEmpty()) {
                        System.out.println("No stations found in " + location);
                    } else {
                        System.out.println("Charging Stations in " + location + ":");
                        for (ChargingStation station : stations) {
                            System.out.println("Station: " + station.getName() + " | Available Slots: " + station.getAvailableSlots());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter station name: ");
                    String stationName = scanner.nextLine();
                    boolean success = bookingSystem.bookChargingSlot(stationName);
                    if (success) {
                        System.out.println("Slot successfully booked at " + stationName);
                    } else {
                        System.out.println("Booking failed! No available slots at " + stationName);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the EV Charging Station Finder and Slot Booking System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
