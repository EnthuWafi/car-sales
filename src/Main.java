import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static CarInventory carInventory = new CarInventory();
    static CustomerList customerList = new CustomerList();
    static TransactionHistory transactionList = new TransactionHistory();

    public static void main(String[] args) {
        File inputFile = new File("Cars.txt");

        try {
            Scanner inFile = new Scanner(inputFile);

            while (inFile.hasNextLine()) {
                String s = inFile.nextLine();
                String delimiter = ";";
                StringTokenizer st = new StringTokenizer(s, delimiter);

                //read from s using st
                String carID = st.nextToken();
                String carBrand = st.nextToken();
                String carBody = st.nextToken();
                Double carPrice = Double.parseDouble(st.nextToken());
                String countryMade = st.nextToken();
                String dateCreated = st.nextToken();
                //new car here
                carInventory.add(new Car(carID, carBrand, carBody, carPrice, countryMade, dateCreated));
            }

            int userInput;
            do {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.print("============ CAR SALES INVENTORY SYSTEM ============\n");
                System.out.print("[MAIN MENU]\n\t1) Display Inventory\n\t2) Display Customer\n\t3) Display Transaction\n\t4) Exit Program and Print Report\n>");

                userInput = sc.nextInt();
                sc.nextLine();

                // Inventory Path
                if (userInput == 1)
                {
                    inventoryPath();
                }
                // Transaction Path
                else if (userInput == 2)
                {
                    customerListPath();
                }
                else if (userInput == 3)
                {
                    transactionListPath();
                }
                // Exit
                else if (userInput == 4)
                {
                    if (!confirmation()){
                        userInput = 0;
                    }
                }

                System.out.println("Enter any key to continue..\n");
                sc.nextLine();

            } while (userInput != 4);
            // print monthly report
            System.out.println("\t\t\t\t\t\t\tTOTAL EARNING FOR THIS MONTH: -");
            System.out.print("---------------------------------------------------------------------------------------------\n");
            transactionList.print();

            double total = 0;
            if (!transactionList.getList().isEmpty())
            {
                Bill temp = (Bill) transactionList.getList().getFirst();
                while (temp != null) {
                    if (temp.getValidity())
                        total += temp.getCar().getCarPrice();
                    temp = (Bill) transactionList.getList().getNext();
                }
            }
            System.out.print("---------------------------------------------------------------------------------------------\n");
            System.out.printf("%80sRM%.2f", "TOTAL: ", total);

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    static void inventoryPath()
    {
        int userInput;
        try {
            while (true) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("INVENTORY:");
                carInventory.display();

                System.out.print("[CHOICES]\n\t1) Add Car\n\t2) Remove Car\n\t3) Modify Car\n\t4) Add Customer Purchase\n\t5) Back\n>");
                userInput = sc.nextInt();
                sc.nextLine();

                if (userInput == 1) {
                    System.out.println("ADD CAR PATH:-");
                    System.out.print("Car ID: ");
                    String carID = sc.nextLine();

                    System.out.print("Car Brand: ");
                    String carBrand = sc.nextLine();

                    System.out.print("Car Body (SUV, Hatchback): ");
                    String carBody = sc.nextLine();

                    System.out.print("Car Price (RM): ");
                    Double carPrice = Double.parseDouble(sc.nextLine());

                    System.out.print("Made In (Country): ");
                    String countryMade = sc.nextLine();

                    System.out.print("Date Created (YYYY-MM-DD): ");
                    String dateCreated = sc.nextLine();
                    //new car here
                    boolean isAdded = carInventory.add(new Car(carID, carBrand, carBody, carPrice, countryMade, dateCreated));
                    if (isAdded) {
                        System.out.println("Car Added.");
                    } else {
                        System.out.println("Car was not Added. Reason: Car with similar ID exists.");
                    }
                } else if (userInput == 2) {
                    System.out.println("REMOVE CAR PATH:-");
                    System.out.print("Enter the Car ID:");
                    String carID = sc.nextLine();

                    Car removedCar = carInventory.remove(carInventory.search(carID));
                    if (removedCar != null) {
                        System.out.println("The following car was Removed.\n" + removedCar.toString());
                    } else {
                        System.out.println("CarID couldn't be found in the list!");
                    }

                }
                // Modify
                else if (userInput == 3) {
                    System.out.println("MODIFY CAR PATH:-");
                    Car modifyCar = null;
                    {
                        System.out.print("Enter the Car ID:");
                        String carID = sc.nextLine();

                        modifyCar = carInventory.search(carID);
                    }

                    if (modifyCar == null) {
                        System.out.println("CarID couldn't be found in the list!");
                        continue;
                    }
                    System.out.println("CarID found! - \n" + modifyCar.toString() + "\nMODIFY CAR:");

                    System.out.print("Car ID: ");
                    String carID = sc.nextLine();

                    System.out.print("Car Brand: ");
                    String carBrand = sc.nextLine();

                    System.out.print("Car Body (SUV, Hatchback): ");
                    String carBody = sc.nextLine();

                    System.out.print("Car Price (RM): ");
                    Double carPrice = Double.parseDouble(sc.nextLine());

                    System.out.print("Made In (Country): ");
                    String countryMade = sc.nextLine();

                    System.out.print("Date Created (YYYY-MM-DD): ");
                    String dateCreated = sc.nextLine();

                    modifyCar.setCarID(carID);
                    modifyCar.setCarBrand(carBrand);
                    modifyCar.setCarBody(carBody);
                    modifyCar.setCarPrice(carPrice);
                    modifyCar.setCountryMade(countryMade);
                    modifyCar.setDateCreated(dateCreated);

                    System.out.println("Car Modified!");
                } else if (userInput == 4) {
                    System.out.println("ADD CUSTOMER PURCHASE PATH:-");
                    System.out.print("Enter the Car ID: ");
                    String carID = sc.nextLine();

                    Car car = carInventory.search(carID);
                    if (car == null) {
                        System.out.println("CarID couldn't be found in the list!");
                        continue;
                    }

                    //customer list
                    if (customerList.getList().isEmpty()) {
                        System.out.println("Customer List is empty!");
                        continue;
                    }
                    customerList.display();
                    System.out.print("Enter the Customer's IC: ");
                    String customerIC = sc.nextLine();

                    Customer cust = null;
                    cust = customerList.search(customerIC);

                    if (cust == null) {
                        System.out.println("CustomerID couldn't be found in the list!");
                        continue;
                    }
                    String transactionType;
                    while (true) {
                        System.out.print("Transaction Type (Cash/Non-Cash): ");
                        transactionType = sc.nextLine();

                        if (transactionType.equalsIgnoreCase("cash") || transactionType.equalsIgnoreCase("non-cash")) {
                            break;
                        } else {
                            System.out.println("Please write either cash or non-cash!");
                        }
                    }

                    if (cust.getCarOwned().add(car)) {
                        transactionList.getList().insertAtBack(new Bill(cust, carInventory.remove(car), transactionType));
                        System.out.println("Customer Purchase added successfully!");
                    } else {
                        System.out.println("Customer Order wasn't added. Failed!");
                    }
                }
                // Exit
                else if (userInput == 5) {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    static void customerListPath() {
        int userInput;
        try {
            while (true) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("CUSTOMER: ");

                customerList.display();

                System.out.print("[CHOICES]\n\t1) Add Customer\n\t2) Display Customer's Cars\n\t3) Back\n>");
                userInput = sc.nextInt();
                sc.nextLine();

                if (userInput == 1) {
                    System.out.println("ADD CUSTOMER PATH:-");
                    System.out.print("Full Name: ");
                    String fullName = sc.nextLine();

                    System.out.print("Customer IC: ");
                    String customerIC = sc.nextLine();

                    System.out.print("Customer Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    //new customer here
                    Customer temp = customerList.search(customerIC);

                    if (temp == null) {
                        customerList.add(new Customer(fullName, age, customerIC));
                        System.out.println("Customer Added. ");
                    } else {
                        System.out.println("Customer was not Added. Reason: Customer with similar ID exists.");
                    }
                } else if (userInput == 2) {
                    System.out.println("DISPLAY CUSTOMER CARS PATH:-");
                    System.out.print("Enter the Customer IC:");
                    String customerIC = sc.nextLine();

                    Customer temp = customerList.search(customerIC);

                    if (temp == null) {
                        continue;
                    }

                    //Display here
                    customerCarsPath(temp);
                }
                // Exit
                else if (userInput == 3) {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    static void customerCarsPath(Customer cust)
    {
        int userInput;
        try {
            while (true) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.printf("DISPLAYING %s's CAR OWNED!%n", cust.getFullName());

                cust.getCarOwned().display();

                System.out.print("[CHOICES]\n\t1) Cancel Car Purchase\n\t2) Search and Display\n\t3) Back\n>");
                userInput = sc.nextInt();
                sc.nextLine();

                if (userInput == 1) {
                    System.out.println("CANCEL CAR PURCHASE PATH:-");

                    System.out.print("Enter the CAR ID: ");
                    String carID = sc.nextLine();
                    Car temp = cust.getCarOwned().search(carID);

                    if (temp == null) {
                        continue;
                    }

                    if (carInventory.add(cust.getCarOwned().remove(temp))) {
                        System.out.println("Car Purchase cancelled successfully (Car is sent back to Inventory)");
                        // set validity to false
                        {
                            Bill bill = (Bill) transactionList.getList().getFirst();
                            while (bill != null) {
                                if ((bill.getCustomer() == cust) && bill.getCar() == temp) {
                                    bill.setStatus("refunded");
                                    bill.setValidity(false);
                                    break;
                                }
                                bill = (Bill) transactionList.getList().getNext();
                            }
                        }
                    } else {
                        System.out.println("Something went wrong. Failed to cancel.");
                    }
                } else if (userInput == 2) {
                    System.out.println("SEARCH AND DISPLAY PATH:-");

                    System.out.print("Enter the CAR ID: ");
                    String carID = sc.nextLine();
                    Car temp = cust.getCarOwned().search(carID);

                    if (temp == null) {
                        continue;
                    }

                    System.out.printf("%nCAR FOUND!%n%s%n", temp.toString());

                }
                // Exit
                else if (userInput == 3) {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    static void transactionListPath()
    {
        int userInput;
        try {
            while (true) {
                System.out.println("\n-------------------------------------------------------------------");
                System.out.println("TRANSACTION HISTORY:");

                transactionList.print();

                System.out.print("[CHOICES]\n\t1) Split Records by Transaction Types\n\t2) Analyse the Records\n\t3) Back\n>");
                userInput = sc.nextInt();
                sc.nextLine();

                if (userInput == 1) {
                    System.out.println("SPLIT RECORDS PATH:-");
                    //Cash and Non-cash
                    TransactionHistory cashList = new TransactionHistory();
                    TransactionHistory nonCashList = new TransactionHistory();
                    LinkedList tempList = new LinkedList();

                    if (transactionList.getList().isEmpty()) {
                        System.out.println("No transaction.");
                        continue;
                    }
                    while (!transactionList.getList().isEmpty()) {
                        Bill bill = (Bill) transactionList.getList().removeFromFront();
                        if (bill.getValidity()) {
                            if (bill.getTransactionType().equalsIgnoreCase("cash")) {
                                cashList.getList().insertAtBack(bill);
                            } else if ((bill.getTransactionType().equalsIgnoreCase("non-cash"))) {
                                nonCashList.getList().insertAtBack(bill);
                            }
                        }
                        tempList.insertAtBack(bill);
                    }
                    while (!tempList.isEmpty()) {
                        transactionList.getList().insertAtFront(tempList.removeFromBack());
                    }
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Cash Transaction Type:");

                    cashList.print();

                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Non-Cash Transaction Type:");

                    nonCashList.print();
                } else if (userInput == 2) {
                    System.out.println("ANALYSE TRANSACTION PATH:-");
                    // highest, lowest, total, etc
                    if (transactionList.getList().isEmpty()) {
                        System.out.println("No transactions.");
                        continue;
                    }
                    Bill temp, highest, lowest;
                    int count = 0;
                    double total = 0;

                    temp = highest = lowest = (Bill) transactionList.getList().getFirst();
                    while (temp != null) {
                        if (temp.getValidity()) {
                            if (temp.getCar().getCarPrice() > highest.getCar().getCarPrice()) {
                                highest = temp;
                            }
                            if (temp.getCar().getCarPrice() < lowest.getCar().getCarPrice()) {
                                lowest = temp;
                            }
                            total += temp.getCar().getCarPrice();
                            count++;
                            ;
                        }

                        temp = (Bill) transactionList.getList().getNext();
                    }
                    System.out.println("RESULT:\n----------");
                    System.out.printf("Highest Priced Car: %s (RM%.2f) owned by %s %n", highest.getCar().getCarID(), highest.getCar().getCarPrice(), highest.getCustomer().getFullName());
                    System.out.printf("Lowest Priced Car: %s (RM%.2f) owned by %s %n", lowest.getCar().getCarID(), lowest.getCar().getCarPrice(), lowest.getCustomer().getFullName());
                    System.out.printf("Total : RM%.2f %n", total);
                    System.out.printf("Average : RM%.2f %n", total / count);
                }
                // Exit
                else if (userInput == 3) {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
    static boolean confirmation(){
        String userInput;
        while (true) {
            System.out.print("\n[Would you like to proceed?(Y/N)]: ");
            userInput = sc.nextLine();
            if (userInput.isEmpty())
            {
                System.out.println("Please do not leave this empty!");
                continue;
            }
            char userChar = userInput.charAt(0);
            if (userChar == 'Y' || userChar == 'y'){
                return true;
            }
            else if (userChar == 'N' || userChar == 'n'){
                return false;
            }
            else {
                System.out.println("Please enter a valid input. . .");
            }
        }
    }
}