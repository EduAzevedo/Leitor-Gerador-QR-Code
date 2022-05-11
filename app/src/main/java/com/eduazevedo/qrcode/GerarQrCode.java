package com.eduazevedo.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GerarQrCode extends AppCompatActivity {

    private ImageView img_qr_code;
    private Button btn_gerar, btn_voltar;
    private EditText edt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_qr_code);

        iniciaComponentes();
        configCliques();

    }

    private void configCliques() {
        btn_gerar.setOnClickListener(view -> {
            if (!edt_text.getText().toString().isEmpty()) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(edt_text.getText().toString(), BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    img_qr_code.setImageBitmap(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                }
                img_qr_code.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Insira alguma coisa!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_voltar.setOnClickListener(view -> finish());

    }

    private void iniciaComponentes() {
        img_qr_code = findViewById(R.id.img_qr_code);
        btn_gerar = findViewById(R.id.btn_gerar);
        btn_voltar = findViewById(R.id.btn_voltar);
        edt_text = findViewById(R.id.edt_text);
    }

}