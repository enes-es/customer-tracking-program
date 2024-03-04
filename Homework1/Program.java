package Homework1;

import java.io.File;
import java.util.Scanner;

// DISCUSS doing enum for 1, 2, 3, 4, 5, 6 (order, retail_customer,
// corporate_customer, operator, ID, company_name)


// TODO add relevant `throws` to every method

// TODO CHECK THE VALIDITY OF VISIBILITY OF EVERY METHOD OF EVERY CLASS!
public final class Program {
    private Program() {}

    /** format-valid textfile contents but each line is word seperated */
    static String[][] textFile = new String[100][100];
    /** number of lines in textFile */
    static int textFileLength = 0;

    static order[] orders = new order[100];
    static operator[] operators = new operator[100];
    static customer[] customers = new customer[100];

    private static int orderCount = 0;
    private static int operatorCount = 0;
    private static int customerCount = 0;

    // TODO program accesses through wrong directionary, fix it
    private static final String textFileName = "content.txt";

    public static void main(String[] args) {
       Scanner scan = null;
        // person searchedPerson = null; // DISCUSS if assigning null is the right
        // approach.
        int searchID;
        try {
            scan = new Scanner(new File(textFileName));
        }

        // TODO Check if this try/catch and the corresponding if (scan != null) is the
        // right approach.
        catch (Exception e) { // had to use Exception rather than FileNotFoundException, since it's forbidden.
            System.err.println("Couldn't find the database text file named: " + textFileName);
            System.exit(1);
        }

        ReadText(scan);
        // rigorous error-checking of format and the content of the input (add this
        // later)
        // ReadText calls error-checking function before accepting new lines to the
        // database

        // need to process orders first, because customers and operators need to be able
        // to access orders
        processOrders();

        // create correct Persons type and store them in the class
        // define (attribute) customers to operators
        // define (attribute) orders to to customers
        processPersons();

        searchID = getIntValue(new Scanner(System.in));

        searchPrintPerson(searchID);

        // search person matching the ID
        // if found:
        // display the person's info

        scan.close(); }
    
/**
 * prints the Person type to the System.out
 * 
 * @param Person must be operator or customer, 
 * null will print warning but won't throw exception!
 * @throws IllegalArgumentException if Person is not operator or customer
 
 */
 private static void printPerson(person Person) throws IllegalArgumentException{
        if (Person == null) {
            System.out.println("No such operator/customer was found. Please try again.");
        }

        if (Person instanceof operator) {
            System.out.println("*** Operator Screen ***");
            ((operator) Person).print_operator();
            ((operator) Person).print_customers();
        }

        else if (Person instanceof customer) {
            System.out.println("*** Customer Screen ***");
            ((customer) Person).print_customer();
            ((customer) Person).print_orders();
        }

        else {
            throw new IllegalArgumentException("Person object is neither operator nor customer type.");
        }

    }


