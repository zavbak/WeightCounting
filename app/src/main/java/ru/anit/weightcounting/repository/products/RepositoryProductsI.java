package ru.anit.weightcounting.repository.products;

import java.util.List;

import io.realm.RealmResults;
import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by Alex on 15.05.2017.
 */

public interface RepositoryProductsI {
    RealmResults<Product> getListProductsAll();
    Product save(Product product);
    Product getProductById(long id);
}
