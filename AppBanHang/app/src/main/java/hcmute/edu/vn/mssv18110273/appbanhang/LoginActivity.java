package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;
import hcmute.edu.vn.mssv18110273.appbanhang.database.ResourceData;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText edt_username, edt_password;
    private TextView tvRegister, tvForgot;

    private BanHangDAO banHangDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData()
    {
        banHangDAO = BanHangDatabase.getInstance(LoginActivity.this).banHangDAO();

        //Them moi data truoc khi chay du lieu
//        ResourceData.insertCategory(banHangDAO);
//        ResourceData.insertItem(banHangDAO);
    }

    private void initView()
    {
        edt_username = this.findViewById(R.id.edt_uername_login);
        edt_password = this.findViewById(R.id.edt_password_login);
        btn_login = this.findViewById(R.id.btn_login);
        tvRegister = this.findViewById(R.id.tvRegisterAccount);
        tvForgot = this.findViewById(R.id.tvForgotPassword);

        btn_login.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                onClickLogin();
                break;
            }
            case R.id.tvRegisterAccount: {
                onClickRegister();
                break;
            }

            case R.id.tvForgotPassword: {
                onClickForgotPassword();
                break;
            }
        }
    }

    private void onClickForgotPassword() {
        Intent mIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(mIntent);
    }

    private void onClickRegister() {
        Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(mIntent);
    }

    private void onClickLogin() {
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        checkUserLogin(username, password);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    private void checkUserLogin(String username, String password) {
        AccountModel account = banHangDAO.getTaiKhoan(username, password);
        if (account == null) {
            Toast.makeText(LoginActivity.this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
        } else {
            new MySharedPreferences(LoginActivity.this).putAccount(Const.KEY_SHARE_PREFERENCE.KEY_ACCOUNT, account);
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
    }
}