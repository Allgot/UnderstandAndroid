package dev_allgot.understand.doitmission_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView customersView;
    CustomerAdapter adapter = new CustomerAdapter();
    TextView nocText;
    EditText nameEdit;
    EditText birthEdit;
    EditText phoneEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nocText = findViewById(R.id.nocText);
        nameEdit = findViewById(R.id.nameEdit);
        birthEdit = findViewById(R.id.birthEdit);
        phoneEdit = findViewById(R.id.phoneEdit);

        customersView = findViewById(R.id.customersView);
        customersView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        customersView.setLayoutManager(layoutManager);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEdit.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Name field is empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(birthEdit.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Birth date field is empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(phoneEdit.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Phone number field is empty!", Toast.LENGTH_LONG).show();
                    return;
                }

                String name = nameEdit.getText().toString();
                String birth = birthEdit.getText().toString();
                String phone = phoneEdit.getText().toString();

                adapter.addCustomer(new Customer(name, birth, phone));
                adapter.notifyItemChanged(adapter.getItemCount() - 1);

                nameEdit.setText("");
                birthEdit.setText("");
                phoneEdit.setText("");

                String msg = adapter.getItemCount()+ " 명";
                nocText.setText(msg);
            }
        });
    }
}
