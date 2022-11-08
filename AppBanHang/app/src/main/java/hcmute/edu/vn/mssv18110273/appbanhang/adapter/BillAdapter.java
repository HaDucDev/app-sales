package hcmute.edu.vn.mssv18110273.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.mssv18110273.appbanhang.R;
import hcmute.edu.vn.mssv18110273.appbanhang.model.BillModel;
import hcmute.edu.vn.mssv18110273.appbanhang.util.StringFormatUtils;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>
{
    private final Context mContext;
    private final List<BillModel> arrSanPham;
    private final BillAdapter.OnClickBillListener listener;

    public interface OnClickBillListener {
        void onClickItem(BillModel item);
    }

    public BillAdapter(Context mContext, List<BillModel> arrSanPham, OnClickBillListener listener) {
        this.mContext = mContext;
        this.arrSanPham = arrSanPham;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BillAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_bill, parent, false);
        return new BillAdapter.BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.BillViewHolder holder, int position)
    {
        BillModel item = arrSanPham.get(position);
        holder.tvId.setText("Mã đơn hàng: " + item.getId() + "");
        holder.tvTenKhachHangDonHang.setText("Khách hàng: " + item.getNameCustomer() + "");
        holder.tvSDTKhachHangDonHang.setText("SDT: " + item.getPhoneCustomer() + "");
        holder.tvDiaChiKHDonHang.setText("Địa chỉ: " + item.getAddressCustomer() + "");
        holder.tvCreatedDate.setText("Thời gian đặt: " + item.getCreatedDate());
        holder.tvTypeThanhToanDonHang.setText("Loại TT: " + item.getPaymentType());
        holder.tvPrice.setText("Tổng tiền: " + StringFormatUtils.convertToStringMoneyVND(item.getTotalPrice()));
        if (position % 2 == 0) {
            holder.rlvDonHangItem.setBackgroundColor(mContext.getResources().getColor(R.color.colorGachChanDam));
        }
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount()
    {
        if (arrSanPham != null) {
            return arrSanPham.size();
        } else {
            return 0;
        }
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvId, tvCreatedDate, tvPrice, tvTenKhachHangDonHang, tvDiaChiKHDonHang, tvSDTKhachHangDonHang, tvTypeThanhToanDonHang;
        protected RelativeLayout rlvDonHangItem;

        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvMaDonHang);
            tvPrice = itemView.findViewById(R.id.tvTotalPriceDonHang);
            tvCreatedDate = itemView.findViewById(R.id.tvDateDonHang);
            rlvDonHangItem = itemView.findViewById(R.id.rlvDonHangItem);
            tvTenKhachHangDonHang = itemView.findViewById(R.id.tvTenKhachHangDonHang);
            tvSDTKhachHangDonHang = itemView.findViewById(R.id.tvSDTKhachHangDonHang);
            tvDiaChiKHDonHang = itemView.findViewById(R.id.tvDiaChiKHDonHang);
            tvTypeThanhToanDonHang = itemView.findViewById(R.id.tvTypeThanhToanDonHang);
        }

        public void bind(final BillModel donHangEntity, final BillAdapter.OnClickBillListener listener)
        {
            itemView.setOnClickListener(v -> listener.onClickItem(donHangEntity));
        }
    }
}
