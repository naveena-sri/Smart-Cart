package com.example.bsn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> cartItems;
    private TextView totalPriceText;

    public CartAdapter(Context context, List<CartItem> cartItems,TextView totalPriceText) {
        this.context = context;
        this.cartItems = cartItems;
        this.totalPriceText = totalPriceText;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.cartItemImage);
        TextView nameView = convertView.findViewById(R.id.cartName);
        TextView priceView = convertView.findViewById(R.id.cartPrice);
        ImageView removeButton = convertView.findViewById(R.id.remove_button);


        CartItem cartItem = cartItems.get(position);

        imageView.setImageResource(cartItem.getImageResId());
        nameView.setText(cartItem.getProductName() + "\nWeight: " + cartItem.getWeight() + cartItem.getUnit() + "\nPrice: ₹" + cartItem.getPrice());
        priceView.setText("₹" + cartItem.getPrice());

        removeButton.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyDataSetChanged();
            updateTotalPrice();  // Call the method to update the total price
        });

        return convertView;
    }
    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) total += item.getPrice();
        totalPriceText.setText("Total Price: ₹" + total);
    }

}
