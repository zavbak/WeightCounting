package ru.anit.weightcounting.mvp.model.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class Product extends RealmObject  {


    @PrimaryKey
    private long id;
    private String name;
    private int startPositionBarcodeWight;
    private int finishPositionBarcodeWight;
    private float coefficient;
    private String barcode;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartPositionBarcodeWight() {
        return startPositionBarcodeWight;
    }

    public void setStartPositionBarcodeWight(int startPositionBarcodeWight) {
        this.startPositionBarcodeWight = startPositionBarcodeWight;
    }

    public int getFinishPositionBarcodeWight() {
        return finishPositionBarcodeWight;
    }

    public void setFinishPositionBarcodeWight(int finishPositionBarcodeWight) {
        this.finishPositionBarcodeWight = finishPositionBarcodeWight;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
