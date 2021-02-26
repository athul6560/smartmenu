package com.andria.qrscan.Activities.Model;

import java.util.List;

public class ItemModel {

    public int Expire;
    public List<ItemDetailModel> Items;

    public int getExpire() {
        return Expire;
    }

    public void setExpire(int expire) {
        Expire = expire;
    }

    public List<ItemDetailModel> getItems() {
        return Items;
    }

    public void setItems(List<ItemDetailModel> items) {
        Items = items;
    }
}
