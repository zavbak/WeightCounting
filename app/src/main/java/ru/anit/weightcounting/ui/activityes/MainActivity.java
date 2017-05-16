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
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.presenters.MainActivityPresenter;
import ru.anit.weightcounting.mvp.views.DialogBarcodeCountingView;
import ru.anit.weightcounting.mvp.views.MainActivityView;
import ru.anit.weightcounting.repository.products.RepositoryProductsI;
import ru.anit.weightcounting.repository.products.RepositoryProductsRealm;
import ru.anit.weightcounting.ui.adapters.MyListAdapter;
import ru.anit.weightcounting.ui.dialog.DialogBarcodeCounting;

public class MainActivity extends BaseMvpActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Realm mRealm;

    @BindView(R.id.tv_message)
    TextView tvMessage;


    RepositoryProductsI mRepositoryProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRepositoryProducts = new RepositoryProductsRealm();

        mRealm = Realm.getDefaultInstance();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyListAdapter(mRepositoryProducts.getListProductsAll(),
                p -> {mPresenter.showDtailProducrion(Long.toString(p.getId()));}));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close(); // Remember to close Realm when done.
    }

    @OnClick(R.id.bt_add_product)
    void onClickBtAddProduct(){
        mPresenter.showDtailProducrion(null);
    }



    @Override
    public void showDetailProduct(String id) {
        startActivity(DetailProductActivity.getIntent(this,id));
    }

}
