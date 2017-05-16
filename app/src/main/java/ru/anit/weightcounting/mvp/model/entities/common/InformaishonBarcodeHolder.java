package ru.anit.weightcounting.mvp.model.entities.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Alex on 16.05.2017.
 */

public class InformaishonBarcodeHolder {

    String mBarcode;
    int mStart;
    int mFinish;
    float mCoefficient;

    String mWeight;
    String messError;
    int simbolsWeight;



    public InformaishonBarcodeHolder(String barcode, int start, int finish, float coefficient) {
        mBarcode = barcode;
        mStart = start;
        mFinish = finish;
        mCoefficient = coefficient;
        init();

    }

    void init(){

        if(mBarcode == null){
            messError = "Штрих код не задан";
            return;
        }

        if(mStart > mFinish){
            messError = "Штрих код не задан";
            return;
        }

        if(mFinish > mBarcode.length()){
            messError = "Не верно задана конечная позиция";
            return;
        }

        if(mStart == mFinish){
            messError = "Начальной позиция = Конечной позиции";
            return;
        }

        try {

            simbolsWeight = Integer.parseInt(mBarcode.substring(mStart, mFinish));
            BigDecimal res = new BigDecimal(Float.toString(mCoefficient)).multiply(new BigDecimal(simbolsWeight));
            mWeight = res.toString();

        } catch (NumberFormatException e) {
            e.printStackTrace();
            messError = "Ошибка получения веса из строки";
            return;
        }

    }


    public String getWeight() {
        return mWeight;
    }

    public String getMessError() {
        return messError;
    }

    public int getSimbolsWeight() {
        return simbolsWeight;
    }
}
