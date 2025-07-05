package com.example.bsn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private String[] categoryNames;
    private int[] categoryImages;

    public CategoryAdapter(Context context, String[] categoryNames, int[] categoryImages) {
        this.context = context;
        this.categoryNames = categoryNames;
        this.categoryImages = categoryImages;
    }

    @Override
    public int getCount() { return categoryNames.length; }

    @Override
    public Object getItem(int position) { return categoryNames[position]; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.category_image);
        TextView textView = convertView.findViewById(R.id.category_name);

        imageView.setImageResource(categoryImages[position]);
        textView.setText(categoryNames[position]);

        return convertView;
    }
}
