package com.example.a3010

import android.content.Context
import org.json.JSONObject
import java.io.InputStreamReader

class AddressHelper(context: Context) {

    private val data: JSONObject

    init {
        // Mở file JSON từ resources
        val inputStream = context.resources.openRawResource(R.raw.tree)
        val reader = InputStreamReader(inputStream)
        val content = reader.readText()
        reader.close()

        data = JSONObject(content)
    }

    fun getProvinces(): List<String> {
        val list = mutableListOf<String>()
        val keys = data.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            list.add(data.getJSONObject(key).getString("name"))
        }
        return list
    }

    fun getDistricts(province: String): List<String> {
        val list = mutableListOf<String>()
        var jProvince: JSONObject? = null

        val keys = data.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            if (data.getJSONObject(key).getString("name").equals(province)) {
                jProvince = data.getJSONObject(key)
                break
            }
        }

        if (jProvince != null) {
            val jDistricts = jProvince.getJSONObject("quan-huyen")
            val districtKeys = jDistricts.keys()
            while (districtKeys.hasNext()) {
                val districtKey = districtKeys.next()
                list.add(jDistricts.getJSONObject(districtKey).getString("name"))
            }
        }

        return list
    }

    fun getWards(province: String, district: String): List<String> {
        val list = mutableListOf<String>()
        var jProvince: JSONObject? = null
        var jDistrict: JSONObject? = null

        val keys = data.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            if (data.getJSONObject(key).getString("name").equals(province)) {
                jProvince = data.getJSONObject(key)
                break
            }
        }

        if (jProvince != null) {
            val jDistricts = jProvince.getJSONObject("quan-huyen")
            val districtKeys = jDistricts.keys()
            while (districtKeys.hasNext()) {
                val districtKey = districtKeys.next()
                if (jDistricts.getJSONObject(districtKey).getString("name").equals(district)) {
                    jDistrict = jDistricts.getJSONObject(districtKey)
                    break
                }
            }
        }

        if (jDistrict != null) {
            val jWards = jDistrict.getJSONObject("xa-phuong")
            val wardKeys = jWards.keys()
            while (wardKeys.hasNext()) {
                val wardKey = wardKeys.next()
                list.add(jWards.getJSONObject(wardKey).getString("name"))
            }
        }

        return list
    }
}
