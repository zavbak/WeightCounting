package ru.anit.weightcounting.api.barcode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ru.anit.weightcounting.repository.barcode.ReciveBarcode;


public class BarcodeDataBroadcastReceiver extends BroadcastReceiver {

    ReciveBarcode mResiveBarcode;

    public BarcodeDataBroadcastReceiver(ReciveBarcode resiveBarcode) {
        mResiveBarcode = resiveBarcode;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String barcode = intent.getStringExtra("com.hht.emdk.datawedge.data_string");
        mResiveBarcode.callBack(barcode);
    }


}