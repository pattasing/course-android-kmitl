package kmitl.lab09.pattasing.moneyflow;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSave;
    private EditText editTextList;
    private EditText editTextAmount;
    private TextView textIncome;
    private TextView textOutCome;
    private String type;
    private MoneyTable moneyTable;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        buttonSave = (Button) findViewById(R.id.buttonSaveU);
        editTextList = (EditText) findViewById(R.id.editListU);
        editTextAmount = (EditText) findViewById(R.id.editAmountU);
        textIncome = (TextView) findViewById(R.id.textViewIncomeU);
        textOutCome = (TextView) findViewById(R.id.textViewOutComeU);

        Intent intent = getIntent();
        type = intent.getStringExtra("itemType");
        id = intent.getStringExtra("itemId");
        editTextAmount.setText(intent.getStringExtra("itemAmount")+"");
        editTextList.setText(intent.getStringExtra("itemList"));
        textOutCome.setOnClickListener(this);
        textIncome.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        checkType(type);
    }

    public void checkType(String type){
        if(type.equals("+")) {
            textIncome.setBackgroundColor(getResources().getColor(R.color.LIGHTCORAL));
            textOutCome.setBackgroundColor(getResources().getColor(R.color.WHITE));
        }
        else{
            textOutCome.setBackgroundColor(getResources().getColor(R.color.LIGHTCORAL));
            textIncome.setBackgroundColor(getResources().getColor(R.color.WHITE));
        }
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
                        moneyTable.setId(Integer.parseInt(id));
                        moneyTable.setType(type);
                        moneyTable.setTextList(editTextList.getText().toString());
                        moneyTable.setAmount(Double.parseDouble(editTextAmount.getText().toString()));

                        moneyDB.getMoneyDAO().updateLine(moneyTable);
                        return null;
                    }
                }.execute();

                Intent intent = new Intent();
                intent.putExtra("itemType", type);
                intent.putExtra("itemList", editTextList.getText().toString());
                intent.putExtra("itemAmount", editTextAmount.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }else {
                Toast.makeText(this,"Something Wrong",Toast.LENGTH_LONG).show();
            }

        }
    }
}
