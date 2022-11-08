package hcmute.edu.vn.mssv18110273.appbanhang.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item") // san pham: laptop dell i5, i7
public class ItemModel implements Serializable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @ColumnInfo(name = "category_id")
    private Long categoryId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private Long price;

    @ColumnInfo(name = "quantity")
    private Long quantity;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image_url")
    private String imageUrl;


    public ItemModel()
    {
    }

    protected ItemModel(Parcel in)
    {
        if (in.readByte() == 0)
        {
            id = null;
        }
        else
        {
            id = in.readLong();
        }
        if (in.readByte() == 0)
        {
            categoryId = null;
        }
        else
        {
            categoryId = in.readLong();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readLong();
        }
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readLong();
        }
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        if (id == null)// neu id bang rong thi ghi byte bang 0, nguoc lai byte bang 1 va ghi id bang long
        {
            dest.writeByte((byte) 0);
        } else
            {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(categoryId);
        }
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(price);
        }
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(quantity);
        }
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
}
