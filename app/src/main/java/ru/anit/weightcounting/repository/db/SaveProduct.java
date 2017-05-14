package ru.anit.weightcounting.repository.db;

import ru.anit.weightcounting.mvp.model.entities.Product;

/**
 * Created by user on 13.05.2017.
 */

public interface SaveProduct {
    Product save(Product product);
}
