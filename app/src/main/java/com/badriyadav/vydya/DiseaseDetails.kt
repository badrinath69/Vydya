package com.badriyadav.vydya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_disease_details.*

class DiseaseDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_details)
        val li= arrayOf<String>("hello","badri","how","are","you")
        var newli:List<String>?=null
        for (i in li.indices){
            //newli!!.plus("$i\n")
            tv_list.append("${i+1} - "+li[i])
            tv_list.append("\n")
        }
    }
}