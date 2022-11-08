package hcmute.edu.vn.mssv18110273.appbanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.database.MySharedPreferences;
import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class UpdateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvUsername;
    private EditText edtPhone, edtEmail, edtFullName, edtAddress, edtPassword, edtConfirmPass;
    private Button btnUpdate;
    private BanHangDAO banHangDAO;

    private AccountModel accountModel;
    private MySharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin tài khoản");
        initView();
        initData();
    }


    private void initView() {
        tvUsername = this.findViewById(R.id.tvUserUpdate);
        edtEmail = this.findViewById(R.id.edtEmailUpdate);
        edtPhone = this.findViewById(R.id.edtPhoneUpdate);
        edtFullName = this.findViewById(R.id.edtFullNameUpdate);
        edtAddress = this.findViewById(R.id.edtAddressUpdate);
        edtPassword = this.findViewById(R.id.edtPassUpdate);
        edtConfirmPass = this.findViewById(R.id.edtConfirmPassUpdate);
        btnUpdate = this.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnUpdate) {
            onClickUpdate();
        }
    }


    private void onClickUpdate() {
        String username = tvUsername.getText().toString();
        String password = edtPassword.getText().toString().trim();
        String fullName = edtFullName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        String confirmPass = edtConfirmPass.getText().toString();
        String address = edtAddress.getText().toString();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            Toast.makeText(UpdateAccountActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPass)) {
            Toast.makeText(UpdateAccountActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Mật khẩu ít nhất phải có 6 kí tự", Toast.LENGTH_SHORT).show();
            return;
        }
        if (username.length() < 6) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập ít nhất phải có 6 kí tự", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() < 11) {
            Toast.makeText(getApplicationContext(), "Số điệm thoại ít nhất 10 số ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.contains("@") == false || email.contains(".") == false) {
            Toast.makeText(getApplicationContext(), "Gmail sai định dạng", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fullName.length() < 3) {
            Toast.makeText(getApplicationContext(), "Họ và tên ít nhất phải có 3 kí tự", Toast.LENGTH_SHORT).show();
            return;
        }

        checkSua(username, password, fullName, phone, email, address);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    private void checkSua(String username, String password, String fullName, String phone, String email, String address) {
        AccountModel newAccount = banHangDAO.getTaiKhoanRegister(username);
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setFullName(fullName);
        newAccount.setMobile(phone);
        newAccount.setEmail(email);
        newAccount.setAddress(address);
        banHangDAO.updateAccount(newAccount);

        preferences.putAccount(Const.KEY_SHARE_PREFERENCE.KEY_ACCOUNT, newAccount);

        Toast.makeText(UpdateAccountActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(UpdateAccountActivity.this, AccountDetailActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void initData() {
        banHangDAO = BanHangDatabase.getInstance(UpdateAccountActivity.this).banHangDAO();
        preferences = new MySharedPreferences(this);
        accountModel = preferences.getAccount(Const.KEY_SHARE_PREFERENCE.KEY_ACCOUNT);
        tvUsername.setText(accountModel.getUsername());
        edtEmail.setText(accountModel.getEmail());
        edtPhone.setText(accountModel.getMobile());
        edtFullName.setText(accountModel.getFullName());
        edtAddress.setText(accountModel.getAddress());
        edtPassword.setText(accountModel.getPassword());
        edtConfirmPass.setText(accountModel.getPassword());
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