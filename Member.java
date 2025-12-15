import java.util.ArrayList;

public class Member {
    private int ID;
    private String song, name;
    private ArrayList<Request> requests;
    private boolean inProgressStatus;

    public Member(int ID, String song, String name) {
        this.ID = ID;
        this.song = song;
        this.name = name;
        this.requests = new ArrayList<>();
        this.inProgressStatus = false;
    }

    public int getID() {
        return ID;
    }

    public String getSong() {
        return song;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public boolean isInProgressStatus() {
        return this.inProgressStatus;
    }

    public void setInProgressStatus(boolean status) {
        this.inProgressStatus = status;
    }

    public String generateAdminReport() {
        String report = "Member ID: " + ID + "\n" + "Name: " + name + "\n";
        report += "Song: " + song + "\n";
        report += "Requests:\n";
        for (Request req : requests) {
            report += req + "\n";
        }
        if (requests.isEmpty()) {
            report += "No requests assigned.\n";
        }
        return report;
    }

    public String generateReport() {
        String report = "Member ID: " + ID + "\n" + "Name: " + name + "\n";
        report += "Song: " + song + "\n";
        report += "Requests:\n";
        for (Request req : requests) {
            report += req + "\n";
        }
        if (requests.isEmpty()) {
            report += "No requests assigned.\n";
        } else {
            setInProgressStatus(true);
        }
        return report;
    }

    public String completeRequests() {
        if (this.inProgressStatus == false) {
            return "No requests in progress to complete. Check the report of requests first before completing.";
        }
        String allEmails = "";
        for (Request req : requests) {
            allEmails += req.completeRequest() + "\n";
        }
        requests.clear();
        setInProgressStatus(false);
        return allEmails;
    }
}
