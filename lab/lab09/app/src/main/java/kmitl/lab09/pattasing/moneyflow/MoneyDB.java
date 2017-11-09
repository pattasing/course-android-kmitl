package kmitl.lab09.pattasing.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MoneyTable.class}, version = 1)
public abstract class MoneyDB extends RoomDatabase{
    public abstract MoneyDAO getMoneyDAO();
}
