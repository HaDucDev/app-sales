package hcmute.edu.vn.mssv18110273.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.edu.vn.mssv18110273.appbanhang.R;
import hcmute.edu.vn.mssv18110273.appbanhang.model.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final Context mContext;
    private final List<CategoryModel> arrSanPham;
    private final OnClickCategoryListener listener;

    public interface OnClickCategoryListener {
        void onClickItem(CategoryModel categoryModel);
    }


    public CategoryAdapter(Context mContext,
                           List<CategoryModel> arrSanPham,
                           OnClickCategoryListener listener) {
        this.mContext = mContext;
        this.arrSanPham = arrSanPham;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel item = arrSanPham.get(position);
        holder.tvName.setText(item.getName());
        holder.tvDesc.setText(item.getDescription());
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.ic_app)
                .error(R.drawable.ic_app).into(holder.imvImage);

        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        if (arrSanPham != null) {
            return arrSanPham.size();
        } else {
            return 0;
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName, tvDesc;
        protected ImageView imvImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameCategoryItem);
            tvDesc = itemView.findViewById(R.id.tvDescCategoryItem);
            imvImage = itemView.findViewById(R.id.imvImageCategoryItem);
        }

        public void bind(final CategoryModel categoryModel, final OnClickCategoryListener listener) {
            itemView.setOnClickListener(v -> listener.onClickItem(categoryModel));
        }
    }
}
