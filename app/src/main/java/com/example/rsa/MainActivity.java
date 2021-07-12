package com.example.rsa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etInput, etOutput;
    private String publicKey = "";
    private String privateKey = "";
    private byte[] encodeData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.etInput);
        etOutput = findViewById(R.id.etOutput);

        try {
            Map<String, Object> keyMap = RSA1.initKey();
            publicKey = RSA1.getPublicKey(keyMap);
            privateKey = RSA1.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encrypt(View v){
        byte[] rsaData = etInput.getText().toString().getBytes();

        try {
            encodeData = RSA1.encryptByPublicKey(rsaData, getPublicKey());
            String encodeStr = new BigInteger(1, encodeData).toString();
            etOutput.setText(encodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void decrypt(View v){
        try {
            byte[] decodeData = RSA1.encryptByPrivateKey(encodeData, getPrivateKey());
            String decodeStr = new String(decodeData);
            etOutput.setText(decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}