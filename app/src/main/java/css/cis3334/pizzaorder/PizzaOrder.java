package css.cis3334.pizzaorder;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

// Pizza class import
import css.cis3334.pizzaorder.Pizza;

/**
 * Created by tgibbons on 2/10/2017.
 * Update by mbutler 2/11/18
 */
public class PizzaOrder implements PizzaOrderInterface {

    public static final Double DELIVERY_PRICE = 2.50;

    private updateViewInterface view;           // link to the activity displaying the orders
    private List<Pizza> pizzasInOrder;          // list of pizzas ordered so far
    private boolean delivery;                   // true if customer wants order delivered

    public PizzaOrder (updateViewInterface view) {
        this.view = view;
        pizzasInOrder = new ArrayList<Pizza>();
    }

    @Override
    public String OrderPizza(String topping, @Pizza.pizzaSize String size, boolean extraCheese){
        // Commented out because I used the StringDef from Pizza to do this
        /* Pizza.pizzaSize size;
        if (size == Pizza.SMALL) {
            size = Pizza.SMALL;
        } else  if (size == Pizza.) {
            size = Pizza.MEDIUM;
        } else {
            size = Pizza.LARGE;
        } */
        Pizza newPizza = new Pizza(topping, size, extraCheese);
        pizzasInOrder.add(newPizza);
        view.updateOrderStatusInView(newPizza.toString() + " added to order");
        startPizzaTimer();
        return newPizza.toString();             // return a description of what was ordered
    }

    @Override
    public Double getTotalBill() {
        Double total = 0.0;
        for (Pizza p:pizzasInOrder ){
            total += p.getPrice();
        }
        if (delivery) {
            total += DELIVERY_PRICE;
        }
        return total;
    }

    @Override
    public Double getPrice(@Pizza.pizzaSize String size) {
        Double price = 0.0;

        if (size == Pizza.SMALL) {
            price = Pizza.SMALL_PRICE;
        }
        if (size == Pizza.MEDIUM) {
            price =  Pizza.MEDIUM_PRICE;
        }
        if (size == Pizza.LARGE) {
            price = Pizza.LARGE_PRICE;
        }
        return price;
    }

    @Override
    public Double getExtraCheesePrice() {
        return Pizza.EXTRA_CHEESE_PRICE;
    }

    @Override
    public void setDelivery(boolean deliver) {
        this.delivery = deliver;
    }

    @Override
    public boolean getDelivery() {
        return delivery;
    }

    /**
     * This class implements a timer for the baking pizza
     */
    private static Runnable pizzaTimer;
    private Handler handler;
    private void startPizzaTimer(){
        handler = new Handler();
        pizzaTimer = new PizzaTimer();
        handler.postDelayed(pizzaTimer, 1000);
    }
    private class PizzaTimer implements Runnable {
        private Integer count = 0;
        @Override
        public void run() {
            view.updateOrderStatusInView("Starting timer ");
            count++;
            if (count > 4) {
                view.updateOrderStatusInView("Pizza ready to eat");
            } else if (count > 3) {
                view.updateOrderStatusInView("Pizza is cooling");
                handler.postDelayed(this, 2000);        // cool pizza for 2 seconds
            } else if (count > 2) {
                view.updateOrderStatusInView("Pizza is baking");
                handler.postDelayed(this, 5000);        // bake pizza for 5 seconds
            } else {
                view.updateOrderStatusInView("Pizza is being prepared " );
                handler.postDelayed(this, 2000);        // wait 2 seconds for pizza to be prepared
            }

        }
    }





}
