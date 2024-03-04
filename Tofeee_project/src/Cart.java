//import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Cart {
    Scanner in = new Scanner(System.in);

    private List<String> items = new ArrayList<>();
//    private List<Integer> quantities = new ArrayList<>();
    private double subTotal = 0;
    private double fees = 50;
    private double total = 0;
    private Order order = new Order();



    // Getters
    public List<String> getItems() {
        return items;
    }
    public Order getOrder(){
        return order;
    }

    public double getSubTotal(){
        return subTotal;
    }
    public double getFees() {
        return fees;
    }
    public double getTotal(){
        return total;
    }

    public void displayCart(){
        System.out.println("-----Shopping cart-----\n");
        if(items.size() == 0) {
            System.out.println("---Your cart is empty---");
            return;
        }
        for (String item : items) {
            System.out.println(item + "\n");
        }

        System.out.println("___________________\nSub total: " + subTotal +
                            "\nShipping fees: " + fees +
                            "\nTotal: " + total + "\n___________________");
    }


     public void addToCart(int ch, int qnt){

        switch (ch){

            case 1:
               items.add("Toffee, unit/s: " + qnt + ", 200$ for unit. Total= " + qnt * 200.0 );
               subTotal += (qnt * 200.0);
//                quantities.add(qnt);
                break;
            case 2:
                items.add("Jelly, unit/s: " + qnt  + ", 200$ for unit. Total= " + qnt * 200.0 );
                subTotal += (qnt * 200.0);
//                quantities.add(qnt);
                break;
            case 3:
                items.add("Chocolate, unit/s: " + qnt  + ", 200$ for unit. Total= " + qnt * 200.0 );
                subTotal += (qnt * 200.0);
//                quantities.add(qnt);
                break;

            default:
                break;
        }

        total = subTotal + fees;
    }



}
