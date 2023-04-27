package uz.akbar.carea

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.akbar.carea.dataClass.CarsData

class MySharedPreference private constructor(contexT: Context) {
    val sharedPreferences = contexT.getSharedPreferences("data", 0)
    val edit = sharedPreferences.edit()
    val gson = Gson()

    companion object{
        private var instance:MySharedPreference? = null
        fun newInstance(contexT: Context): MySharedPreference {
            if (instance == null){
                instance = MySharedPreference(contexT)
            }
            return instance!!
        }
    }

    fun getCarList(): MutableList<CarsData>{
        val data: String = sharedPreferences.getString("carsList", "")!!
        if (data == "") {
            return mutableListOf()
        }
        val typeToken = object :  TypeToken<MutableList<CarsData>>() {}.type
        return gson.fromJson(data, typeToken)
    }

    fun setCarsData(mutableList: MutableList<CarsData>) {
        edit.putString("carsList", gson.toJson(mutableList)).apply()
    }

}