package kmitl.lab09.pattasing.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSave;
    private EditText editTextList;
    private EditText editTextAmount;
    private TextView textIncome;
    private TextView textOutCome;
    private String type;
    private MoneyTable moneyTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        editTextList = (EditText) findViewById(R.id.editList);
        editTextAmount = (EditText) findViewById(R.id.editAmount);
        textIncome = (TextView) findViewById(R.id.textViewIncome);
        textOutCome = (TextView) findViewById(R.id.textViewOutCome);
        type  = "+";
        checkType(type);
        textOutCome.setOnClickListener(this);
        textIncome.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == textIncome) {
            type = "+";
            checkType(type);
        }
        else if(view == textOutCome) {
            type = "-";
            checkType(type);
        }
        else if(view == buttonSave) {

            if(!editTextAmount.getText().toString().equals("") && !editTextList.getText().toString().equals("")){
                final MoneyDB moneyDB = Room.databaseBuilder(this,
                        MoneyDB.class, "MONEY_TABLE")
                        .fallbackToDestructiveMigration()
                        .build();

                new AsyncTask<Void, Void, MoneyTable>() {
                    @Override
                    protected MoneyTable doInBackground(Void... voids) {
                        moneyTable = new MoneyTable();
                        moneyTable.setType(type);
                        moneyTable.setTextList(editTextList.getText().toString());
                        moneyTable.setAmount(Double.parseDouble(editTextAmount.getText().toString()));

                        moneyDB.getMoneyDAO().insert(moneyTable);
                        return null;
                    }
                }.execute();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }else {
                Toast.makeText(this,"Something Wrong",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void checkType(String type){
        if(type.equals("+")) {
//            textIncome.setBackgroundColor(Color.parseColor("#000000"));
            textIncome.setBackgroundColor(getResources().getColor(R.color.LIGHTCORAL));
            textOutCome.setBackgroundColor(getResources().getColor(R.color.WHITE));
        }
        else{
            textOutCome.setBackgroundColor(getResources().getColor(R.color.LIGHTCORAL));
            textIncome.setBackgroundColor(getResources().getColor(R.color.WHITE));
        }
    }
}
