package hcmute.edu.vn.mssv18110273.appbanhang.model;

import android.os.Parcel;
import android.os.Parcelable;

// gio hang
public class ItemInCartModel implements Parcelable
{

    private Long id;

    private ItemModel itemModel;

    private Long giaBan;

    private Long soLuong;

    private Long tongTien;

    public ItemInCartModel()
    {
    }

    protected ItemInCartModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            giaBan = null;
        } else {
            giaBan = in.readLong();
        }
        if (in.readByte() == 0) {
            soLuong = null;
        } else {
            soLuong = in.readLong();
        }
        if (in.readByte() == 0) {
            tongTien = null;
        } else {
            tongTien = in.readLong();
        }
    }

    public static final Creator<ItemInCartModel> CREATOR = new Creator<ItemInCartModel>() {
        @Override
        public ItemInCartModel createFromParcel(Parcel in) {
            return new ItemInCartModel(in);
        }

        @Override
        public ItemInCartModel[] newArray(int size) {
            return new ItemInCartModel[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }

    public Long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Long giaBan) {
        this.giaBan = giaBan;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Long getTongTien() {
        return tongTien;
    }

    public void setTongTien(Long tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (giaBan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(giaBan);
        }
        if (soLuong == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(soLuong);
        }
        if (tongTien == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(tongTien);
        }
    }
}
