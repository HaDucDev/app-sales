package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.adapter.BillAdapter;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class BillActivity extends AppCompatActivity {
    private RecyclerView rcvDonHang;
    private BillAdapter donHangAdapter;
    private List<BillModel> listDonHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Danh sách đơn hàng");
        initView();
        initAdapter();
    }

    private void initView() {
        rcvDonHang = this.findViewById(R.id.rcvDonHang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BillActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvDonHang.setItemAnimator(new DefaultItemAnimator());
        rcvDonHang.setLayoutManager(layoutManager);
    }

    private void initAdapter() {
        listDonHang = new ArrayList<>();
        listDonHang.addAll(BanHangDatabase.getInstance(BillActivity.this).banHangDAO().findAllBills());
        donHangAdapter = new BillAdapter(BillActivity.this, listDonHang, item -> {
            Intent mIntent = new Intent(BillActivity.this, BillDetailActivity.class);
            mIntent.putExtra(Const.KEY_SHARE_PREFERENCE.KEY_DONHANG_ID, item.getId());
            startActivity(mIntent);
            finish();
        });
        rcvDonHang.setAdapter(donHangAdapter);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BillActivity.this, DashboardActivity.class));
        finish();
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
