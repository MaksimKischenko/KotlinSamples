package com.example.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.fragmentexample.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment() {
    private val dataModel : DataModel by activityViewModels()
    lateinit var binding : FragmentBlank2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlank2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.message.observe(activity as LifecycleOwner, {
            binding.fr2Message.text = it
        })

        dataModel.messageForFragment.observe(activity as LifecycleOwner, {
            binding.fr2Message.text = it
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment2()
    }
}