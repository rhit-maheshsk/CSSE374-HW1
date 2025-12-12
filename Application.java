
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    public static ArrayList<Member> members = new ArrayList<>();

    public void createRequest(String emailAddress, Long CCNumber, String sweetheartName, Member assignedMember) {
        Request newRequest = new Request(emailAddress, CCNumber, sweetheartName, assignedMember);
        assignedMember.addRequest(newRequest);
    }

    public static void main(String[] args) {
        String[] songs = {
            "Can't Help Falling in Love",
            "At Last",
            "Unchained Melody",
            "Perfect",
            "All of Me",
            "A Thousand Years",
            "Make You Feel My Love"
        };
        members.add(new Member(55066592, songs[0], "Erin Solstice"));
        members.add(new Member(45709180, songs[1], "Wei Shi Lindon"));
        members.add(new Member(28506423, songs[2], "Bai Xiaochun"));
        members.add(new Member(30879472, songs[3], "Yang Kai"));
        members.add(new Member(61013828, songs[4], "Sunless"));
        members.add(new Member(40337280, songs[5], "Zhou Mingrui"));
        members.add(new Member(92344932, songs[6], "Prissy Kitty Princess"));
        Scanner sc = new Scanner(System.in);
        String EMAIL_REGEX
                = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        int action = 0;
        while (true) {
            System.out.println("""
                                Welcome to the Serenaders Music Club Valentines Song System!
                                Select the action to do:
                                1 - Customers - Order a song for Valentines Day
                                2 - Club members - Get a report of requests for your song
                                3 - Club members - Report back that your songs are done
                                4 - Admin - Show data for all club members
                                5 - Exit
                                Enter 1, 2, 3 or 4: 
                            """);
            try {
                action = sc.nextInt();
                if (action < 1 || action > 5) {
                    throw new Exception("Out of range");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 1, 2, 3, or 4.\n");
                continue;
            }
            break;
        }
        switch (action) {
            case (1):
                int songID = 0;
                String emailAddress = null;
                long CCNumber = 0;
                String sweetheartName = null;
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
                                        """
                    );
                    try {
                        songID = sc.nextInt();
                        if (songID < 1 || songID > 7) {
                            throw new Exception("Out of range");
                        }
                        songID -= 1;
                        for (Member m : members) {
                            if (m.getSong().equals(songs[songID])) {
                                assignedMember = m;
                                break;
                            }
                        }
                        if (assignedMember.isInProgressStatus()) {
                            System.out.println("Sorry, the member performing that song is currently busy and cannot take new requests. Please choose a different song.");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 7.");
                        continue;
                    }

                    System.out.println("Enter your email address: ");
                    try {
                        emailAddress = sc.nextLine();
                        Matcher matcher = pattern.matcher(emailAddress);
                        if (!matcher.matches()) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid email format. Please enter a valid email address.");
                        continue;
                    }

                    System.out.println("Enter your credit card number: ");
                    try {
                        CCNumber = sc.nextLong();
                        if (String.valueOf(CCNumber).length() != 16) {
                            throw new Exception("Invalid length");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid credit card number. Please enter a 16-digit number.");
                        continue;
                    }
                    System.out.println("Enter your sweetheart's name: ");
                    try {
                        sweetheartName = sc.nextLine();
                        int numNames = sweetheartName.trim().split(" ").length;
                        if (numNames < 2 || numNames > 4 || sweetheartName.isBlank()) {
                            throw new Exception("Invalid number of names.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid name. Please enter a full name with 2 to 4 words to correctly identify your sweetheart.");
                        continue;
                    }
                    break;
                }
                Request newRequest = new Request(emailAddress, CCNumber, sweetheartName, assignedMember);
                assignedMember.addRequest(newRequest);
                System.out.println("Done!");
                break;
            case (2):
                int memberID = 0;
                Member currentMember = null;
                while (true) {
                    System.out.println("""
                                    Get a report of requests for your song...
                                    Enter your member ID number: 
                                """);
                    try {
                        memberID = sc.nextInt();
                        for (Member m : members) {
                            if (m.getID() == memberID) {
                                currentMember = m;
                                break;
                            }
                            throw new Exception("Member not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid member ID. Please try again.");
                        continue;
                    }
                    break;
                }
                System.out.println("Look for an email with a list of all the sweethearts to sing to!");
                System.out.println(currentMember.generateReport());
                break;
            case (3):
                memberID = 0;
                currentMember = null;
                while (true) {
                    System.out.println("""
                                    Report back that your songs are done...
                                    Enter your member ID number: 
                                """);
                    try {
                        memberID = sc.nextInt();
                        for (Member m : members) {
                            if (m.getID() == memberID) {
                                currentMember = m;
                                break;
                            }
                            throw new Exception("Member not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid member ID. Please try again.");
                        continue;
                    }
                    break;
                }
                System.out.println("Your customers will be charged and notified!");
                System.out.println(currentMember.completeRequests());
                break;
            case (4):
                System.out.println("Admin - Data");
                for (Member m : members) {
                    System.out.println(m.generateReport());
                }
                break;
            case (5):
                System.out.println("Exiting the system. Goodbye!");

                break;
            default:
            //error handling
        }
        sc.close();
    }

}
