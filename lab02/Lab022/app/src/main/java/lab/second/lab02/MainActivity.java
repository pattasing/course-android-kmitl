package lab.second.lab02;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_at_home2);

        TextView textView = (TextView) findViewById(R.id.textViewTerm);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView textView2 = (TextView) findViewById(R.id.textViewPri);
        textView2.setPaintFlags(textView2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void books(View view) {
        Toast.makeText(
                getBaseContext(),
                "Books Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void games(View view) {
        Toast.makeText(
                getBaseContext(),
                "Games Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void apps(View view) {
        Toast.makeText(
                getBaseContext(),
                "Apps Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void movies(View view) {
        Toast.makeText(
                getBaseContext(),
                "Movies Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void more1(View view) {
        Toast.makeText(
                getBaseContext(),
                "More Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void more2(View view) {
        Toast.makeText(
                getBaseContext(),
                "More Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void seeNew(View view) {
        Toast.makeText(
                getBaseContext(),
                "See New Topics Click ",
                Toast.LENGTH_LONG)
                .show();
    }
    public void skip(View view) {
        Toast.makeText(
                getBaseContext(),
                "Skip Click ",
                Toast.LENGTH_LONG)
                .show();
    }
}
