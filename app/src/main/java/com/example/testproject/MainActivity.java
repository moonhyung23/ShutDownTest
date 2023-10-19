package com.example.testproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DevicePolicyManager devicePolicyManager;
    Button button;
    private int DEVICE_ADMIN_ADD_RESULT_ENABLE = -1;
    private ComponentName componentName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnTest);
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminActive();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                adminActive();
                devicePolicyManager.reboot(componentName);
            }
        });
    }

    private void adminActive() {
        componentName = new ComponentName(this, AppDeviceAdminReceiver.class);
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        startActivityForResult(intent, DEVICE_ADMIN_ADD_RESULT_ENABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == DEVICE_ADMIN_ADD_RESULT_ENABLE) {
//            setCameraDisabled(true);
        }

        if (requestCode == 10 && resultCode == RESULT_OK) {
            // 앱이 기기 소유자로 설정됨
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}