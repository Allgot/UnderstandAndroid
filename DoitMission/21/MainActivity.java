package dev_allgot.understand.doitmission_21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SingletonBookDB sbdb;
    BookDB bookDB;
    EditText titleEdit;
    EditText authorEdit;
    EditText contentsEdit;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbdb = new SingletonBookDB(getApplicationContext());
        bookDB = sbdb.getInstance();

        titleEdit = findViewById(R.id.titleEdit);
        authorEdit = findViewById(R.id.authorEdit);
        contentsEdit = findViewById(R.id.contentsEdit);

        handler = new Handler();

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleEdit.getText().length() <= 0) Toast.makeText(getApplicationContext(), "Title is Empty!", Toast.LENGTH_LONG).show();
                else if(authorEdit.getText().length() <= 0) Toast.makeText(getApplicationContext(), "Author is Empty!", Toast.LENGTH_LONG).show();
                else if(contentsEdit.getText().length() <= 0) Toast.makeText(getApplicationContext(), "Contents is Empty!", Toast.LENGTH_LONG).show();
                else {
                    String title = titleEdit.getText().toString();
                    String author = authorEdit.getText().toString();
                    String contents = contentsEdit.getText().toString();

                    BookDao dao = bookDB.bookDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dao.insertBook(new Book(title, author, contents));
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).start();
                    titleEdit.setText("");
                    authorEdit.setText("");
                    contentsEdit.setText("");
                }
            }
        });
    }
}
