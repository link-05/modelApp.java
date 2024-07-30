/** This class is for checking in attendees who joined an event.
/* It will track each member in the event through taking the member ID of all the attendees.
 * The id will be how each member is checking in with the event.
 */

 import java.util.ArrayList;

enum EventStatus {
    AWAITING,
    CONFIRMED,
    ABSENT;
}
public class CheckIn {
    private int totalMembers;
    private int membersAwaiting;
    private int membersConfirmed;
    private int membersAbsent;
    private ArrayList<String> attendees = new ArrayList<String>();

    //Constructor will create a check-in object with the total number of members signed up as a status of awaiting.
    public CheckIn(int totalMembersSignedUp, ArrayList<Integer> attendeeID) {
        totalMembers = totalMembersSignedUp;
        membersAwaiting = totalMembersSignedUp;
        membersConfirmed = 0;
        membersAbsent = 0;
        DataMatch memberCheck = new DataMatch();
        for(Integer id : attendeeID) {
            attendees.add(memberCheck.findDataInFile(id));
        }
    }

    //This method will keep track of the numbers of members with each status type.
    public void tallyMembersCheckedIn(String status) {
        EventStatus eventStatus = EventStatus.valueOf(status);
        switch(eventStatus) {
            case AWAITING:
            membersAwaiting++;
            break;
            case CONFIRMED:
            membersConfirmed++;
            break;
            case ABSENT:
            membersAbsent++;
            break;
        }
    }

    //Method to report the current status of the check-in.
    public String toString() {
        return "Total members in event: " + totalMembers + "\nNumber of Awaiting status: " + membersAwaiting +
                 "\nNumber of Confirmed status: " + membersConfirmed + "\nNumber of Absent status: " + membersAbsent;
    }
}
