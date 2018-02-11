package css.cis3334.pizzaorder;

/**
 * Created by tgibbons on 2/10/2017.
 * Update by mbutler 2/11/18
 */
public interface PizzaOrderInterface {
    // small mods to allow for passing of string constants for size
    String OrderPizza(String topping, String size, boolean extraCheese);
    Double getTotalBill();
    Double getPrice(String size);
    Double getExtraCheesePrice();
    void setDelivery(boolean deliver);
    boolean getDelivery();

}
