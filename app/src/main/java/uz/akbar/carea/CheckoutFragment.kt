package uz.akbar.carea

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.akbar.carea.adapters.CarsAdapter
import uz.akbar.carea.dataClass.CarsData
import uz.akbar.carea.databinding.FragmentCheckoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


class CheckoutFragment : Fragment() {
    private var param1: CarsData? = null
    lateinit var ordered:MutableList<CarsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as CarsData
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ordered = mutableListOf()
        val binding = FragmentCheckoutBinding.inflate(inflater,container,false)
        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
        var carsJson = shared.getString("cars", null)
        var cars = gson.fromJson<ArrayList<CarsData>>(carsJson, object : TypeToken<ArrayList<CarsData>>() {}.type)

        binding.rasm.setImageResource(param1!!.carrasm)
        binding.name.text = param1!!.carname
        binding.price.text = param1!!.carprice

        for (item in cars){
            if (item.isOrdered == true){
                ordered.add(item)
            }
        }
//        ordered.add(cars.filter { it.isOrdered == true } as ArrayList<CarsData>)
//        ordered.add(param1!!)
//        ordered.add(cars.filter { it.isOrdered==true} as ArrayList<CarsData>)
        val ordesJson = gson.toJson(ordered)
        shared.edit().putString("orders", ordesJson).apply()


        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CarDetailsFragment.newInstance(param1!!))
                .commit()
        }
        binding.buy.setOnClickListener {
            val bindin1 = layoutInflater.inflate(R.layout.dialog_congrats,container,false)
            val myDialog = Dialog(requireContext())
            myDialog.setContentView(bindin1)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
            param1!!.isOrdered = true

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                myDialog.cancel()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MainFragment())
                    .commit()

            },2000)

        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: CarsData) =
            CheckoutFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1,param1)



                }
            }
    }
}




