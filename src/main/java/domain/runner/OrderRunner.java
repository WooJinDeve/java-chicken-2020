package domain.runner;

import com.ibm.j9ddr.vm29.structure.__type_info_node;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.OrderBasket;
import domain.order.OrderRepository;
import domain.table.Table;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static domain.menu.MenuRepository.*;
import static domain.table.TableRepository.*;

public class OrderRunner {

    private static final int ADD_BASKET = 1;
    private static List<Table> tables = tables();
    private static List<Menu> menus = menus();

    private static OrderRepository orderRepository = OrderRepository.getInstance();
    public static void run(){
        OutputView.printTables(tables);
        Table table = findByTableNumber(InputView.inputTableNumber());
        List<OrderBasket> orderBaskets = new ArrayList<>();
        addOrderBasket(orderBaskets);
        createOrderAndSave(table, orderBaskets);
    }

    private static void addOrderBasket(List<OrderBasket> orderBaskets) {
        boolean isCheck = true;
        while (isCheck){
            OutputView.printMenus(menus);
            OrderBasket orderBasket = existedByMenuOrCreateOrderBasket(orderBaskets, findByMenuNumber(InputView.inputMenuNumber()));
            validateAmountAndAdd(orderBasket, InputView.inputMenuAmount());
            orderBaskets.add(orderBasket);
            isCheck = addBasketValidator();
        }
    }

    private static boolean addBasketValidator() {
        return InputView.inputAddBasket() == ADD_BASKET;
    }

    private static OrderBasket existedByMenuOrCreateOrderBasket(List<OrderBasket> orderBaskets, Menu menu){
        OrderBasket orderBasket = orderBaskets.stream()
                .filter(basket -> basket.getMenu().equals(menu))
                .findFirst()
                .orElseGet(() -> createOrderBasket(menu));
        orderBaskets.remove(orderBasket);
        return orderBasket;
    }

    private static OrderBasket createOrderBasket(Menu menu){
        return OrderBasket.of(menu);
    }

    private static void validateAmountAndAdd(OrderBasket orderBasket, int amount){
        orderBasket.validateAmount(amount);
        orderBasket.addAmount(amount);
    }

    private static void createOrderAndSave(Table table, List<OrderBasket> orderBaskets){
        Order order = Order.of(table, orderBaskets);
        orderRepository.saveOrder(order);
    }
}
