# Keyboard-Samples - Task 1, 2 and 3

## App overview 

---
 
<img src="result.gif" alt="App result" height="600">



<details open>
<summary ><h2>Suggests spelling corrections and capitalizes each new sentence</h2></summary>

To capitalize each sentence we use the following attribute: `android:inputType="textCapSentences"`

```xml
<EditText
	android:id="@+id/edit_text_text_auto_correct"
	android:layout_width="235dp"
	android:layout_height="45dp"
	android:layout_marginTop="16dp"
	android:ems="10"
	android:hint="@string/capaitalizes_each_sentence"
	android:imeOptions="actionDone"
	android:inputType="textCapSentences" />
```

This is the method for show the `toast` and close the keyboard if the text is not null. 

```java
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
```

In `onCreate`  I declare that if the `Done` key on the keyboard is pressed, the Toast will also be displayed and the keyboard will be closed.

```java
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
```

</details>

---

<details open>
<summary><h2>Keyboard that offers the key "@"</h2></summary>

For this we use the attribute `android:inputType="textEmailAddress"`: 

```xml
 <EditText
        android:id="@+id/editTextTextEmailAddress"
        android:layout_width="234dp"
        android:layout_height="59dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="12dp"
        android:hint="@string/email"
        android:imeOptions="actionGo"
        android:inputType="textEmailAddress" />
```

This method make a toast to the input email and create a intento to another app.

```java
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
```

</details >

---

<details open >
<summary ><h2>Keyboard that offers the numeric keyboard</h2></summary>

For this we use the attribute `android:inputType="phone"`:

```xml
<EditText
	android:id="@+id/editTextPhone"
	android:layout_width="232dp"
	android:layout_height="50dp"
	android:layout_marginStart="16dp"
	android:layout_marginTop="16dp"
	android:ems="10"
	android:hint="Phone"
	android:imeOptions="actionGo"
	android:inputType="phone"/>

	
```

This is the java code: 

```java
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
```
</details >

---

<details open >
<summary><h2>Numeric keyboard with spinner</h2> </summary>

I declare a spiner and a TextView. 

```xml
<Spinner
	android:id="@+id/spinner"
	android:layout_width="99dp"
	android:layout_height="38dp"
	android:layout_marginTop="60dp"
	android:layout_marginEnd="16dp"
	android:background="@color/purple_500"
	android:backgroundTint="@color/purple_500"
	android:entries="@array/options"
	android:foregroundTint="@color/purple_500" />

<TextView
	android:id="@+id/textView_RESULT"
	android:layout_width="wrap_content"
	android:layout_height="25dp"
	android:layout_marginTop="144dp"/>
```

I add a listener in the spinner and set the text of the TextView: 

```java
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
```

</details >