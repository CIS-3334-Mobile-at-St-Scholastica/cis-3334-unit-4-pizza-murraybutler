package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import our Pizza class for access to StringDefs
import css.cis3334.pizzaorder.Pizza;

/**
 * Created by tgibbons on 2/10/2017.
 * Update by mbutler 2/11/18
 */


public class MainActivity extends AppCompatActivity implements updateViewInterface {

    int id;
    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    TextView txtPizzasOrdered;
    Spinner spinnerToppings;
    PizzaOrderInterface pizzaOrderSystem;

    //Log TAG string
    private static final String TAG = "stringdef";

    private boolean cheese = false;
    private boolean deliver = false;

    private @Pizza.pizzaSize String size;
    private String topper;

    String[] topps = new String[] {
            "Pepperoni",
            "Chicken",
            "Vegatarian"
    };

    @Override
    @SuppressWarnings("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an pizza order system to use in the class for ordering pizzas.
        pizzaOrderSystem = new PizzaOrder(this);
        // Set up our radio buttons
        // int id = ((RadioGroup) findViewById(R.id.sizeGroup)).getCheckedRadioButtonId();
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);
        // review the lines below during the particpation activy and uncomment them
        // using StringDef values for sizes from Pizza class
        rbSmall.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.SMALL));
        rbMedium.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.MEDIUM));
        rbLarge.append(" -- Price: $" + pizzaOrderSystem.getPrice(Pizza.LARGE));

        // Set up the Check Boxes
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        // Set up the TextViews
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);

        // Set up the Spinner
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);
        //create ArrayAdapter
        ArrayAdapter<String> toparrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,topps);
        toparrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToppings.setAdapter(toparrAdapter);
        //create listener for spinner
        spinnerToppings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //required by the onItemSelectedListener interface
            @Override
            public void onItemSelected (AdapterView <?> parent, View view,int pos, long id){
                topper = parent.getItemAtPosition(pos).toString();
            }

            //required by the onItemSelectedListener interface
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing for this
            }
        });


    }

    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        // ****** Students need to add code here to get information from the UI widgets...

        // ****** Students need to modify the call to OrderPizza to order the type of pizza the user selects using the UI widgets

        // Unlike Delivery, the xtracheese is set in the instantiation of the Pizza class, so we check that inline.
        String orderDescription = pizzaOrderSystem.OrderPizza(topper, size, chkbxCheese.isChecked());


        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        // get the order total from the order system
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
        // add this pizza to the textview the lists the pizzas
        txtPizzasOrdered.append(orderDescription+"\n");

    }

    // Radio button input check and var set
    public String onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonSmall:
                if (checked)
                    size = Pizza.SMALL;
                    Log.v(TAG, Pizza.SMALL);
                break;
            case R.id.radioButtonMedium:
                if (checked)
                    size = Pizza.MEDIUM;
                    Log.v(TAG,Pizza.MEDIUM);
                break;
            case R.id.radioButtonLarge:
                if (checked)
                    size = Pizza.LARGE;
                    Log.v(TAG,Pizza.LARGE);
                break;
        }
        return size;
    }

    // checking for Delivery chkbox
    public void onCheckboxClicked(View view) {
        pizzaOrderSystem.setDelivery(chkbxDelivery.isChecked());
    }
}
