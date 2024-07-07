package com.badriyadav.vydya

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.badriyadav.vydya.fragment.Details
import com.badriyadav.vydya.fragment.DeveloperFragment
import com.badriyadav.vydya.fragment.HealthFragment
import com.badriyadav.vydya.fragment.InfoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),Communicator {
    //private val bottom_nav=
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val infoFrag=InfoFragment()
        val healthFrag=HealthFragment()
        val devFrag=DeveloperFragment()
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//            window.navigationBarColor=resources.getColor(R.color.green)
//             }
        showFragment(infoFrag)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_info->showFragment(infoFrag)
                R.id.ic_health->showFragment(healthFrag)
                R.id.ic_developer->showFragment(devFrag)
            }
            true
        }

    }
    private fun showFragment(frag: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_main,frag)
            commit()
        }


    override fun passData(
        diseaseName: String,
        cause: ArrayList<String>,
        picurl: String,
        picurl2: String,
        preventions: ArrayList<String>,
        tablets: ArrayList<String>
    ) {
        val bundle = Bundle()
        bundle.putString("diseasename",diseaseName)
        bundle.putStringArrayList("cause", cause)
        bundle.putString("picurl",picurl)
        bundle.putString("picurl2",picurl2)
        bundle.putStringArrayList("preventions", preventions)
        bundle.putStringArrayList("tablets", tablets)
        val transaction = this.supportFragmentManager.beginTransaction()
        val detailFrag = Details()
        detailFrag.arguments = bundle
        transaction.replace(R.id.fl_main, detailFrag)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}