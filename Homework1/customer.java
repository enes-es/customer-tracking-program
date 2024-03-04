package Homework1;

public class customer extends person {
    // TODO is orders = null necessary or valid?
    public customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
        super(name, surname, address, phone, ID);
        this.operator_ID = operator_ID;
    }

    public customer(String name, String surname, String address, String phone, int ID, order[] orders) {
        super(name, surname, address, phone, ID);
        this.orders = orders;
    }

    public void print_customer() {

        System.out.println("Name & Surname: " + name + " " + surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Operator_ID: " + operator_ID);
    }

    // CHECK is orderCount == 0 {...} necessary or acceptable?
    public void print_orders() {
        if (orderCount == 0)
        {
            System.out.println("This customer has no orders");
        }
        for (int i = 0; i < orderCount; ++i) {
            System.out.print("Order #" + (i+1));
            System.out.print(" => ");
            orders[i].print_order();
        }
    }

    public int getOperatorID() {
        return operator_ID;
    }

    // TODO is protected valid for this method? should it be public?
    // DISCUSS if orders.length should be switched by a null-check, since array may not be full
    // TODO implement the same decision with other define functions (define_customers etc.)

    /**
     * Adds relavent orders to this customer. Stops reading when null is encountered in orders.
     */
    protected void define_orders(order[] orders) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) { // DISCUSS is this check fitting, or should a different approach be taken?
                break;
            }
            if (orders[i].getCustomerID() == this.ID) {
                this.addOrder(orders[i]);
            }
        }
    }

    protected void addOrder(order order) {
        orders[orderCount] = order;
        ++orderCount;
    }

    // TODO check if protected is valid for these variables
    protected order[] orders = new order[100]; // DISCUSS should this be initialized here?
    protected int orderCount = 0;
    protected int operator_ID;

}
