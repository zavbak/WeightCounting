package ru.anit.weightcounting.api.realm;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.anit.weightcounting.repository.db.SaveProduct;
import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by user on 13.05.2017.
 */

public class DBservise implements SaveProduct {





    @Override
    public Product save(Product product) {

        Product productRealModel = null;

//        RealmConfiguration mConfig = new RealmConfiguration.Builder()
//                .schemaVersion(1)
//                .migration(new RealmMigration())
//                .build();

        try {
            //Realm realm = Realm.getInstance(mConfig);
            Realm realm = Realm.getDefaultInstance();

            long id;

            try {
                id = realm.where(Product.class).max("id").intValue() + 1;
            } catch (Exception e) {
                id = 0L;
            }

            realm.beginTransaction();

            productRealModel = realm.createObject(Product.class,id);

            //productRealModel.setId(id);
            productRealModel.setName(product.getName());
            productRealModel.setInitialBarcode(product.getInitialBarcode());
            productRealModel.setStartPositionBarcodeWight(product.getStartPositionBarcodeWight());
            productRealModel.setFinishPositionBarcodeWight(product.getFinishPositionBarcodeWight());
            productRealModel.setCoefficient(product.getCoefficient());

            realm.commitTransaction();
            realm.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return productRealModel;
    }
}
