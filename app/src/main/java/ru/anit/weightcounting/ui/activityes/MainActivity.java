package ru.anit.weightcounting.ui.activityes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.api.realm.RealmMigration;
import ru.anit.weightcounting.mvp.model.entities.Product;
import ru.anit.weightcounting.mvp.presenters.MainActivityPresenter;
import ru.anit.weightcounting.mvp.views.MainActivityView;
import ru.anit.weightcounting.ui.adapters.MyListAdapter;

public class MainActivity extends BaseMvpActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Realm mRealm;


    @BindView(R.id.tv_message)
    TextView tvMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        mRealm = Realm.getDefaultInstance();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyListAdapter(mRealm.where(Product.class).findAll()));

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @OnClick(R.id.bt_add_product)
    void onClickBtAddProduct(){
        mPresenter.showDtailProducrion(null);
    }

    @Override
    public void showDetailProduct(String id) {
        startActivity(DetailProductActivity.getIntent(this));
    }
}
