package ru.anit.weightcounting.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.app.App;
import ru.anit.weightcounting.mvp.model.entities.Product;



public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<Product> mProducts;
    private CallBackClick mCallBackClick;

    public MyListAdapter(RealmResults<Product> products,CallBackClick callBackClick) {
        mProducts = products;
        mProducts.addChangeListener(this);
        mCallBackClick = callBackClick;
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
        holder.setPosition(position);
    }

    @Override

    public int getItemCount() {
        return mProducts.size();
    }


    @Override
    public void onChange(Object element) {
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.info_text)
        TextView mTextTitle;

        int position;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(this);

        }

        public void setPosition(int position) {
            this.position = position;
            mTextTitle.setText(mProducts.get(position).getName());
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(App.getAppContext(),mProducts.get(position).getName(),Toast.LENGTH_SHORT).show();
            mCallBackClick.click(mProducts.get(position));

        }
    }


}