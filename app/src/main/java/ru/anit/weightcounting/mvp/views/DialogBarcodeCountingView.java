package ru.anit.weightcounting.mvp.views;

/**
 * Created by Alex on 16.05.2017.
 */

public interface DialogBarcodeCountingView {
    void setContext();
    void setCallBack(String seats,String weight);
    void setTitle(CharSequence title);
    void setMessage(CharSequence message);
    void show();
    void dismiss();
}
