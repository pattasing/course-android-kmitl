package kmitl.lab04.pattasing.simplemydot.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class Convertor {

    public Convertor() {
    }

    public static Bitmap convertViewtoBitmap(View view){
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }
}
