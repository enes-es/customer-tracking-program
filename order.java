package Homework1;

// FIXME if there's 0 count, don't print bro
public class order {

    public order(String product_name, int count, int total_price, int status, int customer_ID) {
        this.product_name = product_name;
        this.count = count;
        this.total_price = total_price;
        this.status = status;
        this.customer_ID = customer_ID;
    }

    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public void print_order() {
        System.out.print("Product name: " + product_name);
        System.out.print(" - Count: " + count);
        System.out.print(" - Total price: " + total_price);

        System.out.print(" - Status: " + getStatus(this.status) + ".\n");

    }

    /** returns corresponding string value of status:
     * 0 : Initialized
     * 1 : Processing
     * 2 : Completed
     * 3 : Cancelled
     * anything else: throws IllegalArgumentException
    */
    public String getStatus(int status) throws IllegalArgumentException {
        String status_string = new String();

        if (status == 0)
            status_string = "Initialized";

        else if (status == 1)
            status_string = "Processing";

        else if (status == 2)
            status_string = "Completed";

        else if (status == 3)
            status_string = "Cancelled";

        else {
            throw new IllegalArgumentException("Illegal status value.");
        }

        return status_string;
    }

    public int getCustomerID() {
        return customer_ID;
    }
}
