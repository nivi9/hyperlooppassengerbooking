import java.util.*;

class Passenger {
    String name;
    int age;
    String destination;

    public Passenger(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }
}

class Route {
    String from;
    String to;
    int distance;

    public Route(String from, String to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}

public class HyperloopBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfRoutes = 0;
        String startingStation = null;
        List<Route> routes = new ArrayList<>();
        Queue<Passenger> passengerQueue = new LinkedList<>();

        while (scanner.hasNext()) { // Loop until there is more input
            String command = scanner.next(); 

            if (command.equals("INIT")) { // Initialize the system
                numOfRoutes = scanner.nextInt(); // Read the number of routes
                startingStation = scanner.next(); // Read the starting station
                scanner.nextLine(); // Consume the newline character
                for (int i = 0; i < numOfRoutes; i++) {
                    String from = scanner.next(); 
                    String to = scanner.next(); 
                    int distance = scanner.nextInt(); 
                    routes.add(new Route(from, to, distance)); // Add the route
                    scanner.nextLine(); 
                }
            } else if (command.equals("ADD_PASSENGER")) { // Add passengers to the queue
                int numPassengers = scanner.nextInt();
                scanner.nextLine(); 
                for (int i = 0; i < numPassengers; i++) {
                    String name = scanner.next(); 
                    int age = scanner.nextInt(); 
                    String destination = scanner.next(); 
                    passengerQueue.add(new Passenger(name, age, destination)); 
                    scanner.nextLine(); 
                }
            } else if (command.equals("START_POD")) { // Start pods and pick up passengers
                int numPods = scanner.nextInt(); // Read the number of pods to start
                scanner.nextLine(); 
                for (int i = 0; i < numPods; i++) {
                    startPod(passengerQueue, routes, startingStation); // Start a pod
                }
            } else if (command.equals("PRINT_Q")) { // Print the passenger queue
                int numToPrint = scanner.nextInt(); // Read the number of passengers to print
                scanner.nextLine(); 
                printQueue(passengerQueue, numToPrint); // Print the queue
            }
        }
    }

    private static void startPod(Queue<Passenger> passengerQueue, List<Route> routes, String startingStation) {
        List<Passenger> tempPassengers = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (Passenger passenger : passengerQueue) { // Iterate through passengers in the queue
            if (passenger.destination.equals(startingStation)) {
                tempPassengers.add(passenger); // Add passengers with the same starting station
                continue;
            }

            int distance = calculateDistance(routes, startingStation, passenger.destination);
            if (distance < minDistance) {
                minDistance = distance;
                tempPassengers.clear();
                tempPassengers.add(passenger);
            } else if (distance == minDistance) {
                tempPassengers.add(passenger);
            }
        }

        if (!tempPassengers.isEmpty()) { // If there are passengers to pick up
            Passenger oldestPassenger = findOldestPassenger(tempPassengers);
            System.out.println(oldestPassenger.name + " " + startingStation + " " + oldestPassenger.destination); // Print the pickup details
            passengerQueue.remove(oldestPassenger); // Remove the picked-up passenger from the queue
        }
    }

    private static int calculateDistance(List<Route> routes, String from, String to) {
        int distance = 0;
        for (Route route : routes) { // Find the distance between two stations
            if ((route.from.equals(from) && route.to.equals(to)) || (route.from.equals(to) && route.to.equals(from))) {
                distance = route.distance;
                break;
            }
        }
        return distance;
    }

    private static Passenger findOldestPassenger(List<Passenger> passengers) {
        Passenger oldest = passengers.get(0);
        for (Passenger passenger : passengers) { // Find the oldest passenger
            if (passenger.age > oldest.age) {
                oldest = passenger;
            }
        }
        return oldest;
    }

    private static void printQueue(Queue<Passenger> passengerQueue, int numToPrint) {
        List<Passenger> toPrint = new ArrayList<>();
        for (int i = 0; i < numToPrint && !passengerQueue.isEmpty(); i++) { 
            toPrint.add(passengerQueue.poll());
        }

        for (Passenger passenger : toPrint) {
            System.out.println(passenger.name + " " + passenger.age);
        }
    }
}
