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
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillDetailModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.StringFormatUtils;

public class BillDetailAdapter extends RecyclerView.Adapter<BillDetailAdapter.ItemChiTietViewHolder> {

    private final Context mContext;
    private final List<BillDetailModel> arrItems;

    public BillDetailAdapter(Context mContext, List<BillDetailModel> arrItems) {
        this.mContext = mContext;
        this.arrItems = arrItems;
    }

    @NonNull
    @Override
    public ItemChiTietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_bill_detail, parent, false);
        return new ItemChiTietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemChiTietViewHolder holder, int position) {
        BillDetailModel item = arrItems.get(position);
        holder.tvItemNameChiTietDonHang.setText(item.getItemName());
        holder.tvQuantityItemChiTietDonHang.setText("SL: " + item.getQuantity());
        holder.tvTotalPriceChiTietDonHang.setText(StringFormatUtils.convertToStringMoneyVND(item.getTotalPrice()));
        holder.tvOutPriceChiTietDonHang.setText("Giá: " + StringFormatUtils.convertToStringMoneyVND(item.getPrice()));
        Picasso.get().load(item.getItemImageUrl()).placeholder(R.drawable.ic_app)
                .error(R.drawable.ic_app).into(holder.imvImageChiTietDonHang);

    }

    @Override
    public int getItemCount() {
        return arrItems.size();
    }

    public static class ItemChiTietViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvItemNameChiTietDonHang, tvTotalPriceChiTietDonHang, tvOutPriceChiTietDonHang, tvQuantityItemChiTietDonHang;
        protected ImageView imvImageChiTietDonHang;

        public ItemChiTietViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemNameChiTietDonHang = itemView.findViewById(R.id.tvNameChiTietDonHang);
            tvOutPriceChiTietDonHang = itemView.findViewById(R.id.tvOutPriceChiTietDonHang);
            tvQuantityItemChiTietDonHang = itemView.findViewById(R.id.tvQuantityChiTietDonHang);
            tvTotalPriceChiTietDonHang = itemView.findViewById(R.id.tvTotalPriceChiTietDonHang);
            imvImageChiTietDonHang = itemView.findViewById(R.id.imvImageChiTietDonHang);
        }
    }
}
