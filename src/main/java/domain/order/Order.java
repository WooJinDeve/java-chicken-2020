package domain.order;

import domain.table.Table;

import java.util.List;

public class Order {
    Table table;
    List<OrderBasket> orderBaskets;

    public Order(Table table, List<OrderBasket> orderBaskets) {
        this.table = table;
        this.orderBaskets = orderBaskets;
    }

    public List<OrderBasket> getOrderBaskets() {
        return orderBaskets;
    }

    public static Order of(Table table, List<OrderBasket> orderBaskets) {
        return new Order(table, orderBaskets);
    }

    public int getFinalPayment() {
        return orderBaskets.stream()
                .mapToInt(OrderBasket::getPayment)
                .sum();
    }

    public void orderInformationOutput(){
        System.out.println("테이블 번호 :  " + table.toString());
        orderBaskets.forEach(OrderBasket::orderBasketInformationOutput);
    }
}
