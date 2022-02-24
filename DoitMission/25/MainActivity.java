package dev_allgot.understand.doitmission_25;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    RecyclerView picturesRecycler;
    TextView nopText;
    PictureAdapter adapter;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent data;
                        if((data = result.getData()) != null) {
                            ClipData clipData;
                            if((clipData = data.getClipData()) != null) {
                                for(int i =0; i<clipData.getItemCount(); i++) {
                                    try {
                                        String date = "";
                                        Bitmap picture = null;
                                        InputStream inStream = getContentResolver().openInputStream(clipData.getItemAt(i).getUri());

                                        ExifInterface exif = new ExifInterface(inStream);
                                        if(exif.getThumbnail() != null) picture = BitmapFactory.decodeByteArray(exif.getThumbnail(), 0, exif.getThumbnail().length);
                                        if(exif.getAttribute(ExifInterface.TAG_DATETIME) != null) date = exif.getAttribute(ExifInterface.TAG_DATETIME);
                                        adapter.addPicture(new Picture(i, picture, date));
                                    } catch (Exception e) { e.printStackTrace(); }
                                }

                                adapter.notifyItemRangeChanged(0, clipData.getItemCount());

                                String msg = clipData.getItemCount() + " 개";
                                nopText.setText(msg);
                            }
                            else {
                                try {
                                    String date = "";
                                    Bitmap picture = null;
                                    InputStream inStream = getContentResolver().openInputStream(data.getData());

                                    ExifInterface exif = new ExifInterface(inStream);
                                    if(exif.getThumbnail() != null) picture = BitmapFactory.decodeByteArray(exif.getThumbnail(), 0, exif.getThumbnail().length);
                                    if(exif.getAttribute(ExifInterface.TAG_DATETIME) != null) date = exif.getAttribute(ExifInterface.TAG_DATETIME);
                                    adapter.addPicture(new Picture(0, picture, date));
                                } catch (Exception e) { e.printStackTrace(); }

                                adapter.notifyItemChanged(0);

                                nopText.setText("1 개");
                            }
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picturesRecycler = findViewById(R.id.picturesRecycler);
        nopText = findViewById(R.id.nopText);

        adapter = new PictureAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        picturesRecycler.setAdapter(adapter);
        picturesRecycler.setLayoutManager(layoutManager);

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setType("image/*");

        launcher.launch(galleryIntent);
    }
}
