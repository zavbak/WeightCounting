package ru.anit.weightcounting.repository.db;

import ru.anit.weightcounting.api.realm.DBservise;
import ru.anit.weightcounting.mvp.model.entities.Product;



public class SaveProdactRealm implements SaveProduct {

    @Override
    public Product save(Product product) {

        SaveProduct dBservise = new DBservise();

        return dBservise.save(product);
    }
}
