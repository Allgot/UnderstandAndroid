package dev_allgot.understand.doitmission_14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> products = new ArrayList<Product>();
    OnProductTouchListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOnProductTouchListener(OnProductTouchListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView nameText;
        TextView priceText;
        TextView explanationText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            nameText = itemView.findViewById(R.id.nameText);
            priceText = itemView.findViewById(R.id.priceText);
            explanationText = itemView.findViewById(R.id.explanationText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onProductTouch(ViewHolder.this, v, getAdapterPosition());
                }
            });
        }

        public void setProduct(Product product) {
            if(product.imageRId != -1) productImage.setImageResource(product.imageRId);
            nameText.setText(product.name);
            priceText.setText(product.price);
            explanationText.setText(product.explanation);
        }
    }
}
