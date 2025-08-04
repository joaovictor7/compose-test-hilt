package com.composetest.feature.product.data.datasource

import com.composetest.core.database.androidapi.dao.ProductEntityDao
import com.composetest.core.database.androidapi.data.entity.ProductEntity
import javax.inject.Inject

internal class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductEntityDao,
) {

    suspend fun clearAll() {
        productDao.clearAll()
    }

    suspend fun insertAll(products: List<ProductEntity>) {
        productDao.insertAll(products)
    }

    suspend fun getAll() = productDao.getAll()
}