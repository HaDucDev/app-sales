package hcmute.edu.vn.mssv18110273.appbanhang.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;//kieu du lieu mang trong json
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import hcmute.edu.vn.mssv18110273.appbanhang.model.AccountModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemInCartModel;
import hcmute.edu.vn.mssv18110273.appbanhang.model.ItemModel;

public class MySharedPreferences
{
    private static final String MY_SHARE_PREFERENCES = "MySharedAppBanHang";
    private final Context mContext;

    public MySharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void clearAllData() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void clearDataByKey(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        preferences.edit().remove(key).apply();
    }

    public void putAccount(String key, AccountModel accountModel) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String value = gson.toJson(accountModel);
        editor.putString(key, value);
        editor.apply();
    }

    public AccountModel getAccount(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        return gson.fromJson(json, AccountModel.class);
    }

    public void putListItem(String key, List<ItemModel> listItems) {
        SharedPreferences pref = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new GsonBuilder().create();
        JsonArray array = gson.toJsonTree(listItems).getAsJsonArray();
        editor.putString(key, array.toString());
        editor.apply();
    }

    public List<ItemModel> getListItems(String key) {
        Gson gson = new Gson();
        List<ItemModel> listItems;
        SharedPreferences sharedPref = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(key, "");

        Type type = new TypeToken<List<ItemModel>>() {
        }.getType();
        listItems = gson.fromJson(jsonPreferences, type);

        return listItems;
    }

    public void putListItemInCart(String key, List<ItemInCartModel> listItems) {
        SharedPreferences pref = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new GsonBuilder().create();
        JsonArray array = gson.toJsonTree(listItems).getAsJsonArray();
        editor.putString(key, array.toString());
        editor.apply();
    }

    public List<ItemInCartModel> getListItemInCart(String key) {
        Gson gson = new Gson();
        List<ItemInCartModel> listItems;
        SharedPreferences sharedPref = mContext.getSharedPreferences(MY_SHARE_PREFERENCES, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(key, "");

        Type type = new TypeToken<List<ItemInCartModel>>() {
        }.getType();
        listItems = gson.fromJson(jsonPreferences, type);

        return listItems;
    }
}
