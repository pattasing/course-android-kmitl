package kmitl.lab05.pattasing.simplemydot.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import kmitl.lab05.pattasing.simplemydot.Model.Colors;
import kmitl.lab05.pattasing.simplemydot.Model.Dot;
import kmitl.lab05.pattasing.simplemydot.Model.Dots;
import kmitl.lab05.pattasing.simplemydot.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment  implements
        Dots.OnDotsChangeListener, DotView.OnDotViewPressListener, View.OnClickListener {

    private DotView dotView;
    private Dots dots;

    public DotViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);
        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);
        dots = new Dots();
        dots.setListener(this);

        Button btn = (Button) rootView.findViewById(R.id.btn);
        Button button = (Button) rootView.findViewById(R.id.button);
        Button removeAll = (Button) rootView.findViewById(R.id.removeAll);

        btn.setOnClickListener(this);
        button.setOnClickListener(this);
        removeAll.setOnClickListener(this);


        return rootView;
    }

    public static DotViewFragment newInstance() {

        Bundle args = new Bundle();

        DotViewFragment fragment = new DotViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                onRandomDot();
                break;
            case R.id.removeAll:
                dots.clearAll();
                break;
            case R.id.btn:
                break;
        }
    }

    public void onRandomDot() {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }
}

