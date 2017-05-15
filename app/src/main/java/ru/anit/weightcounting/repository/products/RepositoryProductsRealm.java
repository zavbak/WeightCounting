package ru.anit.weightcounting.repository.products;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.anit.weightcounting.api.realm.RealmTable;
import ru.anit.weightcounting.app.App;
import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by Alex on 15.05.2017.
 */

public class RepositoryProductsRealm implements RepositoryProductsI{

    Realm mRealm = Realm.getDefaultInstance();


    @Override
    public RealmResults<Product> getListProductsAll() {
        return mRealm.where(Product.class).findAll();
    }

    private void setId(Product product){
        if(product.getId() == 0){
            long id;

            try {
                id = mRealm.where(Product.class).max("id").intValue() + 1;
            } catch (Exception e) {
                id = 1;
            }

            product.setId(id);
        }
    }

    @Override
    public Product save(Product product) {
        try {

            mRealm.beginTransaction();
            setId(product);
            mRealm.copyToRealmOrUpdate(product);

            mRealm.commitTransaction();
            mRealm.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return product;
    }

    @Override
    public Product getProductById(long id) {

        try {
            return mRealm.where(Product.class).equalTo(RealmTable.ID, id).findFirst();
        } catch (Exception e) {
            return null;
        }

    }
}
