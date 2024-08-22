import java.util.Scanner;
import java.util.Random;

class BinarySearchTree {
    int PassnNo; // Combination of busNo and SeatNo.
    String name;
    BinarySearchTree left, right;

    BinarySearchTree(int PassnNo) {
        this.PassnNo = PassnNo;
        this.name = "";
        this.left = null;
        this.right = null;
    }
}

public class BusReservationSystem {
    static BinarySearchTree root = null;
    static int[][] busSeat = new int[10][33];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Random rand = new Random(); 
        int randomNum = rand.nextInt(10000);//randomNum is a randomly generated reservation number, which is used for booking and cancellation validation.
        int num, custID, reservationNo;

        login();
        do {
            System.out.println("\n\n====================================================================");
            System.out.println("\t\t\tBUS RESERVATION\t\t");
            System.out.println("=====================================================================");
            System.out.println("\n====================  MAIN MENU =====================");
            System.out.println("   [1] VIEW BUS LIST");
            System.out.println("   [2] BOOK TICKETS");
            System.out.println("   [3] CANCEL BOOKING");
            System.out.println("   [4] BUSES SEATS INFO");
            System.out.println("   [5] RESERVATION INFO");
            System.out.println("   [6] EXIT");
            System.out.println("=====================================================");
            System.out.print("   ENTER YOUR CHOICE: ");
            num = scanner.nextInt();

            switch (num) {
                case 1:
                    busLists();
                    break;
                case 2:
                    busLists();
                    bookTickets(randomNum);
                    break;
                case 3:
                    cancel(randomNum);
                    break;
                case 4:
                    status();
                    break;
                case 5:
                    reservationInfo(randomNum);
                    break;
                case 6:
                    System.out.println("\n\n=====================================================================");
                    System.out.println("THANK YOU FOR USING THIS BUS RESERVATION SYSTEM");
                    System.exit(0);
                default:
                    System.out.println("\n\n   INVALID INPUT CHOOSE CORRECT OPTION");
                    break;
            }
        } while (true);
    }

    public static void login() {
        String userName = "user";
        String passWord = "team18";
        String matchPass;
        String matchUser;
        boolean loggedIn = false;

        System.out.println("\n\n=========================================================================================");
        System.out.println("\t\t\tWELCOME TO ONLINE BUS RESERVATION");
        System.out.println("=========================================================================================\n");

        while (!loggedIn) {
            System.out.print("UserName: ");
            matchUser = scanner.next();

            System.out.print("PassWord: ");
            matchPass = scanner.next();

            if (passWord.equals(matchPass)) {
                System.out.println("\nLOGGED IN SUCCESSFULLY...\n");
                loggedIn = true;
            } else {
                System.out.println("\nINVALID DETAILS TRY AGAIN...\n");
            }
        }
    }

    public static void busLists() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Bus.No\tName\t\t\tDestinations  \t\tCharges  \t\tTime");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1\tGangaTravels         \tDharan to Kavre       \tRs.70   \t\t07:00  AM");
        System.out.println("2\tPhaphara Travels     \tKavre To Dharan       \tRs.55   \t\t01:30  PM");
        System.out.println("3\tShiv Ganga Travels   \tAllahabad To Gorakhpur\tRs.40   \t\t03:50  PM");
        System.out.println("4\tSuper Deluxe         \tPokhara To Benigha    \tRs.70   \t\t01:00  AM");
        System.out.println("5\tSai Baba Travels     \tMaitidevi To Janakpur \tRs.55   \t\t12:05  AM");
        System.out.println("6\tShine On Travels     \tMadhubani to Patna    \tRs.40   \t\t09:30  AM");
        System.out.println("7\tMayur Travels        \tPatna To Gaya         \tRs.70   \t\t11:00  PM");
        System.out.println("8\tRajjo Travels        \tBegusarai To Patna    \tRs.55   \t\t08:15  AM");
        System.out.println("9\tShree Travels        \tGaya To Chhapra       \tRs.40   \t\t04:00  PM");
        System.out.println("PRESS 'ENTER' KEY TO CONTINUE ");
        scanner.nextLine();
    }

    public static void bookTickets(int randomNum) {
        int choice, seats, seatNumber, custID;

        do {
            System.out.print("\n\nCHOOSE YOUR BUS: ");
            choice = scanner.nextInt();
            if (choice <= 0 || choice > 9) {
                System.out.println("\nENTER VALID BUS NUMBER!!\n");
            } else {
                break;
            }
        } while (true);

        DisplaySeat(busSeat[choice]);

        do {
            System.out.print("\n\nNO. OF SEATS YOU NEED TO BOOK: ");
            seats = scanner.nextInt();
            if (seats <= 0 || seats > 32) {
                System.out.println("\nENTER VALID SEAT NUMBER!!\n");
            } else {
                break;
            }
        } while (true);

        for (int i = 1; i <= seats; i++) {
            do {
                System.out.print("   ENTER THE SEAT NUMBER: ");
                seatNumber = scanner.nextInt();
                if (seatNumber <= 0 || seatNumber > 32) {
                    System.out.println("\n   ENTER VALID SEAT NUMBER!!\n\n");
                } else {
                    break;
                }
            } while (true);

            custID = choice * 1000 + seatNumber;
            busSeat[choice][seatNumber] = 1;
            root = insert(root, custID);
            System.out.println("\n   YOUR CUSTOMER ID IS: " + custID);
        }
        System.out.println("\nYOUR RESERVATION NUMBER IS: " + randomNum);
        System.out.println("PLEASE NOTE DOWN YOUR RESERVATION NUMBER FOR CANCEL BOOKING TICKETS!!");
    }

    public static void cancel(int randomNum) {
        int reservationNo, seatNumber, choice, seatCancel;
        char c;

        while (true) {
            System.out.print("\nENTER YOUR RESERVATION NUMBER: ");
            reservationNo = scanner.nextInt();
            if (reservationNo == randomNum) {
                System.out.print("\nRESERVATION NUMBER IS CORRECT? " + reservationNo + " ENTER (Y/N): ");
                c = scanner.next().charAt(0);
                if (c == 'y' || c == 'Y') {
                    System.out.print("\n   ENTER THE BUS NUMBER: ");
                    choice = scanner.nextInt();
                    System.out.print("\n HOW MANY SEATS DO YOU WANT TO CANCEL: ");
                    seatCancel = scanner.nextInt();

                    for (int i = 0; i < seatCancel; i++) {
                        System.out.print("   ENTER THE SEAT NUMBER: ");
                        seatNumber = scanner.nextInt();
                        busSeat[choice][seatNumber] = 0;
                    }
                    System.out.println("\nYOUR RESERVATION HAS BEEN CANCELLED!!");
                    DisplaySeat(busSeat[choice]);
                    break;
                } else {
                    System.out.println("\nYOUR RESERVATION CANCELLATION HAS BEEN DENIED");
                    break;
                }
            } else {
                System.out.println("\nNOT FOUND!! ENTER THE CORRECT RESERVATION NUMBER");
            }
        }
    }

    public static void status() {
        int busNum;
        busLists();
        do {
            System.out.print("\n\nENTER YOUR BUS NUMBER: ");
            busNum = scanner.nextInt();
            if (busNum <= 0 || busNum >= 10) {
                System.out.println("\n  PLEASE ENTER CORRECT BUS NUMBER !!\n");
            } else {
                DisplaySeat(busSeat[busNum]);
                break;
            }
        } while (true);
    }

    public static void DisplaySeat(int[] bus) {
        for (int i = 1; i <= 32; i++) {
            System.out.print((i < 10 ? "0" + i : i) + " .");
            System.out.print(bus[i] == 0 ? "EMPTY " : "BOOKED ");
            System.out.print("         ");
            if (i % 4 == 0)
                System.out.println();
        }
    }

    public static void reservationInfo(int randomNum) {
        int custID, reservationNo;
        while (true) {
            System.out.print("\n   ENTER YOUR RESERVATION NUMBER: ");
            reservationNo = scanner.nextInt();

            if (randomNum == reservationNo) {
                while (true) {
                    System.out.print("\n   ENTER YOUR CUSTOMER ID: ");
                    custID = scanner.nextInt();
                    if (!displayReservationInfo(root, custID)) {
                        System.out.println("\n   ENTER CORRECT CUSTOMER ID!!");
                    } else {
                        break;
                    }
                }
                break;
            } else {
                System.out.println("\n INVALID RESERVATION NUMBER PLEASE ENTER CORRECT RESERVATION NUMBER!!");
            }
        }
    }

    public static boolean displayReservationInfo(BinarySearchTree r, int s) {
        while (r != null) {
            if (r.PassnNo == s) {
                System.out.println("\n-----------------------------------------------------------------");
                System.out.println("||              NAME: " + r.name + "                               ||");
                System.out.println("||              CUSTOMER ID: " + r.PassnNo + "                              ||");
                System.out.println("||              BUS NUMBER: " + r.PassnNo / 1000 + "                                  ||");
                System.out.println("||              SEAT NUMBER: " + r.PassnNo % 100 + "                                 ||");
                System.out.println("||              TICKET COST: Rs." + cost(r) + "                             ||");
                System.out.println("-----------------------------------------------------------------");
                return true;
            } else if (r.PassnNo > s) {
                r = r.left;
            } else {
                r = r.right;
            }
        }
        return false;
    }

    public static BinarySearchTree insert(BinarySearchTree r, int custID) {
        if (r == null) {
            r = new BinarySearchTree(custID);
            System.out.print("\n   ENTER THE PERSON NAME: ");
            r.name = scanner.next();
        } else if (r.PassnNo > custID) {
            r.left = insert(r.left, custID);
        } else if (r.PassnNo < custID) {
            r.right = insert(r.right, custID);
        }
        return r;
    }

    public static int cost(BinarySearchTree r) {
        int buscost = r.PassnNo / 1000;
        switch (buscost % 3) {
            case 1:
                return 70;
            case 2:
                return 55;
            case 0:
                return 40;
            default:
                return 0;
        }
    }
}
