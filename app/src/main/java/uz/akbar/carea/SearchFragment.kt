package uz.akbar.carea

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.akbar.carea.adapters.CarsAdapter
import uz.akbar.carea.dataClass.CarsData
import uz.akbar.carea.databinding.FragmentSearchBinding

private const val ARG_PARAM1 = "param1"



class SearchFragment : Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }
    lateinit var mySharedPreferences : MySharedPreference

    lateinit var selectedcars:MutableList<CarsData>
    lateinit var carsAdapter: CarsAdapter
    lateinit var listofcars: MutableList<CarsData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mySharedPreferences = MySharedPreference.newInstance(requireContext())
        selectedcars = mutableListOf()
        val binding = FragmentSearchBinding.inflate(inflater,container,false)
        listofcars = mutableListOf()
        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        var carsJson = shared.getString("cars", null)
        var cars = gson.fromJson<ArrayList<CarsData>>(carsJson, object : TypeToken<ArrayList<CarsData>>() {}.type)

        binding.searchSearch.addTextChangedListener {
            if (binding.searchSearch.text.toString().isNotEmpty()) {
                var filterCars: ArrayList<CarsData> = ArrayList()
                for (i in cars) {
                    if (i.carname.lowercase().trim().contains(binding.searchSearch.text.toString().lowercase().trim())) {
                        filterCars.add(i)
                    }
                }
                binding.foundElementsSearch.adapter = CarsAdapter(filterCars, object : CarsAdapter.OnPressed{
                    override fun onPressed(carsData: CarsData) {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(carsData))
                            .commit()
                    }
                }, object :CarsAdapter.OnSelected{
                    override fun onSelect(carsData: CarsData, list: MutableList<CarsData>) {
                        if (carsData.status){
                            selectedcars.add(carsData)
                        }else{
                            selectedcars.remove(carsData)
                        }
                        mySharedPreferences.setCarsData(list)
                    }
                })
                val layoutManager = GridLayoutManager(requireContext(), 2)
                binding.foundElementsSearch.layoutManager = layoutManager
            } else {
                binding.foundElementsSearch.adapter = CarsAdapter(cars, object : CarsAdapter.OnPressed{
                    override fun onPressed(carsData: CarsData) {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(carsData))
                            .commit()
                    }
                }, object :CarsAdapter.OnSelected{
                    override fun onSelect(carsData: CarsData, list: MutableList<CarsData>) {
                        if (carsData.status){
                            selectedcars.add(carsData)
                        }else{
                            selectedcars.remove(carsData)
                        }
                        mySharedPreferences.setCarsData(list)
                    }
                })

            }
        }


        binding.searchSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.searchSearch.clearFocus()
            }
            false
        }

//        binding.searchSearch.addTextChangedListener{
//            val carfilter = mutableListOf<CarsData>()
//            Log.d("SSS", "onCreateView: ${it.toString()}")
//            if (it!!.length>0 && it!=null){
//                for (i in 0..listofcars.size-1){
//                    if (listofcars[i].carname.trim().toUpperCase().contains(it.trim().toString().toUpperCase())){
//                        carfilter.add(listofcars[i])
//                    }
//                }
//                carsAdapter = CarsAdapter(carfilter, object : CarsAdapter.OnPressed{
//                    override fun onPressed(carsData: CarsData) {
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(carsData))
//                            .commit()
//                    }
//                }, object :CarsAdapter.OnSelected{
//                    override fun onSelect(carsData: CarsData) {
//                        if (carsData.status){
//                            selectedcars.add(carsData)
//                        }else{
//                            selectedcars.remove(carsData)
//                        }
//                    }
//                })
//                val layoutManager = GridLayoutManager(requireContext(), 2)
//                binding.foundElementsSearch.layoutManager = layoutManager
//                if (RefreshSearch(it.toString())>0){
//                    binding.foundElementsSearch.adapter=carsAdapter
//                }
//            }else{
//                binding.foundSearch.visibility = View.GONE
//                binding.searchresultSearch.visibility =View.VISIBLE
//            }
//
//        }


        return binding.root
    }
//    private fun RefreshSearch(s:String):Int{
//        if (s.length<1){
//            return 0
//        }
//        return s.length
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}