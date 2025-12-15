import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static ArrayList<Member> members = new ArrayList<>();
    public static ArrayList<Request> requests = new ArrayList<>();


    private static final String[] SONGS = {
            "Can't Help Falling in Love",
            "At Last",
            "Unchained Melody",
            "Perfect",
            "All of Me",
            "A Thousand Years",
            "Make You Feel My Love"
    };

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void main(String[] args) {
        initMembers();
        initRequests();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Welcome to the Serenaders Music Club Valentines Song System!
                    Select the action to do:
                    1 - Customers - Order a song for Valentines Day
                    2 - Club members - Get a report of requests for your song
                    3 - Club members - Report back that your songs are done
                    4 - Admin - Show data for all club members
                    5 - Exit
                    Enter 1, 2, 3, 4 or 5:
                    """);

            int action;
            try {
                action = Integer.parseInt(sc.nextLine());
                if (action < 1 || action > 5) throw new Exception();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 1, 2, 3, 4 or 5.\n");
                continue;
            }

            switch (action) {
                case 1 -> orderSong(sc);
                case 2 -> memberGetReport(sc);
                case 3 -> memberCompleteSongs(sc);
                case 4 -> adminShowAllData();
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    return;
                }
            }
        }
    }

    // ====== Important behavior methods ======

    private static void orderSong(Scanner sc) {
        int songID;
        String emailAddress;
        long CCNumber;
        String sweetheartName;
        Member assignedMember = null;

        while (true) {
            System.out.println("""
                    Order a song for Valentine’s Day...
                    Select the song from this list:
                    1 - "Can't Help Falling in Love" by Elvis Presley
                    2 - "At Last" by Etta James
                    3 - "Unchained Melody" by The Righteous Brothers
                    4 - "Perfect" by Ed Sheeran
                    5 - "All of Me" by John Legend
                    6 - "A Thousand Years" by Christina Perri
                    7 - "Make You Feel My Love" by Adele
                    Your choice (1 - 7):
                    """);
            try {
                songID = Integer.parseInt(sc.nextLine());
                if (songID < 1 || songID > 7) throw new Exception();
                songID--;

                for (Member m : members) {
                    if (m.getSong().equals(SONGS[songID])) {
                        assignedMember = m;
                        break;
                    }
                }

                if (assignedMember == null || assignedMember.isInProgressStatus()) {
                    System.out.println("That song is currently unavailable. Please choose another.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }

        while (true) {
            System.out.println("Enter your email address: ");
            emailAddress = sc.nextLine();
            Matcher matcher = EMAIL_PATTERN.matcher(emailAddress);
            if (matcher.matches()) break;
            System.out.println("Invalid email format. Please enter a valid email address.");
        }

        while (true) {
            System.out.println("Enter your credit card number: ");
            try {
                CCNumber = Long.parseLong(sc.nextLine());
                if (String.valueOf(CCNumber).length() != 16) throw new Exception();
                break;
            } catch (Exception e) {
                System.out.println("Invalid credit card number. Please enter a 16-digit number.");
            }
        }

        while (true) {
            System.out.println("Enter your sweetheart's name: ");
            sweetheartName = sc.nextLine();
            int numNames = sweetheartName.trim().split(" ").length;
            if (!sweetheartName.isBlank() && numNames >= 2 && numNames <= 4) break;
            System.out.println("Invalid name. Please enter a full name with 2 to 4 words.");
        }

        Request newRequest = new Request(emailAddress, CCNumber, sweetheartName, assignedMember);
        assignedMember.addRequest(newRequest);
        requests.add(newRequest);
        System.out.println("Done!");
    }

    private static void memberGetReport(Scanner sc) {
        Member m = promptMember(sc);
        System.out.println("Look for an email with a list of all the sweethearts to sing to!");
        System.out.println(m.generateReport());
    }

    private static void memberCompleteSongs(Scanner sc) {
        Member m = promptMember(sc);
        System.out.println("Your customers will be charged and notified!");
        System.out.println(m.completeRequests());
    }

    private static void adminShowAllData() {
        System.out.println("Admin - Data");
        for (Member m : members) {
            System.out.println(m.generateAdminReport());
        }
    }

    // Reused once → still UML-worthy
    private static Member promptMember(Scanner sc) {
        while (true) {
            System.out.println("Enter your member ID number:");
            try {
                int memberID = Integer.parseInt(sc.nextLine());
                for (Member m : members) {
                    if (m.getID() == memberID) return m;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Invalid member ID. Please try again.");
            }
        }
    }

    private static void initMembers() {
        members.add(new Member(55066592, SONGS[0], "Erin Solstice"));
        members.add(new Member(45709180, SONGS[1], "Wei Shi Lindon"));
        members.add(new Member(28506423, SONGS[2], "Bai Xiaochun"));
        members.add(new Member(30879472, SONGS[3], "Yang Kai"));
        members.add(new Member(61013828, SONGS[4], "Sunless"));
        members.add(new Member(40337280, SONGS[5], "Zhou Mingrui"));
        members.add(new Member(92344932, SONGS[6], "Prissy Kitty Princess"));
    }

    private static void initRequests() {
        Request r1 = new Request(
                "test1@email.com",
                1234567812345678L,
                "Jane Doe",
                members.get(4)
        );

        Request r2 = new Request(
                "test2@email.com",
                8765432187654321L,
                "John Smith",
                members.get(2)
        );

        requests.add(r1);
        requests.add(r2);

        members.get(4).addRequest(r1);
        members.get(2).addRequest(r2);
    }
}
