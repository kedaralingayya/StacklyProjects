package CineReserve;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CineReservePgm {
	  static Scanner sc = new Scanner(System.in);

	    static ArrayList<FilmDetails> films =
	            new ArrayList<FilmDetails>();

	    static HashMap<Integer, Reservation> reservations =
	            new HashMap<Integer, Reservation>();

	    static ScreenHall hall = new ScreenHall();

	    static int reservationId = 1001;

	    static int viewerId = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  addFilms();

	        int choice;

	        do {

	            System.out.println("\n================================");
	            System.out.println("        CINE RESERVE");
	            System.out.println("   Movie Ticket Booking System");
	            System.out.println("================================");

	            System.out.println("1. Show Films");
	            System.out.println("2. Show Available Seats");
	            System.out.println("3. Reserve Ticket");
	            System.out.println("4. Cancel Reservation");
	            System.out.println("5. View Reservation");
	            System.out.println("6. Rate Film");
	            System.out.println("7. Exit");

	            System.out.println("================================");

	            System.out.print("Enter your choice: ");

	            choice = sc.nextInt();

	            switch (choice) {

	            case 1:

	                showFilms();

	                break;

	            case 2:

	                hall.showAvailableSeats();

	                break;

	            case 3:

	                reserveTicket();

	                break;

	            case 4:

	                cancelReservation();

	                break;

	            case 5:

	                viewReservation();

	                break;

	            case 6:

	                rateFilm();

	                break;

	            case 7:

	                System.out.println(
	                        "Thank you for using CineReserve.");

	                break;

	            default:

	                System.out.println(
	                        "Invalid choice.");
	            }

	        } while (choice != 7);

	        sc.close();
	    }

	    // Add films
	    public static void addFilms() {

	        films.add(
	                new FilmDetails(
	                        1,
	                        "Kantara",
	                        4.7));

	        films.add(
	                new FilmDetails(
	                        2,
	                        "KGF Chapter 2",
	                        4.5));

	        films.add(
	                new FilmDetails(
	                        3,
	                        "Toxic",
	                        4.6));

	        films.add(
	                new FilmDetails(
	                        4,
	                        "Om",
	                        4.8));
	    }

	    // Show films
	    public static void showFilms() {

	        System.out.println("\nAvailable Films:");

	        for (FilmDetails film : films) {

	            film.displayFilm();
	        }
	    }

	    // Reserve ticket
	    public static void reserveTicket() {

	        System.out.println("\n----- Ticket Reservation -----");

	        sc.nextLine();

	        System.out.print("Enter your name: ");

	        String name = sc.nextLine();

	        System.out.print("Enter mobile number: ");

	        String mobile = sc.nextLine();

	        Viewer viewer =
	                new Viewer(viewerId, name, mobile);

	        viewerId++;

	        showFilms();

	        System.out.print("Enter film ID: ");

	        int filmId = sc.nextInt();

	        FilmDetails selectedFilm = null;

	        for (FilmDetails film : films) {

	            if (film.getFilmId() == filmId) {

	                selectedFilm = film;

	                break;
	            }
	        }

	        if (selectedFilm == null) {

	            System.out.println("Film not found.");

	            return;
	        }

	        hall.showAvailableSeats();

	        System.out.print("Enter seat number: ");

	        int seatNumber = sc.nextInt();

	        if (!hall.isSeatAvailable(seatNumber)) {

	            System.out.println(
	                    "Sorry, this seat is already reserved.");

	            return;
	        }

	        boolean reserved =
	                hall.reserveSeat(seatNumber);

	        if (reserved) {

	            sc.nextLine();

	            // Weekend pricing
	            System.out.print(
	                    "Is today weekend? (yes/no): ");

	            String weekend =
	                    sc.nextLine();

	            double amount;

	            if (weekend.equalsIgnoreCase("yes")) {

	                amount = hall.getTicketPrice(true);

	            } else {

	                amount = hall.getTicketPrice(false);
	            }

	            // Discount coupon
	            System.out.print(
	                    "Do you have a coupon? (yes/no): ");

	            String couponChoice =
	                    sc.nextLine();

	            if (couponChoice.equalsIgnoreCase("yes")) {

	                System.out.print(
	                        "Enter coupon code: ");

	                String coupon =
	                        sc.nextLine();

	                if (coupon.equalsIgnoreCase("CINE10")) {

	                    amount =
	                            amount - (amount * 10 / 100);

	                    System.out.println(
	                            "10% discount applied.");

	                } else {

	                    System.out.println(
	                            "Invalid coupon code.");
	                }
	            }

	            Reservation reservation =
	                    new Reservation(
	                            reservationId,
	                            viewer,
	                            selectedFilm,
	                            seatNumber,
	                            amount);

	            reservations.put(
	                    reservationId,
	                    reservation);

	            System.out.println(
	                    "\nTicket reserved successfully!");

	            System.out.println(
	                    "Reservation ID: "
	                    + reservationId);

	            System.out.println(
	                    "Film: "
	                    + selectedFilm.getFilmName());

	            System.out.println(
	                    "Seat: "
	                    + seatNumber);

	            System.out.println(
	                    "Amount: ₹"
	                    + amount);

	            reservationId++;
	        }
	    }

	    // Cancel reservation
	    public static void cancelReservation() {

	        System.out.println(
	                "\n----- Cancel Reservation -----");

	        System.out.print(
	                "Enter reservation ID: ");

	        int id = sc.nextInt();

	        Reservation reservation =
	                reservations.get(id);

	        if (reservation == null) {

	            System.out.println(
	                    "Reservation not found.");

	            return;
	        }

	        int seatNumber =
	                reservation.getSeatNumber();

	        hall.cancelSeat(seatNumber);

	        reservations.remove(id);

	        System.out.println(
	                "Reservation cancelled successfully.");
	    }

	    // View reservation
	    public static void viewReservation() {

	        System.out.println(
	                "\n----- View Reservation -----");

	        System.out.print(
	                "Enter reservation ID: ");

	        int id = sc.nextInt();

	        Reservation reservation =
	                reservations.get(id);

	        if (reservation == null) {

	            System.out.println(
	                    "Reservation not found.");

	        } else {

	            reservation.displayReservation();
	        }
	    }

	    // Rate film
	    public static void rateFilm() {

	        System.out.println(
	                "\n----- Rate Film -----");

	        showFilms();

	        System.out.print(
	                "Enter film ID: ");

	        int filmId = sc.nextInt();

	        FilmDetails selectedFilm = null;

	        for (FilmDetails film : films) {

	            if (film.getFilmId() == filmId) {

	                selectedFilm = film;

	                break;
	            }
	        }

	        if (selectedFilm == null) {

	            System.out.println(
	                    "Film not found.");

	            return;
	        }

	        System.out.print(
	                "Enter rating between 1 and 5: ");

	        double rating = sc.nextDouble();

	        if (rating < 1 || rating > 5) {

	            System.out.println(
	                    "Rating should be between 1 and 5.");

	        } else {

	            selectedFilm.setRating(rating);

	            System.out.println(
	                    "Thank you! Rating submitted.");
	        }
	}

}
