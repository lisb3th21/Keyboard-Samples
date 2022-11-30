package com.lisbeth.keyboardsamples;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editEmail;
    EditText editPhone;
    Spinner spinner;
    EditText editPhone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text_text_auto_correct);
        editEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editPhone = (EditText) findViewById(R.id.editTextPhone);
        editPhone2 = (EditText) findViewById(R.id.editTextPhone2);

        // if the key 'done'is pressed the toast is show and the keyboard is closed
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    showToastText(v);
                }
                return false;
            }
        });
        // if the key 'send'is pressed an intent is created
        editEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    sendEmail(v);
                }
                return false;
            }
        });

        // if the key 'send'is pressed an intent is created
        editPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    call(v);
                }
                return false;
            }
        });

        editPhone2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    MySpinner();
                }
                return false;
            }
        });
        this.MySpinner();
    }

    /**
     * Listen the button of the first edit text and show the toast of the text input
     * 
     * @param view
     */
    public void showToastText(View view) {
        if (editText.getText().length() > 0) {
            Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
            // Close the keyboard
            InputMethodManager imm = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            editText.setText("");
        }
    }

    /**
     * Listen the button and make and intent to send an email
     * 
     * @param view
     */
    public void sendEmail(View view) {
        if (editEmail.getText().length() > 0) {
            Toast.makeText(this, editEmail.getText(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + editEmail.getText()));
            startActivity(Intent.createChooser(intent, "Chooser Title"));
        }
    }

    /**
     * Listen the button and make and intent to call
     * 
     * @param view
     */
    public void call(View view) {
        if (editPhone.getText().length() > 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + editPhone.getText()));
            startActivity(intent);
        }
    }

    /**
     * It creates a spinner and an editText. When the user selects an item from the
     * spinner, it will
     * display the item selected and the text in the editText.
     */
    public void MySpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        editPhone2 = (EditText) findViewById(R.id.editTextPhone2);
        TextView textView = (TextView) findViewById(R.id.textView_RESULT);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                    View selectedItemView, int position, long id) {
                String res = parentView.getItemAtPosition(position).toString();
                if (editPhone2.getText().length() > 0) {
                    Toast.makeText(MainActivity.this, res + ": " + editPhone2.getText(), Toast.LENGTH_SHORT).show();
                    textView.setText(res + ": " + editPhone2.getText());
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}