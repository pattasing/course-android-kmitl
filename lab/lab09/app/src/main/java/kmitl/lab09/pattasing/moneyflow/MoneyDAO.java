package kmitl.lab09.pattasing.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
interface MoneyDAO {
    @Insert
    void insert(MoneyTable moneyTable);

    @Query("SELECT * FROM MONEY_TABLE")
    List<MoneyTable> getAll();

    @Delete
    public void deleteLine(MoneyTable moneyTable);

    @Update
    void updateLine(MoneyTable moneyTable);

}
