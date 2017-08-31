package pattasing.lab03.kmitl.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import pattasing.lab03.kmitl.simplemydot.model.Dot;
import pattasing.lab03.kmitl.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;
    private ArrayList<Dot> setDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot (this, 0,0,30);
        setDot = new ArrayList<>();
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDot.clear();
                dotView.invalidate();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float positionX =  e.getX();
        float positionY =  e.getY()-200;
        CreateDot(positionX, positionY);
        return true;
    }

    public void CreateDot(float positionX, float positionY){

        Random random = new Random();
        int radius = random.nextInt(200);
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        dot = new Dot (this, positionX, positionY, radius);
        dot.setColor(red, green, blue);
        dot.setCenterX(positionX);
        dot.setCenterY(positionY);
        dot.setRadius(radius);
        setDot.add(dot);
    }


    public void onRandomDot(View view) {
        Random random = new Random();
        int radius = random.nextInt(200);
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        CreateDot(centerX, centerY);

    }





    @Override
    public void onDotChanged(Dot dot) {
        dotView.setAllDot(setDot);
        dotView.invalidate();
    }
}
