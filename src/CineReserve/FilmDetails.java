package CineReserve;

public class FilmDetails {

	  private int filmId;
	    private String filmName;
	    private double rating;

	    public FilmDetails(int filmId, String filmName, double rating) {
	        this.filmId = filmId;
	        this.filmName = filmName;
	        this.rating = rating;
	    }

	    public int getFilmId() {
	        return filmId;
	    }

	    public String getFilmName() {
	        return filmName;
	    }

	    public double getRating() {
	        return rating;
	    }

	    public void setRating(double rating) {
	        this.rating = rating;
	    }

	    public void displayFilm() {

	        System.out.println(
	                filmId + " - " + filmName
	                + " | Rating: " + rating + "/5");

	}

}