    private static void ReadText(Scanner scan) {
        String line;
        String[] wordList;

        // until end of file, process and append line-by-line
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            wordList = line.split(";", -1); // -1 so that empty strings will be accounted for during splitting
            // wordList = seperateWords(line, ';');

            if (isLineFormatValid(wordList) && !isDuplicateID(wordList[5])) {
                appendLine(wordList);
            }

            else {
                // ignore this line and continue
            }
        }

    }

    private static Integer getIntValue(Scanner scan) {
        Integer value;
        try {
            value = scan.nextInt();

            return value;
        }

      
        catch (Exception e) { // Had to use RuntimeException, since InputMismatchException is forbidden
            System.err.println("Invalid ID format.\nExitting program...\n");
            System.exit(1);
        }

        // CHECK is this return null here fine?
        return null;

    }

    /**
     * Checks if line format is valid. This method does NOT check if the contents
     * are legal (which is duplicate ID`s for multiple Persons)
     * 
     * @param wordList 
     * @return true if valid, false if invalid
     */
    public static boolean isLineFormatValid(String[] wordList) {
        
        // DISCUSS if parseInt is the right approach
        // DISCUSS discuss if format checking should've been done in the ReadText method

        if (wordList.length < 6) {
            return false;
        }
        // CHECK if `:` and `{}` at the same time is ok.
        switch (wordList[0]) {

        case ("order"): {
            if (wordList.length != 6) {
                return false;
            }

            try {
                /*
                 * Integer.parseInt(wordList[2]); Integer.parseInt(wordList[3]);
                 * Integer.parseInt(wordList[4]); Integer.parseInt(wordList[5]);
                 */
                if (wordList[1].length() < 1) {
                    return false;
                }
                if (Integer.parseInt(wordList[2]) <= 0) {
                    return false;
                }

                if (Integer.parseInt(wordList[3]) <= 0) {
                    return false;
                }

                // status can be 0, 1, 2, 3
                if (Integer.parseInt(wordList[4]) < 0 || Integer.parseInt(wordList[4]) > 4) {
                    return false;
                }

                if (Integer.parseInt(wordList[5]) <= 0) {
                    return false;
                }

                break;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        // TODO add string length check to the elements.
        case ("retail_customer"): {
            if (wordList.length != 7) {
                return false;
            }

            try { // CHECK if length checks are correct (correct index?)
                if (wordList[1].length() < 1)
                    return false;

                if (wordList[2].length() < 1)
                    return false;

                if (wordList[3].length() < 1)
                    return false;

                if (wordList[4].length() < 1)
                    return false;

                if (Integer.parseInt(wordList[5]) <= 0) {
                    return false;
                }

                if (Integer.parseInt(wordList[6]) <= 0) {
                    return false;
                }

                break;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }

        case ("corporate_customer"): {
            if (wordList.length != 8) { // CHECK check if this is the right length
                return false;
            }

            try {
                if (wordList[1].length() < 1)
                    return false;

                if (wordList[2].length() < 1)
                    return false;

                if (wordList[3].length() < 1)
                    return false;

                if (wordList[4].length() < 1)
                    return false;

                if (Integer.parseInt(wordList[5]) <= 0) {
                    return false;
                }

                if (Integer.parseInt(wordList[6]) <= 0) {
                    return false;
                }

                if (wordList[7].length() < 1)
                    return false;

                break;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }

        case ("operator"): {
            if (wordList.length != 7) {
                return false;
            }

            try {
                if (wordList[1].length() < 1)
                    return false;

                if (wordList[2].length() < 1)
                    return false;

                if (wordList[3].length() < 1)
                    return false;

                if (wordList[4].length() < 1)
                    return false;

                if (Integer.parseInt(wordList[5]) <= 0) {
                    return false;
                }

                if (Integer.parseInt(wordList[6]) <= 0) {
                    return false;
                }

                break;
            }

            catch (NumberFormatException e) {
                return false;
            }
        }

        default: {
            return false;
        }
        }

        return true;
    }


    /**
     * Checks if ID is duplicate, among the read lines (in textFile object)
     * @param ID must be integer convertable, non-null and positive
     * @throws IllegalArgumentException if ID can't be int convertable, if null or non-positive
     */
    private static boolean isDuplicateID(String ID) throws IllegalArgumentException {
        /*
         * boolean flag = false; // initially assume that it is not duplicate // check
         * if it's duplicate // CHECK if this is the right way to check for duplicates,
         * // DISCUSS if the inefficiency here permissible. for (int i = 0; i <
         * textFileLength; ++i) { if (!textFile[i][0].equals("order") &&
         * textFile[i][5].equals(ID)) { flag = true; break; } }
         */
        Integer IDint;

        try {
            IDint = Integer.valueOf(ID);
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID must be a number.");
        }

        return isDuplicateID(Integer.valueOf(ID));

    }

    /**
     * Checks if ID is duplicate, among the previously read lines (in textFile[][] object)
     * @throws IllegalArgumentException ID can't be null and must be positive
     */
    private static boolean isDuplicateID(Integer ID) throws IllegalArgumentException{
        boolean flag = false; // initially assume that it is not duplicate
        String str;

        String ID_str = String.valueOf(ID);

        if (ID == null) {
            throw new IllegalArgumentException("ID may not be null.");
        }

        if (ID <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }

        // check for duplicate id`s among non-order lines (which is customer or operator)
        for (int i = 0; i < textFileLength; ++i) {
            str = textFile[i][0];
            if (!(textFile[i][0].equals("order")) && textFile[i][5].equals(ID_str)) {
                flag = true;
                break;
            }

        }

        return flag;
    }

    /** add line to textFile[][] the database, to its end */
    private static void appendLine(String[] wordList) throws ArrayIndexOutOfBoundsException {
        textFile[textFileLength] = wordList;
        ++textFileLength;
    }

    /*
     * Adds orders to orders[] through textFile[][]
     */
    private static void processOrders() {
        for (int i = 0; i < textFileLength; ++i) {

            if (textFile[i][0].equals("order")) {
                addOrder(textFile[i]);
            }
        }
    }

    /**
     * Adds correct person subtype to the corresponding object (customers[] or operators[]).
     * since operators need customers, customers are processed first, then operators are processed second
     */
    private static void processPersons() {

        // Second process customers, operators need them.
        for (int i = 0; i < textFileLength; ++i) {

            // CHECK is this cast ok?
            // DISCUSS should we assign to variables to make them more readable?
            if (textFile[i][0].equals("retail_customer")) {
                addRetailCustomer(textFile[i]).define_orders(orders);

                // creates retail_customer

            }
            if (textFile[i][0].equals("corporate_customer")) {
                addCorporateCustomer(textFile[i]).define_orders(orders);

            }
        }

        // Third process operators last.
        for (int i = 0; i < textFileLength; ++i) {

            if (textFile[i][0].equals("operator")) {
                addOperator(textFile[i]).define_customers(customers);
            }

        }
    }

    // CHECK passed arguments to these add methods
    // appends retail customer to customers[]
    private static retail_customer addRetailCustomer(String[] wordList) {
        retail_customer newCustomer = new retail_customer(wordList[1], wordList[2], wordList[3], wordList[4],
                Integer.valueOf(wordList[5]), Integer.valueOf(wordList[6]));

        customers[customerCount] = newCustomer;
        ++customerCount;

        return newCustomer;
    }

    // appends corporatecustomer to customer[]
    private static corporate_customer addCorporateCustomer(String[] wordList) {
        corporate_customer newCustomer = new corporate_customer(wordList[1], wordList[2], wordList[3], wordList[4],
                Integer.valueOf(wordList[5]), Integer.valueOf(wordList[6]), wordList[7]);

        customers[customerCount] = newCustomer;
        ++customerCount;

        return newCustomer;
    }

    // appends operator to operators[]
    private static operator addOperator(String[] wordList) {
        operator newOperator = new operator(wordList[1], wordList[2], wordList[3], wordList[4],
                Integer.valueOf(wordList[5]), Integer.valueOf(wordList[6]));

        operators[operatorCount] = newOperator;
        ++operatorCount;

        return newOperator;
    }

    // CHECK if order(...) is correct (is there optional fields? etc.)

    // appends order to orders[]
    private static void addOrder(String[] wordList) {
        orders[orderCount] = new order(wordList[1], Integer.valueOf(wordList[2]), Integer.valueOf(wordList[3]),
                Integer.valueOf(wordList[4]), Integer.valueOf(wordList[5]));
        orderCount++;
    }

    /**
     * Searches person with <i>ID</i>, if existing will print their details, else print they don't exist
     */
    private static person searchPrintPerson(int ID) {
        person foundPerson = null;

        for (int i = 0; i < customerCount; ++i) {
            if (customers[i].ID == ID) {
                foundPerson = customers[i];
                break;
            }
        }

        for (int i = 0; i < operatorCount; ++i) {
            if (operators[i].ID == ID) {
                foundPerson = operators[i];
                break;
            }
        }

        if (foundPerson == null) {
            System.out.println("No operator/customer was found with ID " + ID + ". Please try again.");
        }

        else
            printPerson(foundPerson);
        return foundPerson;
    }

}
