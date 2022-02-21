package dev_allgot.understand.doitmission_21;

import android.content.Context;

import androidx.room.Room;

public class SingletonBookDB {
    private static BookDB bdb;
    private static Context ctx;

    public SingletonBookDB(Context context) {
        ctx = context;
        bdb = getInstance();
    }

    public BookDB getInstance() {
        if(bdb == null) bdb = Room.databaseBuilder(ctx.getApplicationContext(), BookDB.class, "Book Database").build();
        return bdb;
    }
}
