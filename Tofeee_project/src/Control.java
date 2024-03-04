import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Control {
    Scanner in = new Scanner(System.in);

    private AccountManger account = new AccountManger();



    private void displayProducts(List<String> myList){
        int ch; int qnt; int i = 0;

        System.out.println("-----List of products-----");
        for(String item : myList){
            System.out.println(item);
        }

        do {
            if(i == 0)
              System.out.println("Which product do you want to add to cart? 1, 2, 3 ?  -4:exit-");
            else {
                System.out.println("\n----Wrong choice please choice number in range 1-4");
                System.out.println("\nWhich product do you want to add to cart? 1, 2, 3 ? -4:exit-");
            }
            ch = in.nextInt();
            i++;
        } while(ch < 1 || ch > 4);

        if(ch != 4) {
            System.out.println("How many you want from it");
            qnt = in.nextInt();

            account.getUser().getCart().addToCart(ch, qnt);
        }

    }

    public void run(){
        int ch; boolean validInfo = false; int x = 0;
        List<String> myList = new ArrayList<>();

        System.out.println("\n-----Welcome to toffee store-----\n" +
                "do you want to register or login?\n" +
                "1- Register\n" +
                "2- Login");
        ch = in.nextInt();

        if(ch == 1) {
            account.registerNewUser();
            run();

        } else if (ch == 2) {
            validInfo = account.loginUser();

        } else {
            System.out.println("Wrong choice");
            return;
        }

        // add products to the list of items
        myList.add("1- Toffee, Price: 200");
        myList.add("2- Jelly , Price: 200");
        myList.add("3- Chocolate, Price: 200");


        int choice; boolean stop = false;

        if(validInfo) {

            while (!stop) {
                System.out.println("What do you want to do?\n" +
                        "1- Display products\n" +
                        "2- Show shopping cart\n" +
                        "3- pay for the products in the cart\n" +
                        "4- Log out");

                choice = in.nextInt();

                Cart cart = account.getUser().getCart();
                switch (choice) {

                    case 1:
                        displayProducts(myList);

                        System.out.println("\nDo you want to put another product to cart? 1- Yes, 2- No");
                        ch = in.nextInt();

                        do {
                            displayProducts(myList);

                            System.out.println("\nDo you want to put another product to cart? 1- Yes, 2- No\n");
                            ch = in.nextInt();
                        } while (ch == 1);

                        System.out.println("\nDo you want to do anything else? 1-Yes, 2-No (No: EXIT FROM SYSTEM)");
                        choice = in.nextInt();
                        if (choice == 2)
                            stop = true;

                        break;

                    case 2:
                        cart.displayCart();

                        if(cart.getItems().size() !=0) {
                            System.out.println("\nDo you want to check out now? 1-Yes, 2-No");
                            choice = in.nextInt();
                        }
                        if (choice == 2) {
                            System.out.println("\nDo you want to do anything else? 1-Yes, 2-No (No: EXIT FROM SYSTEM)");
                            choice = in.nextInt();
                            if (choice == 2)
                                stop = true;

                        } else
                            cart.getOrder().makeOrder(account);

                        break;

                    case 3:
                        cart.displayCart();
                        cart.getOrder().makeOrder(account);
                        break;

                    case 4:
                        System.out.println("\n-----------Logout done successful-----------");
                        account.logOut();
                        run();
                        break;
                    default:
                        account.logOut();
                        run();

                }

            }
        } else{
            System.out.println("-- Wrong info, try again --");
            run();
        }


    }

    
}
