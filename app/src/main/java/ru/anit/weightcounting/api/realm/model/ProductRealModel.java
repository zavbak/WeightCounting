package ru.anit.weightcounting.api.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by user on 13.05.2017.
 */

public class ProductRealModel extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private int startPositionBarcodeWight;
    private int finishPositionBarcodeWight;
    private float coefficient;
    private String initialBarcode;

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

    public String getInitialBarcode() {
        return initialBarcode;
    }

    public void setInitialBarcode(String initialBarcode) {
        this.initialBarcode = initialBarcode;
    }
}
