public class Event {
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private String eventTime;
    private String eventDate;

    //Constructor for creating a singular event
    public Event(String title, String body, String place, String time, String date) {
        this.eventTitle = title;
        this.eventDescription = body;
        this.eventLocation = place;
        this.eventTime = time;
        this.eventDate = date;
    }
}
