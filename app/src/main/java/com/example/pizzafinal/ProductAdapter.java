package com.example.pizzafinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public abstract class ProductAdapter<productList, product> extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mctx;
    private List<product> productList;

    public ProductAdapter(Context mctx, List<Product> productList) {
        this.mctx = mctx;
        this.productList = (List<product>) productList;
    }

    //private List<Product>productList;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new ProductViewHolder(view);
    }

    private void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        final Product product = (Product) productList.get(position);

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        //
        Glide.with(mctx)
                .load(product.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textViewTitle,textViewDesc, textViewRating, textViewPrice;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            relativeLayout=itemView.findViewById(R.id.relative);
        }
    }
}
