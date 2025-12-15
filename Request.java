public class Request {
    private String emailAddress, sweetheartName;
    private long CCNumber;
    private Member assignedMember;

    public Request(String emailAddress, long CCNumber, String sweetheartName, Member assignedMember) {
        this.emailAddress = emailAddress;
        this.CCNumber = CCNumber;
        this.sweetheartName = sweetheartName;
        this.assignedMember = assignedMember;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public long getCreditCardNumber() {
        return CCNumber;
    }

    public String getSweetheartName() {
        return sweetheartName;
    }

    public Member getAssignedMember() {
        return assignedMember;
    }

    @Override
    public String toString() {
        return "Sweetheart: " + sweetheartName + ", Email: " + emailAddress;
    }

    public String completeRequest() {
        String email = "Email sent to: " + emailAddress + "\nContent:\nRequest completed for " + sweetheartName
                + " by member " + assignedMember.getName() + "\nCharging credit card.\n";
        return email;
    }
}
