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

import hcmute.edu.vn.mssv18110273.appbanhang.adapter.CategoryAdapter;
import hcmute.edu.vn.mssv18110273.appbanhang.dao.BanHangDAO;
import hcmute.edu.vn.mssv18110273.appbanhang.database.BanHangDatabase;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.Const;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView rcvCategory;
    private CategoryAdapter adapter;
    private List<CategoryModel> categoryModelList;

    private BanHangDAO banHangDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Loại sản phẩm");
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        banHangDAO = BanHangDatabase.getInstance(CategoryActivity.this).banHangDAO();
    }

    private void initView() {
        rcvCategory = this.findViewById(R.id.rcvCategoryType);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.setLayoutManager(layoutManager);
    }

    private void initAdapter() {
        categoryModelList = new ArrayList<>();
        categoryModelList.addAll(banHangDAO.findAllCategories());
        adapter = new CategoryAdapter(CategoryActivity.this, categoryModelList, categoryModel -> {
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(Const.KeyBundle.CATEGORY_BUNDLE, categoryModel);

            Intent mIntent = new Intent(CategoryActivity.this, ItemActivity.class);
            mIntent.putExtras(mBundle);
            startActivity(mIntent);
        });
        rcvCategory.setAdapter(adapter);
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
