package com.composetest.core.database.androidapi.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.composetest.core.database.androidapi.data.entity.ProductEntity

@Dao
interface ProductEntityDao {

    @Query("DELETE FROM product")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductEntity>
}
