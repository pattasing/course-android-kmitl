package kmitl.lab09.pattasing.moneyflow;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<MoneyTable> {

    private List<MoneyTable> moneyTablesList;
    private Context context;
    private int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<MoneyTable> objects) {
        super(context, resource, objects);
        this.context = context;
        this.moneyTablesList = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        TextView textViewType = view.findViewById(R.id.textItemSymbol);
        TextView textViewList = view.findViewById(R.id.textItemList);
        TextView textViewAmount = view.findViewById(R.id.textItemAmount);


        if(moneyTablesList.get(position).getType().equals("+")){
            textViewType.setBackgroundColor(Color.rgb(190,216,197));
            textViewList.setBackgroundColor(Color.rgb(190,216,197));
            textViewAmount.setBackgroundColor(Color.rgb(190,216,197));
        }
        else {
            textViewType.setBackgroundColor(Color.rgb(216,190,190));
            textViewList.setBackgroundColor(Color.rgb(216,190,190));
            textViewAmount.setBackgroundColor(Color.rgb(216,190,190));
        }


        textViewType.setText(moneyTablesList.get(position).getType());
        textViewList.setText(moneyTablesList.get(position).getTextList());
        textViewAmount.setText((moneyTablesList.get(position).getAmount())+"");


        return view;
    }
}
