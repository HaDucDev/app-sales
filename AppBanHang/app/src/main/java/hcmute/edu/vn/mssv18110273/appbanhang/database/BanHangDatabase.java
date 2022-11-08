package hcmute.edu.vn.mssv18110273.appbanhang.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillDetailModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;


@Database(entities = {AccountModel.class, CategoryModel.class, ItemModel.class,
        BillModel.class, BillDetailModel.class}, version = 5)
public abstract class BanHangDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "appbanhang.db";
    private static BanHangDatabase instance;

    public static synchronized BanHangDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BanHangDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract BanHangDAO banHangDAO();

}