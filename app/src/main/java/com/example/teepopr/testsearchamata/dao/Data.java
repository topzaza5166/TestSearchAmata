
package com.example.teepopr.testsearchamata.dao;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("mapSearch")
    @Expose
    private List<MapSearch> mapSearch = null;

    public List<MapSearch> getMapSearch() {
        return mapSearch;
    }

    public void setMapSearch(List<MapSearch> mapSearch) {
        this.mapSearch = mapSearch;
    }

}
