package ru.anit.weightcounting.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.anit.weightcounting.mvp.model.entities.Product;
import ru.anit.weightcounting.mvp.views.DialogBarcodeView;

/**
 * Created by Alex on 15.05.2017.
 */
@InjectViewState
public class DialogBarcodePresenter extends MvpPresenter<DialogBarcodeView> {

    String id;
    String barcode;

    Boolean init;



    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    public void setIdandBarcode(String barcode,String id) {

        if(init == null){
            this.id = id;
            this.barcode = barcode;
        }

        init = true;
        getViewState().refresh();
    }

    public String getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
