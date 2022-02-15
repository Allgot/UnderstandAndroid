package dev_allgot.understand.doitmission_14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView productsView;
    ProductAdapter adapter = new ProductAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsView = findViewById(R.id.productsView);
        adapter.addProduct(new Product(R.drawable.product1, "R.A.T. 1+", "$10", "Basic model"));
        adapter.addProduct(new Product(R.drawable.product2, "R.A.T. 2+", "$20", "Basic+ model"));
        adapter.addProduct(new Product(R.drawable.product3, "R.A.T. 4+", "$40", "Middle model"));
        adapter.addProduct(new Product(R.drawable.product4, "R.A.T. PRO S3", "$50", "Middle+ model"));
        adapter.addProduct(new Product(R.drawable.product5, "R.A.T. 6+", "$80", "Flagship model"));
        adapter.addProduct(new Product(R.drawable.product6, "R.A.T. 8+", "$100", "Flagship+ model"));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        productsView.setLayoutManager(layoutManager);
        productsView.setAdapter(adapter);

        OnProductTouchListener listener = new OnProductTouchListener() {
            @Override
            public void onProductTouch(ProductAdapter.ViewHolder holder, View view, int position) {
                String name = holder.nameText.getText().toString();
                String price = holder.priceText.getText().toString();
                Toast.makeText(getApplicationContext(), name + ", " + price, Toast.LENGTH_LONG).show();
            }
        };

        adapter.setOnProductTouchListener(listener);
    }
}
