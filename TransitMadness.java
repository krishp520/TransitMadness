/* 
 * Author: Krish Patel
 * Date: <25/02/2024>
 */
import java.util.*;

public class TransitMadness {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // Input: number of buses
    int numberOfBuses = sc.nextInt();

    // Initialize buses and door counts
    Deque<Deque<String>> buses = new LinkedList<>();
    Deque<Integer> busDoorCounts = new LinkedList<>();

    // Input: number of doors for each bus
    for (int i = 0; i < numberOfBuses; i++) {
      busDoorCounts.add(sc.nextInt());
      buses.add(new LinkedList<>()); // Initialize empty passenger queue for each bus
    }

    // Input: number of manifest lines
    int manifestLines = sc.nextInt();

    // Process manifest instructions
    for (int i = 0; i < manifestLines; i++) {
      String input = sc.next();

      switch (input) {
        case "loop":
          rotateBuses(buses, busDoorCounts);
          break;

        case "depart":
          departBus(buses, busDoorCounts);
          break;

        default: // Treat as a passenger name
          addPassenger(buses, busDoorCounts, input);
          break;
      }
    }
  }

  /**
   * Rotate buses in a circular manner.
   */
  private static void rotateBuses(Deque<Deque<String>> buses, Deque<Integer> busDoorCounts) {
    if (!buses.isEmpty()) {
      buses.addLast(buses.pollFirst());
      busDoorCounts.addLast(busDoorCounts.pollFirst());
    }
  }

  /**
   * Depart the first bus and print passenger names.
   */
  private static void departBus(Deque<Deque<String>> buses, Deque<Integer> busDoorCounts) {
    if (!buses.isEmpty()) {
      Deque<String> currentBusPassengers = buses.pollFirst();
      while (!currentBusPassengers.isEmpty()) {
        System.out.println(currentBusPassengers.poll());
      }
      busDoorCounts.pollFirst();
    }
  }

  /**
   * Add a passenger to the appropriate bus.
   */
  private static void addPassenger(Deque<Deque<String>> buses, Deque<Integer> busDoorCounts, String passengerName) {
    if (!buses.isEmpty()) {
      Deque<String> currentBusPassengers = buses.peekFirst();
      int doorCount = busDoorCounts.peekFirst();

      if (doorCount == 2) {
        currentBusPassengers.addLast(passengerName); // Two-door bus (FIFO)
      } else {
        currentBusPassengers.addFirst(passengerName); // One-door bus (LIFO)
      }
    }
  }
}
