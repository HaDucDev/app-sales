package hcmute.edu.vn.mssv18110273.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.edu.vn.mssv18110273.appbanhang.R;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.StringFormatUtils;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final Context mContext;
    private final List<ItemModel> arrSanPham;
    private final onClickSanPhamListener listener;

    public interface onClickSanPhamListener {
        void onClickViewDetail(ItemModel item);

        void onClickAddCart(ItemModel item);
    }


    public ItemAdapter(Context mContext,
                       List<ItemModel> arrSanPham,
                       onClickSanPhamListener listener) {
        this.mContext = mContext;
        this.arrSanPham = arrSanPham;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_good, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel item = arrSanPham.get(position);
        holder.tvTen.setText(item.getName());
        holder.tvGia.setText(StringFormatUtils.convertToStringMoneyVND(item.getPrice()));
        Picasso.get().load(item.getImageUrl()).placeholder(R.drawable.ic_app)
                .error(R.drawable.ic_app).into(holder.imvImageItem);

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

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTen, tvGia;
        protected ImageView imvImageItem;
        protected Button btnViewDetail, btnAddCart;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvNameItem);
            tvGia = itemView.findViewById(R.id.tvPriceItem);
            btnAddCart = itemView.findViewById(R.id.btnCartItem);
            imvImageItem = itemView.findViewById(R.id.imvImageItem);
            btnViewDetail = itemView.findViewById(R.id.btnDetailItem);
        }

        public void bind(final ItemModel itemInCart, final onClickSanPhamListener listener) {
            btnViewDetail.setOnClickListener(v -> {
                listener.onClickViewDetail(itemInCart);
            });

            btnAddCart.setOnClickListener(v -> {
                listener.onClickAddCart(itemInCart);
            });
        }
    }
}