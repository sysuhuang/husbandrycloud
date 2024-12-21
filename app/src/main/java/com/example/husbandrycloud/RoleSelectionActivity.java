package com.example.husbandrycloud;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.husbandrycloud.util.SharedPreferencesManager;



public class RoleSelectionActivity extends AppCompatActivity {

    private boolean hasStoragePermissions(Context context) {
        //版本判断，如果比android 13 就走正常的权限获取
        if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            int readPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return readPermission == PackageManager.PERMISSION_GRANTED && writePermission == PackageManager.PERMISSION_GRANTED;
        }else{
            int audioPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_AUDIO);
            int imagePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES);
            int videoPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_VIDEO);
            return audioPermission == PackageManager.PERMISSION_GRANTED && imagePermission == PackageManager.PERMISSION_GRANTED && videoPermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestStoragePermissions(Context context) {
        String [] permissions;
        if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }else{
            permissions = new String[]{Manifest.permission.READ_MEDIA_AUDIO, Manifest.permission.READ_MEDIA_IMAGES,Manifest.permission.READ_MEDIA_VIDEO};
        }
        ActivityCompat.requestPermissions((Activity) context,
                permissions,
                1234);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        Button userButton = findViewById(R.id.button_user);
        Button merchantButton = findViewById(R.id.button_merchant);
        Button enterpriseButton = findViewById(R.id.button_enterprise);

        if(!hasStoragePermissions(this)){
            requestStoragePermissions(this);
        }



        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManager.setUserRole(RoleSelectionActivity.this, "user");
                launchMainActivity();
            }
        });

        merchantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManager.setUserRole(RoleSelectionActivity.this, "merchant");
                launchMainActivity();
            }
        });

        enterpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesManager.setUserRole(RoleSelectionActivity.this, "enterprise");
                launchMainActivity();
            }
        });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // 结束当前活动
    }
}