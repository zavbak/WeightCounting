package ru.anit.weightcounting.mvp.model.entities;

/**
 * Created by user on 12.05.2017.
 */

public class Product {
    long   id;
    String name;
    String startPositionBarcodeWight;
    String finishPositionBarcodeWight;
    String coefficient;

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

    public String getStartPositionBarcodeWight() {
        return startPositionBarcodeWight;
    }

    public void setStartPositionBarcodeWight(String startPositionBarcodeWight) {
        this.startPositionBarcodeWight = startPositionBarcodeWight;
    }

    public String getFinishPositionBarcodeWight() {
        return finishPositionBarcodeWight;
    }

    public void setFinishPositionBarcodeWight(String finishPositionBarcodeWight) {
        this.finishPositionBarcodeWight = finishPositionBarcodeWight;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }
}
