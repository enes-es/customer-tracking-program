package Homework1;

public class operator extends person {
    public operator(String name, String surname, String address, String phone, int ID, int wage) {
        super(name, surname, address, phone, ID);
        this.wage = wage;
    }

    public void print_operator() {
        // TODO check if print format is valid
        // System.out.println("Operator: " + name + " " + surname + " " + address + " "
        // + phone + " " + ID + " " + wage);

        System.out.println("----------------------------");
        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Wage: " + wage);
        System.out.println("----------------------------");
 
    }

    public void print_customers() throws RuntimeException {
        String customer_type = new String();

        // System.out.println("----------------------------");
        
        if (customerCount == 0) {
            System.out.println("This operator doesn't have any customers.");
        }

        // TODO check if print format is valid
        for (int i = 0; i < customerCount; i++) {
            if (customers[i] instanceof retail_customer)
                customer_type = "retail customer";
            // else customer_type = "corporate customer";
            else if (customers[i] instanceof corporate_customer)
                customer_type = "corporate customer";
            // else throw new RuntimeException("Fatal error: underlying customer type is
            // neither retail_customer nor corporate_customer");
            // CHECK is this throw fine? should we even do the check here?

            System.out.println("Customer #" + (i+1) + " (a " + customer_type + ") :");
            customers[i].print_customer();
            customers[i].print_orders();
        System.out.println("----------------------------");

        }

    }
        /**
     * Adds relavent customers to this operator. Stops reading when null is encountered in customers[].
     */
    public void define_customers(customer[] customers) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] == null) { // DISCUSS is this check fitting, or should we use a different approach?
                break;
            }
            if (customers[i].getOperatorID() == this.ID) {
                this.addCustomer(customers[i]);
            }
        }
    }

    public customer addCustomer(customer obj) {
        customers[customerCount] = obj;
        ++customerCount;

        return customers[customerCount];
    }

    protected int wage;
    protected customer[] customers = new customer[100];
    protected int customerCount = 0;
}
