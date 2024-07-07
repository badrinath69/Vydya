package com.badriyadav.vydya.fragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badriyadav.vydya.Communicator
import com.badriyadav.vydya.R
import com.badriyadav.vydya.adapter.Rc1adapter
import com.badriyadav.vydya.data.model.FsData
import com.badriyadav.vydya.data.model.HealthData
import com.badriyadav.vydya.databinding.FragmentHealthBinding
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HealthFragment : Fragment() {

    private lateinit var communicator: Communicator
    private lateinit var binding: FragmentHealthBinding
    private val TAG: String = "HealthFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: Rc1adapter

    //private var dataList= mutableListOf<HealthData>()
        private var collectionModal: ArrayList<FsData> = arrayListOf()
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHealthBinding.inflate(inflater, container, false)
        //bindingSignIn.txtMessage.text="Sample Text"
        return binding.root
        // Inflate the layout for this fragment
        //loadData()
        val view = inflater.inflate(R.layout.fragment_health, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = activity as Communicator
        loadData()
        //Toast.makeText(activity, "Hello", Toast.LENGTH_LONG).show()
        recyclerView = view.findViewById(R.id.rc1recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        rvAdapter = Rc1adapter(requireActivity())
        recyclerView.adapter = rvAdapter
    }

    private fun loadData() {
        collectionModal.clear()
        binding.idProgressBar.visibility = View.VISIBLE
        binding.idloading.visibility=View.VISIBLE
        db.collection("collection").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("firestore error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        if (dc.document.toObject(FsData::class.java) !in collectionModal) {
                            collectionModal.add(dc.document.toObject(FsData::class.java))


                        }
                    }
                }
                Log.d("HealthFragment","$collectionModal")
                rvAdapter.setData(collectionModal)
                binding.idProgressBar.visibility = View.INVISIBLE
                binding.idloading.visibility = View.INVISIBLE
                rvAdapter.setOnItemClickListner(object : Rc1adapter.onItemClickListner {
                    override fun onItemClick(position: Int) {
                        communicator.passData(collectionModal[position].name.toString(),
                            collectionModal[position].cause as ArrayList<String>,
                            collectionModal[position].picurl.toString(),
                            collectionModal[position].picurl2.toString(),
                            collectionModal[position].preventions as ArrayList<String>,
                            collectionModal[position].tablets as ArrayList<String>,


                        )

                    }

                })
            }

        })
    }
}




