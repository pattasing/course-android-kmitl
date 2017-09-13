package kmitl.lab04.pattasing.simplemydot;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import kmitl.lab04.pattasing.simplemydot.Model.Colors;
import kmitl.lab04.pattasing.simplemydot.Model.Convertor;
import kmitl.lab04.pattasing.simplemydot.Model.Dot;
import kmitl.lab04.pattasing.simplemydot.Model.Dots;
import kmitl.lab04.pattasing.simplemydot.View.DotView;

public class MainActivity extends AppCompatActivity
        implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener{

    private DotView dotView;
    private Dots dots;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        rootView = (View) findViewById(R.id.rootView).getRootView() ;
        dots = new Dots();
        dots.setListener(this);
    }

    public boolean requestWriteExternalStoragePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            return false;
        }else{
            return true;
        }
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Untitled", null);
        return Uri.parse(path);
    }

    public void share(Uri uri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Screen"));
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 999;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission() {
        int hasWriteExtPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExtPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);

            return;
        }
    }

    public void onShare(View view) {
        if(requestWriteExternalStoragePermission()){
            Bitmap bitmap = Convertor.convertViewtoBitmap(rootView);
            Uri bitmapUri = getImageUri(this.getApplicationContext(), bitmap);
            share(bitmapUri);
        }
    }


    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }



    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if(dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }
}
