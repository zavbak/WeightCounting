package ru.anit.weightcounting.ui.activityes;

import android.app.AlertDialog;
import android.app.DialogFragment;
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
import ru.anit.weightcounting.repository.products.RepositoryProductsI;
import ru.anit.weightcounting.repository.products.RepositoryProductsRealm;
import ru.anit.weightcounting.ui.adapters.MyListAdapter;
import ru.anit.weightcounting.ui.dialog.DialogCauntingBarcodeHelper;
import ru.anit.weightcounting.ui.dialog.DialogHelper;
import ru.anit.weightcounting.ui.fragments.DialogBarcodeFragment;

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

    @OnClick(R.id.btTestBarcode)
    void onClickBtTestBarcode(){

        DialogCauntingBarcodeHelper.CallBackChoicePositive positiveChoise = (siets, weight) -> {};
        DialogCauntingBarcodeHelper.CallBackChoiceNegative negativeChoise = () -> {};

        Context context = this;

        DialogCauntingBarcodeHelper.SettingsDialogBarcode settingsDialogBarcode =
                new DialogCauntingBarcodeHelper.SettingsDialogBarcode() {
                    @Override
                    public Context getContext() {
                        return context;
                    }

                    @Override
                    public String getMessage() {
                        return "Сообщение";
                    }

                    @Override
                    public DialogCauntingBarcodeHelper.CallBackChoicePositive getCallBackChoicePositive() {
                        return positiveChoise;
                    }

                    @Override
                    public DialogCauntingBarcodeHelper.CallBackChoiceNegative getCallBackChoiceNegative() {
                        return negativeChoise;
                    }

                    @Override
                    public String getBarcode() {
                        return "6545645564465";
                    }

                    @Override
                    public String getSeats() {
                        return "1";
                    }

                    @Override
                    public String getWeight() {
                        return "100";
                    }
                };


        AlertDialog alertDialog = DialogCauntingBarcodeHelper.getDialogText(settingsDialogBarcode).create();

        alertDialog.show();


    }

    @Override
    public void showDetailProduct(String id) {
        startActivity(DetailProductActivity.getIntent(this,id));
    }

}
