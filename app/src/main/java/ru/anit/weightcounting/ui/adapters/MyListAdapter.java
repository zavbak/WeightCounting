package ru.anit.weightcounting.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by user on 14.05.2017.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<Product> mProducts;

    public MyListAdapter(RealmResults<Product> books) {
        mProducts = books;
        mProducts.addChangeListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextTitle.setText(mProducts.get(position).getName() + "" + mProducts.get(position).getId());
    }

    @Override

    public int getItemCount() {
        return mProducts.size();
    }


    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextTitle;

        public ViewHolder(View v) {
            super(v);
            mTextTitle = (TextView) v.findViewById(R.id.info_text);

        }
    }
}