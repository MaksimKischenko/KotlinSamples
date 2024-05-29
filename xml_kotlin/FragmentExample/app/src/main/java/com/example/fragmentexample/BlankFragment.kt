package com.example.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fragmentexample.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    private  val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toFrag2.setOnClickListener {
            dataModel.messageForFragment.value = "Hello from fragment1"
        }
        binding.toActivity.setOnClickListener {
            dataModel.message.value = "Hello activity from fragment1"
        }
        super.onViewCreated(view, savedInstanceState)
    }

    //SingleTon - создается определенный обьект и далее подставляется
    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}