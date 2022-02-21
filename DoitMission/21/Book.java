package dev_allgot.understand.doitmission_21;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey
    public int bId;

    public String title;
    public String author;
    public String contents;
}
