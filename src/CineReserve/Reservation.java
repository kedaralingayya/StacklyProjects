package CineReserve;

public class Reservation {

    private int reservationId;
    private Viewer viewer;
    private FilmDetails film;
    private int seatNumber;
    private double amount;

    public Reservation(int reservationId, Viewer viewer,
                       FilmDetails film, int seatNumber,
                       double amount) {

        this.reservationId = reservationId;
        this.viewer = viewer;
        this.film = film;
        this.seatNumber = seatNumber;
        this.amount = amount;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void displayReservation() {

        System.out.println("\n-----------------------------");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Viewer         : "
                + viewer.getViewerName());
        System.out.println("Mobile         : "
                + viewer.getMobileNumber());
        System.out.println("Film           : "
                + film.getFilmName());
        System.out.println("Seat Number    : " + seatNumber);
        System.out.println("Amount         : ₹" + amount);
        System.out.println("-----------------------------");
}}
