package uz.akbar.carea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import uz.akbar.carea.adapters.OfferCarsAdapter
import uz.akbar.carea.dataClass.OfferCarsInfo
import uz.akbar.carea.databinding.FragmentSpecialOffersBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SpecialOffersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var binding: FragmentSpecialOffersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpecialOffersBinding.inflate(inflater, container, false)

        val offerCarsAdapter = OfferCarsAdapter(Cars())
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.SPRecycler.layoutManager = layoutManager
        binding.SPRecycler.adapter = offerCarsAdapter

        binding.backFromSpecialOffers.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, HomeFragment())
                .commit()
        }
        return binding.root
    }
    private fun Cars(): MutableList<OfferCarsInfo> {
        val list = mutableListOf<OfferCarsInfo>()
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        list.add(OfferCarsInfo("20%", "Week Deals!", "Get a new car discount, only valid this week", R.drawable.mercedescar))
        return list
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpecialOffersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpecialOffersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}