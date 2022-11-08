package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class AccountDetailActivity extends AppCompatActivity {
    private TextView tvUsername, tvPhone, tvEmail, tvFullName, tvAddress, tvPassword;

    private AccountModel accountModel;
    private MySharedPreferences preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail_info);
        Button btnSua=findViewById(R.id.btnSua);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        initView();
        initData();

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AccountDetailActivity.this,UpdateAccountActivity.class));
            }
        });
    }


    private void initView() {

        tvPhone = this.findViewById(R.id.tvPhoneUserInfo);
        tvEmail = this.findViewById(R.id.tvEmailUserInfo);
        tvUsername = this.findViewById(R.id.tvUsernameUserInfo);
        tvFullName = this.findViewById(R.id.tvFullNameUserInfo);
        tvAddress =this.findViewById(R.id.tvAđressUserInfo);
        tvPassword=this.findViewById(R.id.tvPasswordUserInfo);
    }

    private void initData() {
        preferences = new MySharedPreferences(this);
        accountModel = preferences.getAccount(Const.KEY_SHARE_PREFERENCE.KEY_ACCOUNT);

        tvUsername.setText(accountModel.getUsername());
        tvEmail.setText(accountModel.getEmail());
        tvPhone.setText(accountModel.getMobile());
        tvFullName.setText(accountModel.getFullName());
        tvAddress.setText(accountModel.getAddress());
        tvPassword.setText(accountModel.getPassword());
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
