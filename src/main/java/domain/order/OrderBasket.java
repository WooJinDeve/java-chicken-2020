package domain.order;

import domain.menu.Menu;

import java.util.Objects;

public class OrderBasket {
    private static final int MAX_MENU_AMOUNT = 99;
    private Menu menu;
    private int amount;

    public OrderBasket(Menu menu, int amount) {
        this.menu = menu;

        validateAmount(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount){
        this.amount += amount;
    }

    public static OrderBasket of(Menu menu){
        return new OrderBasket(menu, 0);
    }

    public Menu getMenu() {
        return menu;
    }


    public int getPayment(){
        return menu.getPrice() * amount;
    }

    public void validateAmount(int amount){
        if(MAX_MENU_AMOUNT < amount)
            throw new IllegalArgumentException();
    }

    public void orderBasketInformationOutput(){
        System.out.println("[ " + menu.getName() + " ] " + amount + " 개 " + "[TotalPrice] " + getPayment());
    }

}
