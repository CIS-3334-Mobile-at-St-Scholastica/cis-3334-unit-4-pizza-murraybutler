package css.cis3334.pizzaorder;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tgibbons on 2/10/2017.
 * Update by mbutler 2/11/18
 */

public class Pizza {
    // public enum pizzaSize { SMALL, MEDIUM, LARGE};
    public static final Double SMALL_PRICE = 7.99;
    public static final Double MEDIUM_PRICE = 9.99;
    public static final Double LARGE_PRICE = 11.99;
    public static final Double EXTRA_CHEESE_PRICE = 1.45;

    private String topping;
    private boolean extraCheese;
    private @pizzaSize String size;
    private Double price;
    private String description;

    //Log TAG string
    private static final String TAG = "stringdef";

    //StringDefs for sizes
    public static final String SMALL = "small";
    public static final String MEDIUM = "medium";
    public static final String LARGE = "large";
    //Setups of the enum replacement with the StringDef
    @StringDef({SMALL,MEDIUM,LARGE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface pizzaSize{};

    //@pizzaSize int curSize = LARGE;

    public Pizza(String topping, @pizzaSize String size, boolean extraCheese) {
        this.topping = topping;
        this.size = size;
        this.extraCheese = extraCheese;
        if (size==this.SMALL) {
            price = SMALL_PRICE;
            description = "Small " + topping + " pizza";
            Log.v(TAG,"Size is small");
        } else if (size==this.MEDIUM) {
            price = MEDIUM_PRICE;
            description = "Medium " + topping + " pizza";
            Log.v(TAG,"Size is medium");
        } else {
            price = LARGE_PRICE;
            description = "Large " + topping + " pizza";
            Log.v(TAG,"Size is large");
        }
        if (extraCheese) {
            price += EXTRA_CHEESE_PRICE;;
            description += " with extra cheese";
        }
    }

    public Double getPrice() {
        return price;
    }

    public String toString() {
        return description;
    }

    // Possibly useless methods, but I had an idea to use these instead of importing Pizza to the main class
    @pizzaSize
    public String getCurrentSize() {
        return size;
    }

    public void setCurrentSize(@pizzaSize String icurSize) {
        size = icurSize;
    }

}
