package com.composetest.feature.product.data.mapper

import com.composetest.core.database.data.entity.product.ProductEntity
import com.composetest.feature.product.domain.model.ProductModel
import javax.inject.Inject

internal class ProductEntityMapper @Inject constructor() {

    fun mapperToModels(entities: List<ProductEntity>) = entities.map { mapperToModel(it) }

    fun mapperToEntities(models: List<ProductModel>) = models.map { mapperToEntity(it) }

    fun mapperToModel(entity: ProductEntity) = ProductModel(
        id = entity.id,
        title = entity.title,
        description = entity.description,
        price = entity.price,
        discountPercentage = entity.discountPercentage,
        rating = entity.rating,
        stock = entity.stock,
        thumbnail = entity.thumbnail,
    )

    fun mapperToEntity(model: ProductModel) = ProductEntity(
        id = model.id,
        title = model.title,
        description = model.description,
        price = model.price,
        discountPercentage = model.discountPercentage,
        rating = model.rating,
        stock = model.stock,
        thumbnail = model.thumbnail,
    )
}