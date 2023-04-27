package uz.akbar.carea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.load
import uz.akbar.carea.dataClass.CarsData
import uz.akbar.carea.databinding.FragmentCarDetailsBinding


private const val ARG_PARAM1 = "param1"
class CarDetailsFragment : Fragment() {
    private var param1: CarsData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as CarsData

        }
    }
    lateinit var binding: FragmentCarDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarDetailsBinding.inflate(inflater, container, false)

        //Related to implemented car details
        binding.imageCarDetails.setImageResource(param1!!.carrasm)
        binding.nameCarDetails.text = param1!!.carname
        binding.reytingCarDetails.text = param1!!.carreyting
        binding.priceamountCarDetails.text = param1!!.carprice






        //Related to return to HomeFragment
        binding.backFromCarDetails.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, MainFragment())
                .commit()
        }

        binding.makeofferCarDetails.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, CheckoutFragment.newInstance(param1!!))
                .commit()
        }
        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: CarsData) =
            CarDetailsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1,param1)

                }
            }
    }
}


