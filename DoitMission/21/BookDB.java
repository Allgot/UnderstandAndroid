package dev_allgot.understand.doitmission_21;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDB extends RoomDatabase {
    public abstract BookDao bookDao();
}
