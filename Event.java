import java.util.ArrayList;

//Class for creating a singular event with informations like title, description, location, time, member list, and date.
public class Event {
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private String eventTime;
    private String eventDate;
    private ArrayList<Integer> attendees;

    //Constructor for creating a singular event
    public Event(String title, String body, String place, String time, String date) {
        this.eventTitle = title;
        this.eventDescription = body;
        this.eventLocation = place;
        this.eventTime = time;
        this.eventDate = date;
        attendees = new ArrayList<Integer>();
    }

    //Method to assist with the check in feature, score report, and track application history.

    //method to add an attendee into the attendee list.
    public void addAttendee(int memberId) {
        attendees.add(memberId);
    }

    //method to remove an attendee from the attendee list.
    public void removeAttendee(int memberId) {
        attendees.remove(memberId);
    }

    //method to view the list of attendees.
    public void viewAttendeesList() {
        int value = 1;
        for (int attendee : attendees){
            System.out.println("Attendee " + value++ + ": memberID " + attendee);
        }
    }

    //This method will give the attendee list to the checkIn class.
    public ArrayList<Integer> getAttendees() {
        return attendees;
    }

    //Will be passed into the checkIn class to track the amount of attendees in total.
    public int countNumberOfAttendees() {
        return attendees.size();
    }

    //Display all details of the event.
    public String toString() {
        return eventTitle + "\n" + eventDescription + "\nLocated at " + eventLocation + "\nAt " + eventTime + " on " + eventDate;
    }

}
