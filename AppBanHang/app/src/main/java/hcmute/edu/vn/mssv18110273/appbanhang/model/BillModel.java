package hcmute.edu.vn.mssv18110273.appbanhang.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "bill")// hoa don
public class BillModel implements Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "total_price")
    private Long totalPrice;

    @ColumnInfo(name = "created_date")
    private String createdDate;

    @ColumnInfo(name = "name_customer")
    private String nameCustomer;

    @ColumnInfo(name = "address_customer")
    private String addressCustomer;

    @ColumnInfo(name = "phone_customer")
    private String phoneCustomer;

    @ColumnInfo(name = "payment_type")
    private String paymentType;


    public BillModel() {
    }

    protected BillModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            totalPrice = null;
        } else {
            totalPrice = in.readLong();
        }
        createdDate = in.readString();
        nameCustomer = in.readString();
        addressCustomer = in.readString();
        phoneCustomer = in.readString();
        paymentType = in.readString();
    }

    public static final Creator<BillModel> CREATOR = new Creator<BillModel>() {
        @Override
        public BillModel createFromParcel(Parcel in) {
            return new BillModel(in);
        }

        @Override
        public BillModel[] newArray(int size) {
            return new BillModel[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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
        if (totalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(totalPrice);
        }
        dest.writeString(createdDate);
        dest.writeString(nameCustomer);
        dest.writeString(addressCustomer);
        dest.writeString(phoneCustomer);
        dest.writeString(paymentType);
    }
}
