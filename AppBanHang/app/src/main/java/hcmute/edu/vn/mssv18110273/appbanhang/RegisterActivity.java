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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegister;
    private EditText edtUsername, edtPassword, edtFullName, edtPhone, edtEmail, edtConfirmPass,edtAddress;

    private BanHangDAO banHangDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng ký tài khoản");
        initView();
        initData();
    }

    private void initData() {
        banHangDAO = BanHangDatabase.getInstance(RegisterActivity.this).banHangDAO();
    }

    private void initView() {
        edtUsername = this.findViewById(R.id.edtUserRegister);
        edtPassword = this.findViewById(R.id.edtPassRegister);
        edtFullName = this.findViewById(R.id.edtFullNameRegister);
        edtPhone = this.findViewById(R.id.edtPhoneRegister);
        edtEmail = this.findViewById(R.id.edtEmailRegister);
        edtConfirmPass = this.findViewById(R.id.edtConfirmPassRegister);
        edtAddress=this.findViewById(R.id.edtAddressRegister);
        btnRegister = this.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegister) {
            onClickRegister();
        }
    }

    private void onClickRegister() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString().trim();
        String fullName = edtFullName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        String confirmPass = edtConfirmPass.getText().toString();
        String address=edtAddress.getText().toString();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPass)) {
            Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6 ){
            Toast.makeText(getApplicationContext(),"Mật khẩu ít nhất phải có 6 kí tự",Toast.LENGTH_SHORT).show();
            return;
        }
        if(username.length() < 6 ){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập ít nhất phải có 6 kí tự",Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.length() < 11 ){
            Toast.makeText(getApplicationContext(),"Số điệm thoại ít nhất 10 số ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.contains("@")== false || email.contains(".")== false){
            Toast.makeText(getApplicationContext(),"Gmail sai định dạng",Toast.LENGTH_SHORT).show();
            return;
        }
        if(fullName.length() < 6 ){
            Toast.makeText(getApplicationContext(),"Họ và tên ít nhất phải có 6 kí tự",Toast.LENGTH_SHORT).show();
            return;
        }

        checkRegister(username, password, fullName, phone, email,address);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    private void checkRegister(String username, String password, String fullName, String phone, String email, String address) {
        AccountModel taiKhoan = banHangDAO.getTaiKhoanRegister(username);
        if (taiKhoan == null) {
            AccountModel newAccount = new AccountModel();
            newAccount.setUsername(username);
            newAccount.setPassword(password);
            newAccount.setFullName(fullName);
            newAccount.setMobile(phone);
            newAccount.setEmail(email);
            newAccount.setAddress(address);

            banHangDAO.insertAccount(newAccount);
            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(mIntent);
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
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