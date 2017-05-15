package ru.anit.weightcounting.api.realm;

public interface RealmTable {

    String ID = "id";

    interface Product {
        String NAME        = "name";
        String STRAT       = "startPositionBarcodeWight";
        String FINISH      = "finishPositionBarcodeWight";
        String COEFFICIENT = "coefficient";
        String BARCODE     = "initialBarcode";
    }

    interface Student{
        String NAME = "name";
        String AGE = "age";
        String EMAIL = "email";
        String BOOKS = "books";
        String LESSONS = "lessons";
    }
}
