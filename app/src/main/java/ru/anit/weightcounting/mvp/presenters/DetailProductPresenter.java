package ru.anit.weightcounting.mvp.presenters;

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

    public DetailProductPresenter() {
        mProduct = new Product();
        mProduct.setName("Свинина Мираторг");
    }


    //**********************************************************************************************
    //  Dilog

    public void refreshView(){
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


    //**********************************************************************************************
    //  Event

    public void clickName() {
        showDilogName();
    }

    public void clickStartPosition() {
        showDilogStartPosition();
    }

    public void finishPosition() {
        showDilogFinishPosition();
    }


    public String getName() {

        String title = "Наименование: ";
        return title + mProduct.getName();
    }

    public String getStart() {

        String title = "Начальная позиция: ";
        return title + mProduct.getStartPositionBarcodeWight();
    }


    public String getFinish() {
        String title = "Конечная позиция: ";
        return title + mProduct.getFinishPositionBarcodeWight();
    }

    public void setName(String name) {
        mProduct.setName(name);
        refreshView();
    }

    public void setStartPosition(String s) {
        mProduct.setStartPositionBarcodeWight(s);
        refreshView();

    }

    public void setFinishPosition(String s) {
        mProduct.setFinishPositionBarcodeWight(s);
        refreshView();
    }



}
