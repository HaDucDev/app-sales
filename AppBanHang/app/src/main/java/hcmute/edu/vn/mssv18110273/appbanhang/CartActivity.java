package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.adapter.CartAdapter;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillDetailModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemInCartModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;
import hcmute.edu.vn.mssv18110273.appbanhang.util.StringFormatUtils;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTotalPrice;
    private RecyclerView rcvItems;
    private List<ItemInCartModel> lstItemCart;
    private CartAdapter itemInCartAdapter;

    private Long totalPrice = 0L;
    private AccountModel accountModel;
    private MySharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Giỏ hàng");
        initView();
        initData();
        initAdapter();
        calculatorCurrentTotalPrice();
    }

    private void initView() {
        rcvItems = findViewById(R.id.rcvItemsCart);
        Button btnOrder = findViewById(R.id.btnOrderCart);
        tvTotalPrice = findViewById(R.id.tvTotalPriceCart);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvItems.setItemAnimator(new DefaultItemAnimator());
        rcvItems.setLayoutManager(layoutManager);

        btnOrder.setOnClickListener(this);
    }

    private void initData() {
        lstItemCart = new ArrayList<>();
        preferences = new MySharedPreferences(CartActivity.this);
        if (preferences.getListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART) != null) {
            lstItemCart.addAll(preferences.getListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART));
        }

        accountModel = preferences.getAccount(Const.KEY_SHARE_PREFERENCE.KEY_ACCOUNT);
    }

    private void initAdapter() {
        itemInCartAdapter = new CartAdapter(CartActivity.this, lstItemCart, new CartAdapter.onClickListener() {
            @Override
            public void onClickDelete(ItemInCartModel item) {
                lstItemCart.remove(item);
                calculatorCurrentTotalPrice();
                itemInCartAdapter.notifyDataSetChanged();
                preferences.clearDataByKey(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART);
                preferences.putListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART, lstItemCart);
            }

            @Override
            public void onPlusClick(ItemInCartModel item) {
                setNumOrder(item);
                itemInCartAdapter.notifyDataSetChanged();
                calculatorCurrentTotalPrice();
            }

            @Override
            public void onMinusClick(ItemInCartModel item) {
                setNumOrder(item);
                itemInCartAdapter.notifyDataSetChanged();
                calculatorCurrentTotalPrice();
            }
        });
        rcvItems.setAdapter(itemInCartAdapter);
    }

    private void calculatorCurrentTotalPrice() {
        totalPrice = 0L;
        if (lstItemCart != null && !lstItemCart.isEmpty()) {
            for (ItemInCartModel obj : lstItemCart) {
                totalPrice += obj.getTongTien();
            }
        }
        tvTotalPrice.setText(StringFormatUtils.convertToStringMoneyVND(totalPrice));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOrderCart) {
            onClickButtonOrder();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onClickButtonOrder() {
        if (lstItemCart == null || lstItemCart.isEmpty()) {
            Toast.makeText(CartActivity.this, "Thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            return;
        }

        BillModel billModel = new BillModel();
        billModel.setId(null);
        billModel.setNameCustomer(accountModel.getFullName());
        billModel.setAddressCustomer(accountModel.getAddress());
        billModel.setPhoneCustomer(accountModel.getMobile());
        billModel.setTotalPrice(totalPrice);
        billModel.setPaymentType("Thanh toán sau khi nhận hàng");
        billModel.setCreatedDate(StringFormatUtils.getCurrentDateStr());
        long idInsert = BanHangDatabase.getInstance(CartActivity.this).banHangDAO().insertBill(billModel);

        lstItemCart.forEach(obj -> {
            BillDetailModel billDetailModel = new BillDetailModel();
            billDetailModel.setId(null);
            billDetailModel.setBillId(idInsert);
            billDetailModel.setPrice(obj.getGiaBan());
            billDetailModel.setItemId(obj.getId());
            billDetailModel.setQuantity(obj.getSoLuong());
            billDetailModel.setTotalPrice(obj.getTongTien());
            billDetailModel.setItemName(obj.getItemModel().getName());

            BanHangDatabase.getInstance(CartActivity.this).banHangDAO().insertBillDetail(billDetailModel);
        });

        preferences.clearDataByKey(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART);
        Toast.makeText(CartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CartActivity.this, DashboardActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(CartActivity.this, CategoryModel.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNumOrder(ItemInCartModel item) {
        if (lstItemCart.size() > 1) {
            for (int i = 0; i < lstItemCart.size(); i++) {
                ItemInCartModel obj = lstItemCart.get(i);
                if (item.getId().equals(obj.getItemModel().getId())) {
                    lstItemCart.set(i, item);
                    preferences.putListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART, lstItemCart);
                    break;
                }
            }
        }
    }
}
