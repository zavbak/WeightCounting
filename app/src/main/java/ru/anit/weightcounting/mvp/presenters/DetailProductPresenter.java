package ru.anit.weightcounting.mvp.presenters;

import android.content.IntentFilter;

import ru.anit.weightcounting.repository.products.RepositoryProductsI;
import ru.anit.weightcounting.repository.products.RepositoryProductsRealm;
import ru.anit.weightcounting.mvp.model.entities.Product;
import ru.anit.weightcounting.mvp.views.DetailProductView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class DetailProductPresenter extends MvpPresenter<DetailProductView> {

    Product mProduct;

    RepositoryProductsI repositoryProducts;


    public DetailProductPresenter() {
        repositoryProducts = new RepositoryProductsRealm();
    }


    public void setProduct(String id) {

        if(mProduct == null){
            mProduct = new Product();

            if (id != null) {
                Product productRealm = repositoryProducts.getProductById(Long.parseLong(id));

                mProduct.setId(productRealm.getId());
                mProduct.setBarcode(productRealm.getBarcode());
                mProduct.setName(productRealm.getName());
                mProduct.setStartPositionBarcodeWight(productRealm.getStartPositionBarcodeWight());
                mProduct.setFinishPositionBarcodeWight(productRealm.getFinishPositionBarcodeWight());
                mProduct.setCoefficient(productRealm.getCoefficient());
            }
        }
    }

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

    public void saveProduct(){
        repositoryProducts.save(mProduct);
        getViewState().exit();
    }

    public void refreshView() {
        getViewState().refresh();
    }

    //**********************************************************************************************
    //  Dilog

    void showDilogName() {
        getViewState().showDialogName(mProduct.getName());
    }

    void showDilogStartPosition() {
        getViewState().showDialogStartPosition(null);
    }

    void showDilogFinishPosition() {
        getViewState().showDialogFinishPosition(null);
    }

    void showDilogBarCode() {
        getViewState().showDialogBarcode(null);
    }

    //**********************************************************************************************
    //  Event

    public void clickName() {
        showDilogName();
    }
    public void clickBarcode() {
        showDilogBarCode();
    }

    public void clickStartPosition() {
        showDilogStartPosition();
    }

    public void clickFinishPosition() {
        showDilogFinishPosition();
    }

    public void clickCoefficient() {
        getViewState().showDialogCoefficient(null);
    }


    //**********************************************************************************************
    //      get param Product

    public String getName() {
        return mProduct.getName();
    }

    public int getStartPosition() {
        return mProduct.getStartPositionBarcodeWight();
    }

    public int getFinishPosition() {
        return mProduct.getFinishPositionBarcodeWight();
    }

    public float getCoefficient() {
        return mProduct.getCoefficient();
    }

    public String getInitialBarcode() {
        return mProduct.getBarcode();
    }
    public String getId() {
        return Long.toString(mProduct.getId());
    }
    //**********************************************************************************************
    //      set param Product

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
        mProduct.setBarcode(initBarcode);
        refreshView();
    }


}
