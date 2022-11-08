package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Trang home");
        initView();
    }

    private void initView() {
        Button btnDanhMuc = this.findViewById(R.id.btnDanhMuc);
        Button btnGioHang = this.findViewById(R.id.btnGioHang);
        Button btnTaiKhoan = this.findViewById(R.id.btnTaiKhoan);
        Button btnLogout = this.findViewById(R.id.btnLogout);
//        ImageView imv1 =this.findViewById(R.id.imv1);
//        ImageView imv2 =this.findViewById(R.id.imv2);
//        ImageView imv3 =this.findViewById(R.id.imv3);
//        ImageView imv4 =this.findViewById(R.id.imv4);

        btnDanhMuc.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, CategoryActivity.class));
        });

        btnGioHang.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, BillActivity.class));
        });

        btnTaiKhoan.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, AccountDetailActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            new MySharedPreferences(DashboardActivity.this).clearAllData();
            Intent mIntent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        });
    }
}
