package uz.akbar.carea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.akbar.carea.databinding.FragmentMakeOfferBinding

class MakeOfferFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMakeOfferBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeOfferBinding.inflate(inflater,container,false)
//        binding.offer.setOnClickListener {
//            findNavController().navigate(R.id.action_makeOfferFragment_to_offerProcessFragment)
//        }


        return binding.root
    }

//    private fun loadNumbers() {
//        binding.one.setOnClickListener(this)
//        binding.two.setOnClickListener(this)
//        binding.three.setOnClickListener(this)
//        binding.four.setOnClickListener(this)
//        binding.five.setOnClickListener(this)
//        binding.six.setOnClickListener(this)
//        binding.seven.setOnClickListener(this)
//        binding.eight.setOnClickListener(this)
//        binding.nine.setOnClickListener(this)
//        binding.zero.setOnClickListener(this)
//    }

    override fun onClick(p0: View?) {

    }
}