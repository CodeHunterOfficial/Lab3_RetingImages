package com.example.graphapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnGo;
    private Button btnlike, btndislike;
    private EditText txtUrl;
    private ImageView img;
    private Photo[] myphoto = new Photo[8];
    private String[] filename = new String[8];
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename[0] = "dog1.jpg";
        filename[1] = "dog2.jpg";
        filename[2] = "dog3.jpg";
        filename[3] = "dog4.png";
        filename[4] = "dog5.png";
        filename[5] = "dog6.png";
        filename[6] = "dog7.jpg";
        filename[7] = "dog8.jpg";

        for (int i = 0; i < 8; i++) {
            myphoto[i] = new Photo(filename[i]);
        }
        i = 0;
        txtUrl = findViewById(R.id.edit1);
        btnGo = findViewById(R.id.button);
        btndislike = findViewById(R.id.btndislike);
        btnlike = findViewById(R.id.btnlike);
        img = findViewById(R.id.imageView);
        btnGo.setOnClickListener(v -> {
            String url = String.valueOf(txtUrl.getText());
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        btndislike.setOnClickListener(v -> {
            i++;
            try (InputStream inputStream = getApplicationContext().getAssets().open(myphoto[i].name)) {
                myphoto[i].rating = false;
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                img.setImageDrawable(drawable);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                i = i % 7;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnlike.setOnClickListener(v -> {
            i++;
            try (InputStream inputStream = getApplicationContext().getAssets().open(myphoto[i].name)) {
                myphoto[i].rating = true;
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                img.setImageDrawable(drawable);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                i = i % 7;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}