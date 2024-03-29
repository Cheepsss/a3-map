package Ui;

import Domain.Cake;
import Domain.Order;
import Service.File.CakeServiceTextFile;
import Service.File.OrderServiceTextFile;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Consumer;

public class Ui {

    private final CakeServiceTextFile cServ;
    private final OrderServiceTextFile oServ;

    private final Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Ui(CakeServiceTextFile cServ, OrderServiceTextFile oServ) {
        this.cServ = cServ;
        this.oServ = oServ;
    }

    public void menu() {
        System.out.println("--------------------------");
        System.out.println("""
                <<CRUD OPPERATIONS>>
                 1. Add cake
                 2. Display all cakes
                 3. Remove a cake
                 4. Update a cake

                 5. Add order
                 6. Display all orders
                 7. Remove an order
                 8. Update an order
                                
                 0. Exit""");
        System.out.println("--------------------------");

    }

    public void addCake() {
        try {
            System.out.print("Input cake id: ");
            int id = sc.nextInt();
            System.out.print("Input name: ");
            String name = sc.next();
            System.out.print("Input ingredients: ");
            String ingredients = sc.next();
            System.out.print("Input type: ");
            String type = sc.next();
            Cake cake = new Cake(id, name, ingredients, type);
            cServ.add(cake);
        } catch (Exception e) {
            System.out.println("Couldn't add cake! " + e.getMessage());
        }
    }

    public void displayCakesWithConsumer() {
        Consumer<Cake> getAllCakes = System.out::println;

        for (Cake cake : cServ.getAll()) {
            getAllCakes.accept(cake);
        }
    }

    public void removeCake() {
        try {
            System.out.print("Input cake id: ");
            int id = sc.nextInt();
            cServ.remove(id);
        } catch (Exception e) {
            System.out.println("Couldn't remove cake! " + e.getMessage());
        }
    }

    public void updateCake() {
        try {
            System.out.print("Input cake id: ");
            int id = sc.nextInt();
            System.out.print("Input name: ");
            String name = sc.next();
            System.out.print("Input ingredients: ");
            String ingredients = sc.next();
            System.out.print("Input type: ");
            String type = sc.next();
            Cake cake = new Cake(id, name, ingredients, type);
            cServ.update(cake);
        } catch (Exception e) {
            System.out.println("Couldn't update cake! " + e.getMessage());
        }
    }

    public void addOrder() {
        Order order = null;
        try {
            System.out.print("Input order id: ");
            int orderId = sc.nextInt();
            if (oServ.checkExistence(orderId))
                throw new Exception("Order id already exists!");
            System.out.print("Input cake id: ");
            int cakeId = sc.nextInt();

            Cake cake = null;
            if (!cServ.checkExistence(cakeId)) {
                System.out.print("Input name: ");
                String name = sc.next();
                System.out.print("Input ingredients: ");
                String ingredients = sc.next();
                System.out.print("Input type: ");
                String type = sc.next();
                cake = new Cake(cakeId, name, ingredients, type);
                cServ.add(cake);
            } else
                cake = cServ.getById(cakeId);
            System.out.print("Input date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(sc.next());
            System.out.print("Input client: ");
            String client = sc.next();

            order = new Order(orderId, cake, date, client);
            oServ.add(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayOrdersWithConsumer() {
        Consumer<Order> getAllOrders = System.out::println;

        for (Order a : oServ.getAll()) {
            getAllOrders.accept(a);
        }
    }

    public void removeOrder() {
        System.out.print("Input order id: ");
        int id = sc.nextInt();
        try {
            oServ.remove(id);
        } catch (Exception e) {
            System.out.println("Couldn't delete order! " + e.getMessage());
        }
    }

    public void updateOrder() {
        try {
            System.out.print("Input order id: ");
            int id = sc.nextInt();
            Order order = oServ.getById(id);
            System.out.print("Input date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(sc.next());
            System.out.print("Input client: ");
            String client = sc.next();

            order.setDate(date);
            order.setClient(client);
            oServ.update(order);
        } catch (Exception e) {
            System.out.println("Couldn't update order! " + e.getMessage());
        }
    }


    public void main() {
        System.out.println();

        boolean loop = true;
        while (loop) {
            menu();
            Scanner sc = new Scanner(System.in);
            System.out.println("Input your option: ");
            int option = sc.nextInt();
            switch (option) {
                case (1) -> addCake();
                case (2) -> displayCakesWithConsumer();
                case (3) -> removeCake();
                case (4) -> updateCake();
                case (5) -> addOrder();
                case (6) -> displayOrdersWithConsumer();
                case (7) -> removeOrder();
                case (8) -> updateOrder();
                case (0) -> {
                    System.out.print("Exit...");
                    loop = false;
                }
            }
        }
    }
}
