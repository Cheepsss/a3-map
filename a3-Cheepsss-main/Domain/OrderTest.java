package Domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testGetCake() {
        Cake cake = new Cake(1, "Chocolate Cake", "Flour, Sugar, Cocoa", "Chocolate");
        Order order = new Order(1, cake, LocalDate.now(), "John Doe");
        assertEquals(cake, order.getCake());
    }

    @Test
    void testSetCake() {
        Cake cake = new Cake(1, "Chocolate Cake", "Flour, Sugar, Cocoa", "Chocolate");
        Order order = new Order(1, null, LocalDate.now(), "John Doe");
        order.setCake(cake);
        assertEquals(cake, order.getCake());
    }

    @Test
    void testGetDate() {
        LocalDate date = LocalDate.now();
        Order order = new Order(1, null, date, "John Doe");
        assertEquals(date, order.getDate());
    }

    @Test
    void testSetDate() {
        LocalDate date = LocalDate.now();
        Order order = new Order(1, null, null, "John Doe");
        order.setDate(date);
        assertEquals(date, order.getDate());
    }

    @Test
    void testGetClient() {
        Order order = new Order(1, null, LocalDate.now(), "John Doe");
        assertEquals("John Doe", order.getClient());
    }

    @Test
    void testSetClient() {
        Order order = new Order(1, null, LocalDate.now(), null);
        order.setClient("Jane Doe");
        assertEquals("Jane Doe", order.getClient());
    }

    @Test
    void testToString() {
        Cake cake = new Cake(1, "Chocolate Cake", "Flour, Sugar, Cocoa", "Chocolate");
        LocalDate date = LocalDate.now();
        Order order = new Order(1, cake, date, "John Doe");

        String expected = "Order{\n" +
                "id = 1\n" +
                "Cake{\n" +
                "id = 1\n" +
                "name = Chocolate Cake\n" +
                "type = Chocolate\n" +
                "ingredients = Flour, Sugar, Cocoa\n" +
                "}\n" +
                "date = 2023-12-14\n" +
                "client = John Doe\n" +
                "}\n";
        assertEquals(expected, order.toString());
    }
}