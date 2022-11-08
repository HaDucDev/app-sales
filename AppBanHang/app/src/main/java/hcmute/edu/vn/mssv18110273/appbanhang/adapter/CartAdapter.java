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
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemInCartModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.StringFormatUtils;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemCartViewHolder>
{

    public interface onClickListener {
        void onClickDelete(ItemInCartModel item);

        void onPlusClick(ItemInCartModel item);

        void onMinusClick(ItemInCartModel item);
    }

    private final Context mContext;
    private final onClickListener listener;
    private final List<ItemInCartModel> arrItems;

    public CartAdapter(Context mContext, List<ItemInCartModel> arrItems, onClickListener listener) {
        this.mContext = mContext;
        this.arrItems = arrItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_item_cart, parent, false);
        return new ItemCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCartViewHolder holder, int position) {
        ItemInCartModel item = arrItems.get(position);
        holder.tvQuantityItemCart.setText(String.valueOf(item.getSoLuong()));
        holder.tvItemNameCart.setText(item.getItemModel().getName());
        holder.tvTotalPriceCart.setText(StringFormatUtils.convertToStringMoneyVND(item.getTongTien()));
        holder.tvOutPriceCart.setText("Giá: " + StringFormatUtils.convertToStringMoneyVND(item.getGiaBan()));
        Picasso.get().load(item.getItemModel().getImageUrl()).placeholder(R.drawable.ic_app)
                .error(R.drawable.ic_app).into(holder.imvImage);

        holder.bind(arrItems.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return arrItems.size();
    }

    public static class ItemCartViewHolder extends RecyclerView.ViewHolder {
        protected Button btnDelete;
        protected ImageView imvImage;
        protected ImageView btnPlus, btnMinus;
        protected TextView tvItemNameCart, tvTotalPriceCart, tvOutPriceCart, tvQuantityItemCart;

        public ItemCartViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPlus = itemView.findViewById(R.id.btnPlusItemCart);
            btnMinus = itemView.findViewById(R.id.btnMinItemCart);
            btnDelete = itemView.findViewById(R.id.btnXoaItemCart);
            imvImage = itemView.findViewById(R.id.imvImageItemCart);
            tvItemNameCart = itemView.findViewById(R.id.tvNameItemCart);
            tvOutPriceCart = itemView.findViewById(R.id.tvOutPriceItemCart);
            tvQuantityItemCart = itemView.findViewById(R.id.tvQuantityItemCart);
            tvTotalPriceCart = itemView.findViewById(R.id.tvTotalPriceItemCart);
        }

        public void bind(final ItemInCartModel itemInCart, final onClickListener listener) {
            btnDelete.setOnClickListener(v -> {
                listener.onClickDelete(itemInCart);
            });
            btnPlus.setOnClickListener(view -> {
                if(itemInCart.getSoLuong() >= itemInCart.getItemModel().getQuantity())
                {
                    // neu san pham het hang thi khong cho them. Den so luong co o gio la toi da
                }
                else {
                    long num = itemInCart.getSoLuong() + 1;
                    long total = num * itemInCart.getGiaBan();

                    itemInCart.setSoLuong(num);
                    itemInCart.setTongTien(total);

                    tvQuantityItemCart.setText(String.valueOf(itemInCart.getSoLuong()));
                    tvTotalPriceCart.setText(StringFormatUtils.convertToStringMoneyVND(itemInCart.getTongTien()));

                    listener.onPlusClick(itemInCart);
                }
            });

            btnMinus.setOnClickListener(view -> {
                if (itemInCart.getSoLuong() > 1) {
                    long num = itemInCart.getSoLuong() - 1;
                    long total = num * itemInCart.getGiaBan();

                    itemInCart.setSoLuong(num);
                    itemInCart.setTongTien(total);

                    tvQuantityItemCart.setText(String.valueOf(itemInCart.getSoLuong()));
                    tvTotalPriceCart.setText(StringFormatUtils.convertToStringMoneyVND(itemInCart.getTongTien()));

                }
                listener.onMinusClick(itemInCart);
            });
        }
    }
}