package com.github.jokar.zhihudaily.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.github.jokar.zhihudaily.model.entities.story.StoryEntity

/**
 * 【坑3】, 方法返回类型不能用ArrayList 他会识别不了，用List就好了
 * 【坑4】: insert 的返回值 不能是 Long _impl 生成的是 long 和 kotlin 的Long 类型对不上， 可以用 List 解决这种尴尬；
 * fun insertEntity(vararg entity: UserDBEntity): List
 *  insert 的返回值 不能是 Long _impl 生成的是 long 和 kotlin 的Long 类型对不上， 可以用 List 解决
 */
@Dao
interface StoryDao {

    /**
     * 插入story
     */
    @Insert
    fun insert(stories: ArrayList<StoryEntity>)

    /**
     * 插入
     */
    @Insert
    fun insert(story: StoryEntity)
    /**
     * 根据时间获取story
     */
    @Query("SELECT * FROM story WHERE date = :date ")
    fun getStoryByDate(date: Long): List<StoryEntity>

    /**
     * 获取所有收藏的story
     */
    @Query("SELECT * FROM story WHERE collection = 1 order by date desc")
    fun getAllCollectedStory(): List<StoryEntity>

    /**
     * 更新story
     */
    @Update
    fun updateStory(story: StoryEntity)

    /**
     * 根据id获取story
     */
    @Query("SELECT * FROM story where id = :id ")
    fun selectStory(id: Int): StoryEntity
}