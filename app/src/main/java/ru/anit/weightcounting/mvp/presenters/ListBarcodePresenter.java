package ru.anit.weightcounting.mvp.presenters;

import android.content.IntentFilter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.anit.weightcounting.mvp.model.entities.Product;
import ru.anit.weightcounting.mvp.model.entities.common.HelperGetWeight;
import ru.anit.weightcounting.mvp.model.entities.common.InformaishonBarcodeHolder;
import ru.anit.weightcounting.mvp.views.ListBarcodeView;

/**
 * Created by Alex on 15.05.2017.
 */
@InjectViewState
public class ListBarcodePresenter extends MvpPresenter<ListBarcodeView> {


    String mBarcode;



    public void showBarcodeDialog() {



        Product product = new Product();
        product.setName("Говядина на кости");
        product.setId(12);
        product.setStartPosition(1);
        product.setFinishPosition(5);

        product.setCoefficient((float)0.01);

        InformaishonBarcodeHolder infBarcode

                = new InformaishonBarcodeHolder(mBarcode,
                product.getStartPosition(),
                product.getFinishPosition(),
                product.getCoefficient()
        );


        if(infBarcode.getWeight() == null){
            getViewState().showDialogBarcode("Подтвердите ввод!",infBarcode.getMessError(),"1",infBarcode.getWeight());
        }else{
            String str = String.format(
                    " ШК: %s \n\n (%s-%s) %s К = %s \n\n Вес: %s \n\n %s (%s)",
                    mBarcode,
                    product.getStartPosition(),
                    product.getFinishPosition(),
                    infBarcode.getSimbolsWeight(),
                    product.getCoefficient(),
                    infBarcode.getWeight(),
                    product.getName(),
                    product.getId());

            getViewState().showDialogBarcode("Подтвердите ввод!",str,"1",infBarcode.getWeight());
        }

    }


    public void onStart(){
        IntentFilter intentFilter = new IntentFilter("DATA_SCAN");
        getViewState().registerBarcodeReceiver();
    }

    public void onStop(){
        getViewState().unregisterReceiver();
    }

    public void setBarcode(String barcode) {
        mBarcode = barcode;
        showBarcodeDialog();
    }
}
