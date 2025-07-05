package com.example.bsn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContributorActivity extends AppCompatActivity {
    private static final int SMS_PERMISSION_CODE = 1;
    EditText edtName, edtPhone, edtProduct;
    Button btnSendContribution;
    String phoneNumber = "9095531883"; // Replace with actual number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);

        edtName = findViewById(R.id.edName);
        edtPhone = findViewById(R.id.edPhone);
        edtProduct = findViewById(R.id.edProduct);
        btnSendContribution = findViewById(R.id.btnContribution);

        btnSendContribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ContributorActivity.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContributorActivity.this,
                            new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                } else {
                    sendSMS();
                }
            }
        });
    }

    private void sendSMS() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String product = edtProduct.getText().toString().trim();

        if (!name.isEmpty() && !phone.isEmpty() && !product.isEmpty()) {
            String message = "Contributor Details:\n" +
                    "Name: " + name + "\n" +
                    "Phone: " + phone + "\n" +
                    "Product to Contribute: " + product + "\n" +
                    "Wished to have contribution towards our shop.";

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "Contribution details sent successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSMS();
            } else {
                Toast.makeText(this, "SMS permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
