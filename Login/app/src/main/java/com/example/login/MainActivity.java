package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.login.MESSAGE";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        String test = loginFromJNI("a", "p");
        tv.setText(test);
    }

    /**
     * Called when the user taps the Submit button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText username = findViewById(R.id.editTextTextPersonName2);
        EditText password = findViewById(R.id.editTextTextPassword2);
        String message = loginFromJNI(username.getText().toString(), password.getText().toString());
        if (!message.equals("")) {
            intent.putExtra(EXTRA_MESSAGE, message);
        }
        startActivity(intent);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String loginFromJNI(String user, String pass);
}