package com.example.listviewtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView cityListView;
    private List<String> cities = new ArrayList<>();

    private class CityAdapter extends ArrayAdapter<City>{

        public CityAdapter(@NonNull Context context, ArrayList<City> cities) {
            super(context, 0, cities);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // 找到哪一筆資料
            City city = getItem(position);

            // 設定要塞入的樣式
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_information,parent,false);

            }
            // 將資料塞入設計好的樣式
            TextView tvName = (TextView) convertView.findViewById(R.id.cityName);
            TextView tvZipCode = (TextView) convertView.findViewById(R.id.zipCode);

            tvName.setText(city.name);
            tvZipCode.setText(String.valueOf(city.zipCode));

            return convertView;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        ListView lv = (ListView)findViewById(R.id.cityListView);
//        lv.setOnItemClickListener(this);
        cityListView = (ListView) findViewById(R.id.cityListView);
        setCities();
        cityListView.setOnItemClickListener(this);
    }

    public void setCities(){
//        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
//        cities.add("花蓮縣");
//        cities.add("彰化縣");
//        cities.add("澎湖縣");
//        cities.add("新竹市");
//        // new ArrayAdapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cities);
//        cityListView.setAdapter(adapter);

        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(new City("台北市",100));
        cityList.add(new City("新北市",207));
        cityList.add(new City("台中市",404));
        CityAdapter adapter = new CityAdapter(this,cityList);
        ListView cityListView = (ListView)findViewById(R.id.cityListView);
        cityListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView  tv = (TextView) findViewById(R.id.textView);
//        String[] citiesArray = getResources().getStringArray(R.array.cities);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("城市").setMessage("您選擇的是:" + cities.get(i)).setCancelable(true);
        dialog.setPositiveButton("確定", null);
        dialog.setNeutralButton("取消",null);
        dialog.setNegativeButton("放棄", null);
        dialog.show();

//        dialog.setTitle("城市");
//        dialog.setMessage("您選擇的是:" + citiesArray[i]);
//        dialog.setCancelable(true);
//        dialog.show();

//         Toast.makeText(this,"您選擇的是:" + citiesArray[i],Toast.LENGTH_LONG).show();

//        tv.setText("您選擇的是:" + citiesArray[i]);
    }
}