package com.eduazevedo.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private Button btn_scanear, btn_gerar;
    private ImageView img_qr_code;

    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciaComponentes();

        Glide.with(this).load(R.drawable.codigo_qr).into(img_qr_code);

        btn_scanear.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(activity);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Camera SCAN");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(false);
            integrator.initiateScan();
        });

        btn_gerar.setOnClickListener(view -> {
            Intent intent = new Intent(this, GerarQrCode.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                alert(result.getContents());
            } else {
                Toast.makeText(this, "Leitura cancelada!", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg) {
        Intent intent = new Intent(this, QrCode.class);
        intent.putExtra("qrcode", msg);
        startActivity(intent);
    }

    private void iniciaComponentes() {
        btn_scanear = findViewById(R.id.btn_scanear);
        btn_gerar = findViewById(R.id.btn_gerar);
        img_qr_code = findViewById(R.id.img_qr_code);

    }
}