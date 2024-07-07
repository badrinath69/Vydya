package com.badriyadav.vydya.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.badriyadav.vydya.R
import com.badriyadav.vydya.databinding.FragmentDetailsBinding
import com.badriyadav.vydya.databinding.FragmentHealthBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_disease_details.*


class Details : Fragment() {


    private lateinit var binding: FragmentDetailsBinding
    var diseasename: String? = ""
    var cause: ArrayList<String>?= arrayListOf()
    var picurl:String?=""
    var picurl2:String?=""
    var preventions:ArrayList<String>?=arrayListOf()
    var tablets:ArrayList<String>?=arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn: Button = requireActivity().findViewById(R.id.backbt)
        val healthFrag = HealthFragment()
        diseasename = arguments?.getString("diseasename")
        cause=arguments?.getStringArrayList("cause")
        picurl=arguments?.getString("picurl")
        picurl2=arguments?.getString("picurl2")
        preventions=arguments?.getStringArrayList("preventions")
        tablets= arguments?.getStringArrayList("tablets")
        binding.iddiseasename.text=diseasename
        Glide.with(activity?.applicationContext!!).load(picurl).into(binding.img1)
        Glide.with(activity?.applicationContext!!).load(picurl2).into(binding.img2)
        for (i in cause!!.indices){
            //newli!!.plus("$i\n")
            binding.idcause.append("${i+1} - "+ cause!![i])
            binding.idcause.append("\n")
        }
        for (i in preventions!!.indices){
            //newli!!.plus("$i\n")
            binding.preventions.append("${i+1} - "+ preventions!![i])
            binding.preventions.append("\n")
        }
        for (i in tablets!!.indices){
            //newli!!.plus("$i\n")
            binding.tablets.append("${i+1} - "+ tablets!![i])
            binding.tablets.append("\n")
        }
        btn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_main, healthFrag)
                commit()
            }
        }

    }
}