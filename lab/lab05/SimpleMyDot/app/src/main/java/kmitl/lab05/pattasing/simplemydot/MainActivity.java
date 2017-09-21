package kmitl.lab05.pattasing.simplemydot;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kmitl.lab05.pattasing.simplemydot.Model.Dots;
import kmitl.lab05.pattasing.simplemydot.View.DotView;
import kmitl.lab05.pattasing.simplemydot.View.DotViewFragment;

public class MainActivity extends AppCompatActivity {

    private DotView dotView;
    private Dots dots;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, DotViewFragment.newInstance())
                .commit();
    }
}
