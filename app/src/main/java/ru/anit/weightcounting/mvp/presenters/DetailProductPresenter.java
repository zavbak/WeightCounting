package ru.anit.weightcounting.mvp.presenters;

import android.content.IntentFilter;

import ru.anit.weightcounting.api.BarcodeDataBroadcastReceiver;
import ru.anit.weightcounting.mvp.model.entities.Product;
import ru.anit.weightcounting.mvp.views.DetailProductView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class DetailProductPresenter extends MvpPresenter<DetailProductView> {

    Product mProduct;



    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().refresh();
    }

    public void onStart(){
        IntentFilter intentFilter = new IntentFilter("DATA_SCAN");
        getViewState().registerBarcodeReceiver();
    }

    public void onStop(){
        getViewState().unregisterReceiver();
    }

    public DetailProductPresenter() {
        mProduct = new Product();
        mProduct.setName("Свинина Мираторг");
    }


    //**********************************************************************************************
    //  Dilog

    public void refreshView() {
        getViewState().refresh();
    }

    void showDilogName() {
        getViewState().showDialogName(mProduct.getName());
    }

    void showDilogStartPosition() {
        getViewState().showDialogStartPosition(null);
    }

    void showDilogFinishPosition() {
        getViewState().showDialogFinishPosition(null);
    }

    public void clickCoefficient() {
        getViewState().showDialogCoefficient(null);
    }


    //**********************************************************************************************
    //  Event

    public void clickName() {
        showDilogName();
    }

    public void clickStartPosition() {
        showDilogStartPosition();
    }

    public void clickFinishPosition() {
        showDilogFinishPosition();
    }


    public String getName() {
        return mProduct.getName();
    }

    public int getStart() {
        return mProduct.getStartPositionBarcodeWight();
    }


    public int getFinish() {
        return mProduct.getFinishPositionBarcodeWight();
    }

    public float getCoefficient() {
        return mProduct.getCoefficient();
    }

    public String getInitialBarcode() {
        return mProduct.getInitialBarcode();
    }


    public void setName(String name) {
        mProduct.setName(name);
        refreshView();
    }

    public void setStartPosition(String s) {
        int anInt = 0;
        try {
            anInt = Integer.parseInt(s) -1 ;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        mProduct.setStartPositionBarcodeWight(anInt);
        refreshView();

    }

    public void setFinishPosition(String s) {

        int anInt = 0;
        try {
            anInt = Integer.parseInt(s) -1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        mProduct.setFinishPositionBarcodeWight(anInt);
        refreshView();
    }


    public void setCoefficient(String s) {

        float v = 0;
        try {
            v = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mProduct.setCoefficient(v);
        refreshView();
    }

    public void setInitBarcode(String initBarcode) {
        mProduct.setInitialBarcode(initBarcode);
        refreshView();
    }
}
