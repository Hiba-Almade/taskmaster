package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Query("select * from tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tasks WHERE id LIKE :id")
    Task findTaskById(String id);

    @Delete
    int delete(Task task);

}
