import java.util.Scanner;

public class Order {
    Scanner in = new Scanner(System.in);

    public void makeOrder(AccountManger acc) {
//        this.products = cart.getItems();
        Cart cart = acc.getUser().getCart(); int ch;

        if (cart.getItems().size() == 0) {
            System.out.println("\n---Your cart is empty---\n");
            return;
        }
        System.out.println("_________ORDER INFO__________\nSub total: " + cart.getSubTotal() +
                "\nShipping fees: " + cart.getFees() +
                "\nTotal: " + cart.getTotal() );
        System.out.println("Payment method: cash" +
                            " \n_______________________________\nAre you sure you want to make this order?" +
                            " 1- Yes, 2-No");
        ch = in.nextInt();
        if(ch == 1) {
            System.out.println("\n-----Order Placed successfully-----");
            // rand number for the order
            System.exit(0);
        }

    }



}
