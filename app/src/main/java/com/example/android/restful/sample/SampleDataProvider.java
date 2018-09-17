package com.example.android.restful.sample;

import com.example.android.restful.model.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public  static List<DataItem> dataItemList;
    public static Map<String, DataItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>();
        dataItemMap = new HashMap<>();

        addItem(new DataItem(null, "salad", "salads","our Quinoa", 1, 12, "quinoa_salad.jpg"));
    }
}
