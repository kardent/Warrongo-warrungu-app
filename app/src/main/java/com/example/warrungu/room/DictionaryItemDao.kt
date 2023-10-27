package com.example.warrungu.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface DictionaryItemDao {
    @Upsert
    suspend fun upsertDictionaryItem(dictionaryItem: DictionaryItem)

    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' ORDER BY language ASC")
    fun getDictionaryItems(): List<DictionaryItem>

    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' AND (english like :search OR language like :search) ORDER BY language ASC")
    fun getDictionaryItemsSearch(search: String): List<DictionaryItem>

    @Query("SELECT * FROM DictionaryItem WHERE id = :idString")
    fun getDictionaryItemById(idString: String): List<DictionaryItem>

    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' AND type = :type ORDER BY language ASC")
    fun getDictionaryItemsOfType(type: String): List<DictionaryItem>
    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' AND type = :type AND (english like :search OR language like :search) ORDER BY language ASC")
    fun getDictionaryItemsOfTypeSearch(type: String, search: String): List<DictionaryItem>

    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' AND image != '' ORDER BY RANDOM() ASC LIMIT 4")
    fun getGameItems(): List<DictionaryItem>
    @Query("SELECT * FROM DictionaryItem WHERE english != '' AND language != '' AND image != '' ORDER BY RANDOM() ASC LIMIT 10")
    fun getGameCharades(): List<DictionaryItem>


}