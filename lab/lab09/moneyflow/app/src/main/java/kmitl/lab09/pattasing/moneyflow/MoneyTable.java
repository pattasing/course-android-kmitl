package kmitl.lab09.pattasing.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
@Entity(tableName = "MONEY_TABLE")
class MoneyTable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "TYPE")
    String type;

    @ColumnInfo(name  = "TEXTLIST")
    String textList;

    @ColumnInfo(name = "AMOUNT")
    double amount;

    public MoneyTable() {
    }

    @ParcelConstructor
    public MoneyTable(int id, String type, String textList, double amount) {
        this.id = id;
        this.type = type;
        this.textList = textList;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTextList() {
        return textList;
    }

    public void setTextList(String textList) {
        this.textList = textList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s : %s : %.0f", type, textList, amount);
    }
}
