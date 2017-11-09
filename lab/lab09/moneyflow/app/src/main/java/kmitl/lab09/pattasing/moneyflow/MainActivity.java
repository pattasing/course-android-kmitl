package kmitl.lab09.pattasing.moneyflow;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private Button buttonAdd;
    private ListView list;
    private int RESULT_ADD = 234;
    private CustomAdapter adapter;
    private TextView textTotal;
    private int RESULT_UP = 235;
    private List<MoneyTable> moneyTableListGlobal;
    private int indexForDelete;
    private TextView tip;
    private int indexForUpdate;
    private TextView tip2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calMoney();

        buttonAdd = (Button) findViewById(R.id.buttonAddList);
        buttonAdd.setOnClickListener(this);
        list = (ListView) findViewById(R.id.listView);
        list.setOnItemLongClickListener(this);
        list.setOnItemClickListener(this);
        textTotal = (TextView) findViewById(R.id.total);
        tip = (TextView) findViewById(R.id.textViewTip);
        tip2 = (TextView) findViewById(R.id.tip2);
    }
    @Override
    public void onClick(View view) {
        if(view == buttonAdd) {
            Intent intent = new Intent(MainActivity.this, AddListActivity.class);
            startActivityForResult(intent, RESULT_ADD);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_ADD && resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this,"Add Success",Toast.LENGTH_LONG).show();
            calMoney();
//            Toast.makeText(MainActivity.this,"Add Success",Toast.LENGTH_LONG).show();
        }
        else if(requestCode == RESULT_UP && resultCode == RESULT_OK){
            calMoney();
            Toast.makeText(MainActivity.this,"Update Success",Toast.LENGTH_LONG).show();
        }
    }

    public void calMoney(){

        final MoneyDB moneyDB = Room.databaseBuilder(this,
                MoneyDB.class, "MONEY_TABLE")
                .fallbackToDestructiveMigration()
                .build();

        new AsyncTask<Void, Void, List<MoneyTable>>() {
            @Override
            protected List<MoneyTable> doInBackground(Void... voids) {
                List<MoneyTable> moneyTableslist = moneyDB.getMoneyDAO()
                        .getAll();
//                moneyTableListGlobal = moneyTableslist;
                return moneyTableslist;
            }

            @Override
            protected void onPostExecute(List<MoneyTable> moneyTables) {
                moneyTableListGlobal = moneyTables;
                adapter = new CustomAdapter(
                        MainActivity.this,
                        R.layout.item_list, moneyTables);

                Double total = 0.00;
                Double income = 0.00;
                Double outcome = 0.00;

                for(MoneyTable item : moneyTables) {
                    if(item.getType().equals("+")){
                        income += item.getAmount();
                    }
                    else{
                        outcome += item.getAmount();
                    }
                }
                total = income - outcome;
                if(total > 0.5 * income) {
                    textTotal.setTextColor(Color.rgb(65,163,23));
                }
                else if((0.25 * income <= total) && (total <= 0.5 * income)){
                    textTotal.setTextColor(Color.rgb(251,185,23));
                }
                else {
                    textTotal.setTextColor(Color.rgb(255,36,0));
                }

                textTotal.setText(total.toString());

                list.setAdapter(adapter);
            }
        }.execute();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        indexForDelete = i;
        final MoneyDB moneyDB = Room.databaseBuilder(this,
                MoneyDB.class, "MONEY_TABLE")
                .build();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {

                new AsyncTask<Void, Void, MoneyTable>() {
                    @Override
                    protected MoneyTable doInBackground(Void... voids) {
                        moneyDB.getMoneyDAO().deleteLine(moneyTableListGlobal.get(indexForDelete));
                        return null;
                    }
                }.execute();


//                moneyDB.getMoneyDAO().deleteLine(moneyTable);
//                adapter.notifyDataSetChanged();
                calMoney();
                Toast.makeText(MainActivity.this,"Deletetd",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        indexForUpdate = i;
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("itemType", moneyTableListGlobal.get(i).getType());
        intent.putExtra("itemList", moneyTableListGlobal.get(i).getTextList());
        intent.putExtra("itemAmount", moneyTableListGlobal.get(i).getAmount()+"");
        startActivityForResult(intent, RESULT_UP);
    }
}
