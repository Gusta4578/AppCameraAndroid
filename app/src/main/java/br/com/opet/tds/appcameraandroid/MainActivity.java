package br.com.opet.tds.appcameraandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnCamera;
    private ImageView imgCamera;
    private ImageView imgBase64;
    private TextView textBase64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.btnCamera);
        imgCamera = (ImageView) findViewById(R.id.imageCamera);
        imgBase64 = (ImageView) findViewById(R.id.imageBase64);
        textBase64 = (TextView) findViewById(R.id.textImageBase64);
        btnCamera.setOnClickListener(this);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imgCamera.setImageBitmap(imageBitmap);
            String base64Rep = ImageUtil.convertToBase64(imageBitmap);
            textBase64.setText(base64Rep);
            imgBase64.setImageBitmap(ImageUtil.convertToBitMap(base64Rep));
        }
    }
    @Override
    public void onClick(View view) {
        dispatchTakePictureIntent();
    }
}
