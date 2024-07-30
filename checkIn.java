enum EventStatus {
    AWAITING,
    CONFIRMED,
    ABSENT;
}

public class checkIn {
    private int totalMembers;
    private int membersAwaiting;
    private int membersConfirmed;
    private int membersAbsent;

    //Constructor will create a check-in object with the total number of members signed up as a status of awaiting.
    public checkIn(int totalMembersSignedUp) {
        totalMembers = totalMembersSignedUp;
        membersAwaiting = totalMembersSignedUp;
        membersConfirmed = 0;
        membersAbsent = 0;
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
