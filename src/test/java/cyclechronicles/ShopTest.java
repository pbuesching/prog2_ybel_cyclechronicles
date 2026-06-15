package cyclechronicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShopTest {

    Shop shop;

    @BeforeEach
    public void setUp() {
        // Given
        shop = new Shop();
    }

    public Order createOrderMock(Type bike, String name) {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(bike);
        when(order.getCustomer()).thenReturn(name);
        return order;
    }

    // TC1
    @Test
    public void testRace() {
        //Given
        Order order = createOrderMock(Type.RACE, "Markus");

        //When
        var result = shop.accept(order);

        //Then
        assertTrue(result);
    }

    // TC2
    @Test
    public void testGrenzwertAnzahlOrders() {
        //Given
        for (int i = 0; i < 4; i++) {
            Order order = createOrderMock(Type.SINGLE_SPEED, "Jan" + i);
            shop.accept(order);
        }
        Order order = createOrderMock(Type.SINGLE_SPEED, "Chris");

        //When
        var result = shop.accept(order);

        //Then
        assertTrue(result);
    }

    // TC3
    @Test
    public void testFixie() {
        //Given
        Order order = createOrderMock(Type.FIXIE, "Peter");

        //When
        var result = shop.accept(order);

        //Then
        assertTrue(result);
    }


    // TC4
    @Test
    public void testEBike() {
        //Given
        Order order = createOrderMock(Type.EBIKE, "Peter");

        //When
        var result = shop.accept(order);

        //Then
        assertFalse(result);
    }

    // TC5
    @Test
    public void testGravel() {
        //Given
        Order order = createOrderMock(Type.GRAVEL, "Ben");

        //When
        var result = shop.accept(order);

        //Then
        assertFalse(result);
    }

    // TC6
    @Test
    public void testDoubleOrder() {
        //Given
        Order order = createOrderMock(Type.RACE, "Peter");
        Order order2 = createOrderMock(Type.FIXIE, "Peter");

        //When
        shop.accept(order);
        var result = shop.accept(order2);

        //Then
        assertFalse(result);
    }

    // TC7
    @Test
    public void testTooManyOrders() {
        //Given
        for (int i = 0; i < 5; i++) {
            Order order = createOrderMock(Type.SINGLE_SPEED, "Peter" + i);
            shop.accept(order);
        }
        Order order = createOrderMock(Type.RACE, "Zoe");

        //When
        var result = shop.accept(order);

        //Then
        assertFalse(result);
    }
}
