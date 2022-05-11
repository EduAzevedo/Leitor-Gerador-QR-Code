package com.eduazevedo.qrcode;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

public class QrCode extends AppCompatActivity {

    private TextView txt_codigo;
    private String codigo;
    private Button btn_copy, btn_voltar, btn_browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        iniciaComponentes();
        configCliques();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            codigo = (String) bundle.getSerializable("qrcode");
            txt_codigo.setText(codigo);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void configCliques() {
        btn_copy.setOnClickListener(view -> {
            final ClipboardManager clipboardManager = (ClipboardManager) getBaseContext()
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            final ClipData clipData = ClipData
                    .newPlainText("Codigo copiado: ", codigo);
            clipboardManager.setPrimaryClip(clipData);
            Snackbar snackbar = Snackbar.make(view, "Copiado", Snackbar.LENGTH_LONG);
            snackbar.show();
        });

        btn_browser.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(codigo));
                startActivity(intent);
        });

        btn_voltar.setOnClickListener(view -> {
            finish();
        });
    }

    private void iniciaComponentes() {
        txt_codigo = findViewById(R.id.txt_codigo);
        btn_copy = findViewById(R.id.btn_copy);
        btn_voltar = findViewById(R.id.btn_voltar);
        btn_browser = findViewById(R.id.btn_browser);
    }

}