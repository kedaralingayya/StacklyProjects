package CineReserve;

public class Viewer {
	

	    private int viewerId;
	    private String viewerName;
	    private String mobileNumber;

	    public Viewer(int viewerId, String viewerName, String mobileNumber) {

	        this.viewerId = viewerId;
	        this.viewerName = viewerName;
	        this.mobileNumber = mobileNumber;
	    }

	    public int getViewerId() {
	        return viewerId;
	    }

	    public String getViewerName() {
	        return viewerName;
	    }

	    public String getMobileNumber() {
	        return mobileNumber;
	    }

	    public void displayViewer() {

	        System.out.println("Viewer ID : " + viewerId);
	        System.out.println("Name      : " + viewerName);
	        System.out.println("Mobile    : " + mobileNumber);
}
}