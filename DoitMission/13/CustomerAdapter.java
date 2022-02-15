package dev_allgot.understand.doitmission_13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        holder.setCustomer(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView birthText;
        TextView phoneText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            birthText = itemView.findViewById(R.id.birthText);
            phoneText = itemView.findViewById(R.id.phoneText);
        }

        public void setCustomer(Customer customer) {
            nameText.setText(customer.name);
            birthText.setText(customer.birth);
            phoneText.setText(customer.phone);
        }
    }
}
