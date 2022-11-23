package domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {

    private static List<Order> orders = new ArrayList<>();
    private static OrderRepository orderRepository = new OrderRepository();
    private OrderRepository(){}

    public static OrderRepository getInstance(){
        return orderRepository;
    }


    public static void saveOrder(Order order){
        orders.add(order);
    }

    public static List<Order> findAllByTableNumber(int tableNumber){
        return orders.stream()
                .filter(order -> order.table.hasNumber(tableNumber))
                .collect(Collectors.toList());
    }
}
