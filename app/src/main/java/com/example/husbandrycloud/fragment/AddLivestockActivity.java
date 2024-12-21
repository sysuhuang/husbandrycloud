package com.example.husbandrycloud.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.husbandrycloud.R;

public class AddLivestockActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText breedEditText, birthDateEditText, healthRecordEditText;
    private ImageView livestockImageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_livestock);

        breedEditText = findViewById(R.id.editTextBreed);
        birthDateEditText = findViewById(R.id.editTextBirthDate);
        healthRecordEditText = findViewById(R.id.editTextHealthRecord);
        livestockImageView = findViewById(R.id.imageViewLivestock);
        Button uploadButton = findViewById(R.id.buttonUpload);
        Button selectImageButton = findViewById(R.id.buttonSelectImage);

        selectImageButton.setOnClickListener(v -> openGallery());

        uploadButton.setOnClickListener(v -> uploadLivestockData());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            livestockImageView.setImageURI(imageUri);
        }
    }

    private void uploadLivestockData() {
        String breed = breedEditText.getText().toString();
        String birthDate = birthDateEditText.getText().toString();
        String healthRecord = healthRecordEditText.getText().toString();

        if (breed.isEmpty() || birthDate.isEmpty() || healthRecord.isEmpty() || imageUri == null) {
            Toast.makeText(this, "请填写所有字段并选择图片", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 实现数据上传逻辑（如 API 调用）

        Toast.makeText(this, "牲畜数据上传成功", Toast.LENGTH_SHORT).show();
        finish(); // 结束活动
    }
}