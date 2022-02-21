package dev_allgot.understand.doitmission_21;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface BookDAO {
    @Insert
    void insertBooks(ArrayList<Book> books);

    @Insert
    void insertBook(Book book);

    @Update
    void updateBooks(ArrayList<Book> books);

    @Update
    void updateBook(Book book);

    @Query("DELETE FROM books")
    void deleteAll();

    @Delete
    void delete(ArrayList<Book> books);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT * FROM books")
    Book[] selectAll();
}
