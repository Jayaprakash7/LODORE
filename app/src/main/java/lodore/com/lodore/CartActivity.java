package lodore.com.lodore;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import lodore.com.lodore.Fragment.CheckOutFragment2;
import lodore.com.lodore.Pojo.CartDTO;
import lodore.com.lodore.adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    EditText editTextMain;
    Button btnHome;
    RecyclerView recyclerView;
    CartAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textKey", editTextMain.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {


        super.onRestoreInstanceState(savedInstanceState);
        String string = savedInstanceState.getString("textKey");
        editTextMain.setText(string);
    }

    public void checkOutFragment() {
        CheckOutFragment2 checkOutFragment2 = new CheckOutFragment2();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_container, checkOutFragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        editTextMain = (EditText) findViewById(R.id.edit_main);
        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        adapter = new CartAdapter(this, getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        setSupportActionBar(toolbar);

        btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckOutActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public static List<CartDTO> getData() {
        List<CartDTO> cartDTOList = new ArrayList<>();
        String[] title = {"الآسيوية", "الآسيوية"};
        String[] price = {"السع", "السعر"};
        int[] image = {R.drawable.perfume2, R.drawable.perfume4};

        for (int i = 0; i < title.length; i++) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.titleCart = title[i];
            cartDTO.priceCart = price[i];
            cartDTO.imageCart = image[i];

            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_cart) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
