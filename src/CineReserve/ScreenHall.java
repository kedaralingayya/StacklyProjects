package CineReserve;
import java.util.ArrayList;
public class ScreenHall {
	 private int totalSeats = 20;

	    private ArrayList<Integer> availableSeats =
	            new ArrayList<Integer>();

	    private ArrayList<Integer> reservedSeats =
	            new ArrayList<Integer>();

	    public ScreenHall() {

	        for (int i = 1; i <= totalSeats; i++) {

	            availableSeats.add(i);
	        }
	    }

	    // Display available seats
	    public void showAvailableSeats() {

	        System.out.println("\nAvailable Seats:");

	        if (availableSeats.isEmpty()) {

	            System.out.println("No seats available.");
	            return;
	        }

	        for (Integer seat : availableSeats) {

	            System.out.print(seat + " ");
	        }

	        System.out.println();
	    }

	    // Check seat
	    public boolean isSeatAvailable(int seatNumber) {

	        return availableSeats.contains(seatNumber);
	    }

	    // Reserve seat
	    public boolean reserveSeat(int seatNumber) {

	        if (availableSeats.contains(seatNumber)) {

	            availableSeats.remove(Integer.valueOf(seatNumber));

	            reservedSeats.add(seatNumber);

	            return true;
	        }

	        return false;
	    }

	    // Cancel reservation
	    public boolean cancelSeat(int seatNumber) {

	        if (reservedSeats.contains(seatNumber)) {

	            reservedSeats.remove(Integer.valueOf(seatNumber));

	            availableSeats.add(seatNumber);

	            return true;
	        }

	        return false;
	    }

	    // Ticket price
	    public double getTicketPrice(boolean weekend) {

	        double price = 200;

	        if (weekend) {

	            price = 250;
	        }

	        return price;
}}
