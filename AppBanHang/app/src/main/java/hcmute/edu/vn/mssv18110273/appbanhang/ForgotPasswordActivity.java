package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnConfirm;
    private EditText edtUsername, edtEmail;

    private BanHangDAO banHangDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quên mật khẩu");
        initView();
        initData();
    }

    private void initData() {
        banHangDAO = BanHangDatabase.getInstance(ForgotPasswordActivity.this).banHangDAO();
    }

    private void initView() {
        edtUsername = this.findViewById(R.id.edtUserForgot);
        edtEmail = this.findViewById(R.id.edtEmailForgot);
        btnConfirm = this.findViewById(R.id.btnConfirmForgot);

        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConfirmForgot: {
                onClickConfirm();
                break;
            }
        }
    }

    private void onClickConfirm() {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();

        if (username.isEmpty() || email.isEmpty()) {
            Toast.makeText(ForgotPasswordActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        forgotPassword(username, email);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    private void forgotPassword(String username, String email)
    {
        AccountModel taiKhoan = banHangDAO.getTaiKhoanRegisterByEmail(username, email);

        if (taiKhoan == null)
        {
            Toast.makeText(ForgotPasswordActivity.this, "Không tồn tại tài khoản", Toast.LENGTH_SHORT).show();
        }
        else {

            AccountModel model = banHangDAO.getTaiKhoanRegister(username);
            model.setPassword("123456");
            banHangDAO.updateAccount(model);

            Toast.makeText(ForgotPasswordActivity.this, "Mật khẩu của bạn là 123456", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            finish();
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