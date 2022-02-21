package dev_allgot.understand.doitmission_21;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    public Book(String title, String author, String contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }
    
    @PrimaryKey(autoGenerate = true)
    public int bId;

    public String title;
    public String author;
    public String contents;
}
