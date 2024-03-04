import Domain.Cake;
import Domain.Order;
import Repository.File.FileRepository;
import Repository.LoadRepository;
import Service.File.OrderServiceTextFile;
import Service.File.CakeServiceTextFile;
import Ui.Ui;

import java.time.LocalDate;


public class Main {

    public static void addEntries(CakeServiceTextFile cService, OrderServiceTextFile oService) {


        Cake c1 = new Cake(1, "asdfsd", "adsfdsa", "adasdfs");
        Cake c2 = new Cake(2, "asdfdsa", "asdfads", "adsfsd");
        Cake c3 = new Cake(3, "asdfdsa", "asdf", "adsdfds");

        Order o1 = new Order(1, c1, LocalDate.parse("2020-05-13"), "dsfsdfe");
        Order o2 = new Order(2, c2, LocalDate.parse("2019-07-26"), "sdfsdfds");
        Order o3 = new Order(3, c3, LocalDate.parse("2022-10-03"), "dsfsdf");

        try {

            cService.add(c1);
            cService.add(c2);
            cService.add(c3);

            oService.add(o1);
            oService.add(o2);
            oService.add(o3);

        } catch (Exception e) {
            System.out.println("Couldn't add the entries! " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        var cakesRepository = LoadRepository.createCakeRepository();
        CakeServiceTextFile cakesService = new CakeServiceTextFile((FileRepository<Cake>) cakesRepository);

        var ordersRepository = LoadRepository.createOrderRepository();
        OrderServiceTextFile ordersService = new OrderServiceTextFile((FileRepository<Order>) ordersRepository);

        Ui ui = new Ui(cakesService, ordersService);
        ui.main();
    }
}