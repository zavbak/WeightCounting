package ru.anit.weightcounting.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.views.DialogBarcodeCountingView;

/**
 * Created by Alex on 16.05.2017.
 */

public class DialogBarcodeCounting implements DialogBarcodeCountingView {

    Context mContext;
    AlertDialog.Builder mBuilder;
    CharSequence mTitle;
    CharSequence mMessage;

    AlertDialog mAlertDialog;




    public DialogBarcodeCounting(Context context) {
        mContext = context;

    }

    void create(){
        LayoutInflater li = LayoutInflater.from(mContext);
        View view = li.inflate(R.layout.dialog_barcode, null);


        mBuilder = new AlertDialog.Builder(mContext);

        mBuilder.setView(view);


        mBuilder
                .setTitle(mTitle)
                .setMessage(mMessage)
                .setPositiveButton("OK", (dialog, which) -> {})
                .setNegativeButton("Cancel",(dialog, which) -> {})
                .setCancelable(false);
    }

    @Override
    public void setContext() {

    }

    @Override
    public void setCallBack(String seats, String weight) {

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessage = message;
    }


    @Override
    public void show() {
        create();
        mAlertDialog = mBuilder.create();
        mAlertDialog.show();
    }

    @Override
    public void dismiss() {
        if(mAlertDialog != null){
            mAlertDialog.dismiss();
        }
    }
}
