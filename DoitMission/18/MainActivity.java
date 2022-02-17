package dev_allgot.understand.doitmission_18;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Uri> ps = new ArrayList<Uri>();
    int cursor = 1;

    TextView piText;
    ImageView pImage1;
    TextView pDateText1;
    ImageView pImage2;
    TextView pDateText2;

    Handler handler;

    ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        piText = findViewById(R.id.piText);
        pImage1 = findViewById(R.id.pImage1);
        pImage2 = findViewById(R.id.pImage2);
        pDateText1 = findViewById(R.id.pDateText1);
        pDateText2 = findViewById(R.id.pDateText2);

        handler = new Handler();

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setType("image/*");

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK) {
                    Intent data;
                    if((data = result.getData()) != null) {
                        ClipData clipData;
                        if((clipData = data.getClipData()) != null) {
                            for(int i=0; i< clipData.getItemCount(); i++) {
                                ps.add(clipData.getItemAt(i).getUri());
                            }
                        }
                        else {
                            ps.add(data.getData());
                        }

                        PictureThread pThread = new PictureThread();
                        pThread.start();
                    }
                }
            }
        });
        galleryLauncher.launch(galleryIntent);

    }

    public class PictureThread extends Thread {
        @Override
        public void run() {
            Runnable pic = new Runnable() {
                @Override
                public void run() {
                    ContentResolver resolver = getContentResolver();

                    if(cursor > ps.size()) cursor = 1;

                    String pi = cursor + "/" + ps.size();
                    piText.setText(pi);

                    try {
                        InputStream inStream = resolver.openInputStream(ps.get(cursor - 1));
                        Bitmap p1 = BitmapFactory.decodeStream(inStream);
                        String dateTime1 = "Created date is unknown";

                        ExifInterface exif = new ExifInterface(inStream);
                        if(exif.getThumbnail() != null) p1 = BitmapFactory.decodeByteArray(exif.getThumbnail(), 0, exif.getThumbnail().length);
                        pImage1.setImageBitmap(p1);

                        if(exif.getAttribute(ExifInterface.TAG_DATETIME) != null) dateTime1= exif.getAttribute(ExifInterface.TAG_DATETIME);
                        pDateText1.setText(dateTime1);
                    } catch(Exception e) { e.printStackTrace(); }

                    cursor++;

                    if(cursor > ps.size()) {
                        handler.postDelayed(this, 5000);
                        return;
                    }

                    try {
                        InputStream inStream = resolver.openInputStream(ps.get(cursor - 1));
                        Bitmap p2 = BitmapFactory.decodeStream(inStream);
                        String dateTime2 = "Created date is unknown";

                        ExifInterface exif = new ExifInterface(inStream);
                        if(exif.getThumbnail() != null) p2 = BitmapFactory.decodeByteArray(exif.getThumbnail(), 0, exif.getThumbnail().length);
                        pImage2.setImageBitmap(p2);

                        if(exif.getAttribute(ExifInterface.TAG_DATETIME) != null) dateTime2= exif.getAttribute(ExifInterface.TAG_DATETIME);
                        pDateText2.setText(dateTime2);
                    } catch (Exception e) { e.printStackTrace(); }

                    cursor++;
                    handler.postDelayed(this, 5000);
                }
            };

            handler.post(pic);
        }
    }
}
