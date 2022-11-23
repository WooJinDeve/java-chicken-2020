package domain.runner;

import domain.menu.Category;
import domain.order.Order;
import domain.order.OrderBasket;
import domain.order.OrderRepository;
import domain.order.PayGroupAdvanced;
import domain.table.Table;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static domain.order.OrderRepository.*;
import static domain.table.TableRepository.tables;

public class PaymentRunner {

    private static List<Table> tables = tables();
    public static void run(){
        OutputView.printTables(tables);
        List<Order> orders = findAllByTableNumber(InputView.inputTableNumberToPayment());
        if (verifyPayment(orders)){
            orders.forEach(Order::orderInformationOutput);
            PayGroupAdvanced payType = PayGroupAdvanced.findByPayType(InputView.inputPayment());
            double price = discount(orders, payType, getOrdersByTotalPayment(orders));
            OutputView.printTotalPayment(price);
        }
    }

    private static int countByMenuCategoryChicken(List<Order> orders){
        return orders.stream().mapToInt(order -> order.getOrderBaskets().stream()
                .filter(orderBasket -> orderBasket.getMenu().hasCategory(Category.CHICKEN))
                .mapToInt(OrderBasket::getAmount)
                .sum()).sum();
    }

    private static double discount(List<Order> orders, PayGroupAdvanced payType, int price){
        int count = countByMenuCategoryChicken(orders);
        if (count >= 0)
            price -= count * 1000;
        return PayGroupAdvanced.discountPrice(payType, price);
    }


    private static boolean verifyPayment(List<Order> orders){
        return getOrdersByTotalPayment(orders) > 0;
    }

    private static int getOrdersByTotalPayment(List<Order> orders){
        return orders.stream()
                .mapToInt(Order::getFinalPayment)
                .sum();
    }
}
