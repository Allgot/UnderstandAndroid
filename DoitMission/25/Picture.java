package dev_allgot.understand.doitmission_25;

import android.graphics.Bitmap;
import android.net.Uri;

public class Picture {
    public int num;
    public Bitmap picture;
    public String date;

    public Picture(int num, Bitmap picture, String date) {
        this.num = num;
        this.picture = picture;
        this.date = date;
    }
}
