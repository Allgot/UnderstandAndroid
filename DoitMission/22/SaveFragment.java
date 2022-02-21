package dev_allgot.understand.doitmission_22;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveFragment extends Fragment {
    private SBDBViewModel viewModel;

    SingletonBookDB sbdb;
    BookDB bookDB;

    EditText titleEdit;
    EditText authorEdit;
    EditText contentsEdit;

    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        handler = new Handler();

        titleEdit = view.findViewById(R.id.titleEdit);
        authorEdit = view.findViewById(R.id.authorEdit);
        contentsEdit = view.findViewById(R.id.contentsEdit);

        viewModel = new ViewModelProvider(requireActivity()).get(SBDBViewModel.class);
        sbdb = viewModel.getMSBDB().getValue();
        if(sbdb != null) bookDB = sbdb.getInstance();
        else Toast.makeText(getContext(), "Error!", Toast.LENGTH_LONG).show();


        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titleEdit.getText().length() <= 0) Toast.makeText(getContext(), "Title is Empty!", Toast.LENGTH_LONG).show();
                else if(authorEdit.getText().length() <= 0) Toast.makeText(getContext(), "Author is Empty!", Toast.LENGTH_LONG).show();
                else if(contentsEdit.getText().length() <= 0) Toast.makeText(getContext(), "Contents is Empty!", Toast.LENGTH_LONG).show();
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
                                    Toast.makeText(getContext(), "Successfully Saved!", Toast.LENGTH_LONG).show();
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

        return view;
    }
}
