package ru.anit.weightcounting.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Alex on 15.05.2017.
 */

public interface ListBarcodeView extends MvpView {


    void showDialogBarcode(CharSequence title, CharSequence message,String sites,String weight);

    void dissmisDialogBarcode();

    @StateStrategyType(SkipStrategy.class)
    void registerBarcodeReceiver();
    @StateStrategyType(SkipStrategy.class)
    void unregisterReceiver();
}
