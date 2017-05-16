package ru.anit.weightcounting.ui.activityes;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.presenters.ListBarcodePresenter;
import ru.anit.weightcounting.mvp.views.DialogBarcodeCountingView;
import ru.anit.weightcounting.mvp.views.ListBarcodeView;
import ru.anit.weightcounting.repository.barcode.BarcodeDataBroadcastReceiver;
import ru.anit.weightcounting.ui.dialog.DialogBarcodeCounting;

/**
 * Created by Alex on 15.05.2017.
 */

public class ListBarcodeActivity extends BaseMvpActivity implements ListBarcodeView {

    BarcodeDataBroadcastReceiver mBarcodeDataBroadcastReceiver;

    @InjectPresenter
    ListBarcodePresenter mPresenter;

    DialogBarcodeCountingView barcodeCountingDilog;


    public static Intent getIntent(final Context context, String id) {
        Intent intent = new Intent(context, ListBarcodeActivity.class);
        intent.putExtra("id",id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barcode);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        mBarcodeDataBroadcastReceiver = new BarcodeDataBroadcastReceiver(barcode -> {mPresenter.setBarcode(barcode);});

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onStop();
    }

    @OnClick(R.id.btTestBarcode)
    void onClickBtTestBarcode(){
        mPresenter.setBarcode("05204654654564564156165");
    }




    @Override
    public void showDialogBarcode(CharSequence title, CharSequence message, String sites, String weight) {
        Context context = this;

        DialogBarcodeCountingView barcodeCountingDilog = new DialogBarcodeCounting(this);
        barcodeCountingDilog.setTitle(title);
        barcodeCountingDilog.setMessage(message);
        barcodeCountingDilog.show();

    }

    @Override
    public void dissmisDialogBarcode() {
        if(barcodeCountingDilog != null){
            barcodeCountingDilog.dismiss();
        }
    }

    @Override
    public void registerBarcodeReceiver() {

        IntentFilter intentFilter = new IntentFilter("DATA_SCAN");
        registerReceiver(mBarcodeDataBroadcastReceiver, intentFilter);
    }

    @Override
    public void unregisterReceiver() {
        unregisterReceiver(mBarcodeDataBroadcastReceiver);
    }





}
