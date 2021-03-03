package com.andria.qrscan.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andria.qrscan.Adapter.CategoryListAdapter;
import com.andria.qrscan.Adapter.ItemListAdapter;
import com.andria.qrscan.Model.ItemDetailModel;
import com.andria.qrscan.Model.categoryModel;
import com.andria.qrscan.R;
import com.andria.qrscan.Utils.SmartMenuUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DashboardActivity extends AppCompatActivity {
    ItemDetailModel item;

    RecyclerView rl_category, rl_detail_cat;
    CategoryListAdapter categoryListAdapter;
    ItemListAdapter itemListAdapter;
    private ImageView qrBtn;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        rl_category = findViewById(R.id.rl_category);
        qrBtn = findViewById(R.id.qr_btn);
        rl_detail_cat = findViewById(R.id.rl_detail_cat);
        search = findViewById(R.id.search_Edittetxt);
        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, QRActivity.class));
                finish();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }



        });
        setCategoryRecycler();
        setItemRecycler(getfullJson());


    }

    private void setItemRecycler(List<ItemDetailModel> itemDetailModels) {
        itemListAdapter = new ItemListAdapter(DashboardActivity.this,itemDetailModels);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        rl_detail_cat.setLayoutManager(mLayoutManager1);
        rl_detail_cat.setItemAnimator(new DefaultItemAnimator());
        rl_detail_cat.setAdapter(itemListAdapter);
    }
    void filter(String text){
        List<ItemDetailModel> temp = new ArrayList();
        for(ItemDetailModel d: getfullJson()){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(d.getDishName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        itemListAdapter.updateList(temp);
    }

    private void setCategoryRecycler() {
       // categoryListAdapter = new CategoryListAdapter(DashboardActivity.this, sortdataforcategory(getfullJson()));
        categoryListAdapter=new CategoryListAdapter(this,sortdataforcategory(getfullJson()), new CategoryListAdapter.OnCOAAccountClickListener() {
            @Override
            public void onClicked(categoryModel account) {
                List<ItemDetailModel> listItem=  getcategoryitemList(account.getCname());
                setItemRecycler(listItem);
            }


        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rl_category.setLayoutManager(mLayoutManager);
        rl_category.setItemAnimator(new DefaultItemAnimator());
        rl_category.setAdapter(categoryListAdapter);
    }

    private List<ItemDetailModel> getcategoryitemList(String cname) {
        List<ItemDetailModel> list= new ArrayList<>();
        for(int i=0;i<getfullJson().size();i++){
            if(getfullJson().get(i).getCategory().equals(cname)){
                list.add(getfullJson().get(i));
            }
        }
        return list;
    }

    private List<ItemDetailModel> getfullJson() {
        List<ItemDetailModel> itemList = new ArrayList<>();
        String json = SmartMenuUtil.getItem(DashboardActivity.this);
        String formattedjson = json.replaceAll("\\s", "");

        try {
            JSONObject mainObject = new JSONObject(formattedjson);
            JSONArray array = mainObject.getJSONArray("Items");
            System.out.println("itemlength" + array.length());

            for (int i = 0; i < array.length(); i++) {
                item = new ItemDetailModel();

                JSONObject itemobject = array.getJSONObject(i);
                item.setCategory(itemobject.getString("category"));
                item.setDishName(itemobject.getString("dishName"));
                item.setImage_url(itemobject.getString("image_url"));
                item.setCategory_image(itemobject.getString("category_image"));
                item.setPrice(itemobject.getInt("price"));
                itemList.add(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    private List<categoryModel> sortdataforcategory(List<ItemDetailModel> getfullJson) {
        List<categoryModel> categoryModel = new ArrayList<>();
        for (int i = 0; i < getfullJson.size(); i++) {
            categoryModel item = new categoryModel(getfullJson.get(i).getCategory(), getfullJson.get(i).getCategory_image());

            categoryModel.add(item);
        }
        Set<categoryModel> s = new HashSet<categoryModel>();
        s.addAll(categoryModel);
        categoryModel.clear();
        categoryModel.addAll(s);


        return categoryModel;
    }


}