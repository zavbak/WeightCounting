package ru.anit.weightcounting.ui.activityes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.anit.weightcounting.api.BarcodeDataBroadcastReceiver;
import ru.anit.weightcounting.mvp.views.DetailProductView;
import ru.anit.weightcounting.mvp.presenters.DetailProductPresenter;


import ru.anit.weightcounting.R;
import ru.anit.weightcounting.ui.dialog.DialogHelper;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class DetailProductActivity extends BaseMvpActivity implements DetailProductView {

    @InjectPresenter
    DetailProductPresenter mPresenter;

    BarcodeDataBroadcastReceiver mBarcodeDataBroadcastReceiver;

    @BindView(R.id.tv_initial_barcode)
    TextView tvInitialBarcode;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_start)
    TextView tvStart;

    @BindView(R.id.tv_finish)
    TextView tvFinish;

    @BindView(R.id.tv_coefficient)
    TextView tvСoefficient;

    @BindView(R.id.tv_message)
    TextView tvMessage;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);

        mBarcodeDataBroadcastReceiver = new BarcodeDataBroadcastReceiver(barcode -> {mPresenter.setInitBarcode(barcode);});
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

    @Override
    public void refresh() {

        String barcode = mPresenter.getInitialBarcode();
        String name = mPresenter.getName();
        int start = mPresenter.getStart();
        int finish = mPresenter.getFinish();
        float coef = mPresenter.getCoefficient();


        tvInitialBarcode.setText("" + barcode);
        tvName.setText("Наименование: " + name);
        tvStart.setText("Начальная позиция: " + (start + 1));
        tvFinish.setText("Конечная позиция: " + (finish +1));
        tvСoefficient.setText("Коэффицент: " + coef);

        String message = "";

        if(barcode != null){

            if(start > finish){
                message = "Ошибка: Начальной позиции";
            }else if(finish > barcode.length()){
                message = "Ошибка: Конечной позиции";
            }else if(start == finish){
                message = "Ошибка: Начальной позиция = Конечной позиции";
            }else {

                final SpannableStringBuilder text = new SpannableStringBuilder(barcode);
                final ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));
                text.setSpan(style, start, finish, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                tvInitialBarcode.setText(text);


                int simbolsWeight = 0;
                try {
                    simbolsWeight = Integer.parseInt(barcode.substring(start,finish));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if(simbolsWeight == 0){
                    message = "Ошибка получения веса";
                }else{


                    float weight = simbolsWeight*coef;

                    message = String.format("Получили вес: %d x %f = %f", simbolsWeight, coef,weight);

                }

            }
        }

        tvMessage.setText(message);




    }

    //**********************************************************************************************
    //                              event

    @OnClick(R.id.tv_name)
    void onClickTvName(){
        mPresenter.clickName();
    }

    @OnClick(R.id.tv_start)
    void onClickTvStartPosition(){
        mPresenter.clickStartPosition();
    }

    @OnClick(R.id.tv_finish)
    void onClickTvFinishPosition(){
        mPresenter.clickFinishPosition();
    }

    @OnClick(R.id.tv_coefficient)
    void onClickTvInitialBarcode(){
        mPresenter.clickCoefficient();
    }



    //**********************************************************************************************
    //                              Dilod

    @Override
    public void showDialogName(String defaultStr) {
        AlertDialog alertDialog = DialogHelper.getDialogText(this,"Введи наименование",defaultStr,
                s -> mPresenter.setName(s),() -> {}).create();

        alertDialog.show();
    }

    @Override
    public void showDialogStartPosition(String defaultStr) {
        AlertDialog alertDialog = DialogHelper.getDialogInteger(this,"Введи начальную позицию",defaultStr,
                s -> mPresenter.setStartPosition(s),() -> {
                }).create();

        alertDialog.show();
    }

    @Override
    public void showDialogFinishPosition(String defaultStr) {
        AlertDialog alertDialog = DialogHelper.getDialogInteger(this,"Введи конечную позицию",defaultStr,
                s -> mPresenter.setFinishPosition(s),() -> {}).create();

        alertDialog.show();
    }

    @Override
    public void showDialogCoefficient(String defaultStr) {
        AlertDialog alertDialog = DialogHelper.getDialogFloat(this,"Введи коэффицент",defaultStr,
                s -> mPresenter.setCoefficient(s),() -> {}).create();

        alertDialog.show();
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
    //**********************************************************************************************




}
