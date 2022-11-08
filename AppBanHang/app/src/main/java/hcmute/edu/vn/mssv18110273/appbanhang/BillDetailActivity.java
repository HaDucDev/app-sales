package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.adapter.BillDetailAdapter;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillDetailModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class BillDetailActivity extends AppCompatActivity {
    private RecyclerView rcvItems;
    private BillDetailAdapter adapter;
    private List<BillDetailModel> listSubDonHangEntities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết đơn hàng");
        initView();
        initAdapter();
    }


    private void initView() {
        rcvItems = findViewById(R.id.rcvChiTietDonHang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BillDetailActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvItems.setItemAnimator(new DefaultItemAnimator());
        rcvItems.setLayoutManager(layoutManager);
    }

    private void initAdapter() {
        Intent intent = getIntent();
        Long billId = intent.getExtras().getLong(Const.KEY_SHARE_PREFERENCE.KEY_DONHANG_ID);
        listSubDonHangEntities = new ArrayList<>();
        listSubDonHangEntities.addAll(BanHangDatabase.getInstance(BillDetailActivity.this).banHangDAO().findAllBillDetail(billId));
        adapter = new BillDetailAdapter(BillDetailActivity.this, listSubDonHangEntities);
        rcvItems.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(BillDetailActivity.this, BillActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
