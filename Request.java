public class Request {
    private String emailAddress, CCNumber, sweetheartName;
    private Member assignedMember;

    public Request(String emailAddress, String CCNumber, String sweetheartName, Member assignedMember) {
        this.emailAddress = emailAddress;
        this.CCNumber = CCNumber;
        this.sweetheartName = sweetheartName;
        this.assignedMember = assignedMember;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCreditCardNumber() {
        return CCNumber;
    }

    public String getSweetheartName() {
        return sweetheartName;
    }

    public Member getAssignedMember() {
        return assignedMember;
    }

    public String completeRequest() {
        String email = "Email sent to: " + emailAddress + "\nContent:\nRequest completed for " + sweetheartName + " by member " + assignedMember.getName() + "\nCharging credit card.\n";
        return email;
    }
}
