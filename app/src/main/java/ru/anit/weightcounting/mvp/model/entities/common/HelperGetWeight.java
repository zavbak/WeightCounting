package ru.anit.weightcounting.mvp.model.entities.common;

import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by Alex on 16.05.2017.
 */

public class HelperGetWeight {
    public static String getWeightByBarcode(Product product, String barcode,String simbols, String messError) {


        int start = product.getStartPosition();
        int finish = product.getFinishPosition();
        float coef = product.getCoefficient();


        if (barcode != null) {

            if (start > finish) {
                messError = "Ошибка: Начальной позиции";
                return null;
            } else if (finish > barcode.length()) {
                messError = "Ошибка: Конечной позиции";
            } else if (start == finish) {
                messError = "Ошибка: Начальной позиция = Конечной позиции";
                return null;
            } else {


                int simbolsWeight = 0;
                try {

                    simbolsWeight = Integer.parseInt(barcode.substring(start, finish));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }

                if (simbolsWeight == 0) {
                    messError = "Ошибка получения веса из строки";
                    return null;
                } else {

                    simbols = barcode.substring(start, finish);
                    return Float.toString(simbolsWeight * coef);


                }

            }
        }

        return null;
    }

}
