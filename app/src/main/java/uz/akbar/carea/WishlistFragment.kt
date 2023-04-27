package uz.akbar.carea

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.akbar.carea.adapters.CarsAdapter
import uz.akbar.carea.dataClass.CarsData
import uz.akbar.carea.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    var wishList = mutableListOf<CarsData>()
    lateinit var list: MutableList<CarsData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mySharedPreference = MySharedPreference.newInstance(requireContext())
        list = mySharedPreference.getCarList()
        val binding = FragmentWishlistBinding.inflate(inflater,container,false)

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, MainFragment())
                .commit()
        }

        val carsAdapter = CarsAdapter(wishList, object : CarsAdapter.OnPressed{
            override fun onPressed(carsData: CarsData) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(carsData))
                    .commit()
            }
        }, object : CarsAdapter.OnSelected{
            override fun onSelect(carsData: CarsData, list: MutableList<CarsData>) {
                if (carsData.status){
                    wishList.add(carsData)

                }else{
                    wishList.remove(carsData)
                }
                mySharedPreference.setCarsData(list)
            }
        })
        getWishList()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.carsrecyclerview.layoutManager = layoutManager
        binding.carsrecyclerview.adapter = carsAdapter



        return binding.root
    }

    private fun getWishList() {
        for (i in list){
            if (i.status){
                wishList.add(i)
            }
        }
    }

}