package ru.anit.weightcounting.ui.activityes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.presenters.ListBarcodePresenter;
import ru.anit.weightcounting.mvp.views.ListBarcodeView;
import ru.anit.weightcounting.repository.barcode.BarcodeDataBroadcastReceiver;

/**
 * Created by Alex on 15.05.2017.
 */

public class ListBarcodeActivity extends BaseMvpActivity implements ListBarcodeView {

    BarcodeDataBroadcastReceiver mBarcodeDataBroadcastReceiver;

    @InjectPresenter
    ListBarcodePresenter mPresenter;


    public static Intent getIntent(final Context context, String id) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        intent.putExtra("id",id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        mPresenter.setProductId(id);

        mBarcodeDataBroadcastReceiver = new BarcodeDataBroadcastReceiver(barcode -> {mPresenter.scanBarcode(barcode);});
    }
}
