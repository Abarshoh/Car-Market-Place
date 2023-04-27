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
import uz.akbar.carea.databinding.FragmentOrdersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrdersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrdersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var selectedcars:MutableList<CarsData>
    lateinit var mySharedPreferences : MySharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mySharedPreferences = MySharedPreference.newInstance(requireContext())
        selectedcars = mutableListOf()
        val binding = FragmentOrdersBinding.inflate(inflater,container,false)
        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        var ordersJson = shared.getString("orders", null)
        var ordered = gson.fromJson<ArrayList<CarsData>>(ordersJson, object : TypeToken<ArrayList<CarsData>>() {}.type)

        binding.orderedElemnts.adapter = CarsAdapter(selectedcars, object : CarsAdapter.OnPressed{
            override fun onPressed(carsData: CarsData) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(carsData))
                    .commit()
            }
        }, object : CarsAdapter.OnSelected{
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
        binding.orderedElemnts.layoutManager = layoutManager
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrdersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrdersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}