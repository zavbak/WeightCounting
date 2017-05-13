package ru.anit.weightcounting.ui.activityes.datail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.anit.weightcounting.common.ActivitieysHelper;
import ru.anit.weightcounting.mvp.views.DetailProductView;
import ru.anit.weightcounting.mvp.presenters.DetailProductPresenter;


import ru.anit.weightcounting.R;
import ru.anit.weightcounting.ui.activityes.BaseMvpActivity;
import ru.anit.weightcounting.ui.dialog.DialogHelper;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class DetailProductActivity extends BaseMvpActivity implements DetailProductView {

    @InjectPresenter
    DetailProductPresenter mPresenter;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_start)
    TextView tvStart;

    @BindView(R.id.tv_finish)
    TextView tvFinish;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, DetailProductActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
    }

    @Override
    public void refresh() {
        tvName.setText(mPresenter.getName());
        tvStart.setText(mPresenter.getStart());
        tvFinish.setText(mPresenter.getFinish());
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
        mPresenter.finishPosition();
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
    //**********************************************************************************************




}
