package com.sergio.imgtopdf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Collections.singletonList;

public class MainActivity extends AppCompatActivity {


    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final String LOG_ACTIVITY = "MainActivity";

    Activity activity;
    Image image;




    String path, filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        activity = this;



        filename=new Date().toString();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getImages();

            }
        });
    }


    private void getImages() {

        Config config = new Config();

        config.setToolbarTitleRes(R.string.str_bar_tool);
        //config.

        ImagePickerActivity.setConfig(config);

        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);

    }


    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (requestCode == INTENT_REQUEST_GET_IMAGES && resuleCode == Activity.RESULT_OK) {




            ArrayList<Uri> image_uris = intent.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);

            if(image_uris.size()!=0){

                ArrayList<String> tempUris = new ArrayList<>();

                for (Uri uri : image_uris) {

                    tempUris.add(uri.getPath());
                }

                CallBackCreatePdf callBackCreatePdf = new CallBackCreatePdf() {
                    @Override
                    public void OnCallBackCreatePdf(String tPath) {

                        if(tPath!=""){

                         Toast.makeText(getBaseContext(),"Pdf Creado",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getBaseContext(),"Error al crear el Pdf",Toast.LENGTH_LONG).show();
                        }


                    }
                };

                MaterialDialog.Builder builder = new MaterialDialog.Builder(activity)
                        .title("Creando Pdf")
                        .content("un momento ...")
                        .cancelable(false)
                        .progress(true, 0);
                MaterialDialog dialog = builder.build();

                AsynCreatePdf asynCreatePdf = new AsynCreatePdf(activity,callBackCreatePdf,dialog,tempUris,"1",("pdf"+(new Date()).getSeconds()));

                asynCreatePdf.execute();
            }

            Log.d(LOG_ACTIVITY, "onActivityResult");



        }
    }



}



