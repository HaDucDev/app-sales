package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.adapter.ItemAdapter;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemInCartModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button btnTotal;
    private RecyclerView rcvItem;
    private ItemAdapter sanPhamAdapter;
    private List<ItemModel> listSanPham;

    private AlertDialog detailSanPhamDialog;
    private ImageView imvDlg;
    private EditText edtNameDlg, edtGiaDlg, edtDesc;
    private TextView tvTitleDlg;
    private LinearLayout llButton;

    private CategoryModel categoryModel;
    private ItemInCartModel itemInCart;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sản phẩm");
        initView();
        initAdapter();
        initDialog();
    }

    private void initView() {
        rcvItem = this.findViewById(R.id.rcvSanPhamBanHang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ItemActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvItem.setItemAnimator(new DefaultItemAnimator());
        rcvItem.setLayoutManager(layoutManager);

        btnTotal = this.findViewById(R.id.btnTotal);
        btnTotal.setTransformationMethod(null);

        btnTotal.setOnClickListener(this);
    }

    private void initAdapter() {
        Intent mIntent = getIntent();
        categoryModel = mIntent.getExtras().getParcelable(Const.KeyBundle.CATEGORY_BUNDLE);
        mySharedPreferences = new MySharedPreferences(ItemActivity.this);

        listSanPham = new ArrayList<>();
        listSanPham.addAll(BanHangDatabase.getInstance(ItemActivity.this).banHangDAO().findAllItems(categoryModel.getId()));
        sanPhamAdapter = new ItemAdapter(ItemActivity.this, listSanPham, new ItemAdapter.onClickSanPhamListener() {
            @Override
            public void onClickViewDetail(ItemModel item) {
                edtNameDlg.setTextColor(getResources().getColor(R.color.black));
                edtGiaDlg.setTextColor(getResources().getColor(R.color.black));
                edtDesc.setTextColor(getResources().getColor(R.color.black));
                edtNameDlg.setText(item.getName());
                edtGiaDlg.setText(item.getPrice().toString());
                edtDesc.setText(item.getDescription());
                Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.ic_app)
                        .error(R.drawable.ic_app).into(imvDlg);

                showDialog();
            }

            @Override
            public void onClickAddCart(ItemModel item) {
                itemInCart = new ItemInCartModel();
                itemInCart.setSoLuong(1L);
                itemInCart.setId(item.getId());
                itemInCart.setItemModel(item);
                itemInCart.setGiaBan(item.getPrice());
                itemInCart.setTongTien(item.getPrice());

                List<ItemInCartModel> lstItemCart = new ArrayList<>();
                if (mySharedPreferences.getListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART) != null) {
                    lstItemCart.addAll(mySharedPreferences.getListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART));
                }
                if (lstItemCart.size() > 0) {
                    ItemInCartModel itemRemove = null;
                    for (ItemInCartModel obj : lstItemCart) {
                        if (obj != null) {
                            if (itemInCart.getId().equals(obj.getItemModel().getId())) {
                                itemRemove = obj;
                            }
                        }
                    }
                    if (itemRemove != null) {
                        lstItemCart.remove(itemRemove);
                    }
                }
                lstItemCart.add(itemInCart);
                mySharedPreferences.putListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART, lstItemCart);

                Toast.makeText(ItemActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        rcvItem.setAdapter(sanPhamAdapter);
    }

    private void initDialog() {
        detailSanPhamDialog = new AlertDialog.Builder(ItemActivity.this, R.style.CustomAlertDialog).create();
        View mViewDialog = getLayoutInflater().inflate(R.layout.dialog_add_sanpham, null);
        edtNameDlg = mViewDialog.findViewById(R.id.edtTenDlg);
        edtGiaDlg = mViewDialog.findViewById(R.id.edtGiaBanDlg);
        edtDesc = mViewDialog.findViewById(R.id.edtDescDlg);
        tvTitleDlg = mViewDialog.findViewById(R.id.tvTitleDlg);
        imvDlg = mViewDialog.findViewById(R.id.imvImageDlg);
        llButton = mViewDialog.findViewById(R.id.llButtonDlg);
        llButton.setVisibility(View.GONE);
        edtGiaDlg.setEnabled(false);
        edtNameDlg.setEnabled(false);
        edtDesc.setEnabled(false);
        tvTitleDlg.setText("Chi tiết");
        detailSanPhamDialog.setView(mViewDialog);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTotal: {
                if (mySharedPreferences.getListItemInCart(Const.KEY_SHARE_PREFERENCE.KEY_LIST_ITEM_CART) != null) {
                    startActivity(new Intent(ItemActivity.this, CartActivity.class));
                    finish();
                } else {
                    Toast.makeText(ItemActivity.this, "Thêm sản phẩm trước", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void showDialog() {
        if (detailSanPhamDialog != null && !detailSanPhamDialog.isShowing()) {
            detailSanPhamDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
