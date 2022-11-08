package hcmute.edu.vn.mssv18110273.appbanhang.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillDetailModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BanHangDAO
{

    @Query("SELECT * FROM account where username = :username and password = :password")
    AccountModel getTaiKhoan(String username, String password);

    @Query("SELECT * FROM account where username = :username ")
    AccountModel getTaiKhoanRegister(String username);

    @Query("SELECT * FROM account where username = :username and email = :email ")
    AccountModel getTaiKhoanRegisterByEmail(String username, String email);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertAccount(AccountModel accountModel);

    @Update(onConflict = REPLACE)
    void updateAccount(AccountModel accountModel);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertCategory(CategoryModel categoryModel);

    @Query("SELECT * FROM category")
    List<CategoryModel> findAllCategories();

    @Query("SELECT * FROM item where category_id = :categoryId ")
    List<ItemModel> findAllItems(Long categoryId);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertItem(ItemModel itemModel);

    @Update(onConflict = REPLACE)
    void updateItem(ItemModel itemModel);

    @Delete
    void deleteItem(ItemModel itemModel);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insertBill(BillModel billModel);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertBillDetail(BillDetailModel billDetailModel);

    @Query("SELECT * FROM bill order by id desc")
    List<BillModel> findAllBills();

    @Query("SELECT * FROM bill_detail where bill_id = :billId ")
    List<BillDetailModel> findAllBillDetail(Long billId);

}
