package com.badriyadav.vydya.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.badriyadav.vydya.R
import com.badriyadav.vydya.data.model.FsData
import com.badriyadav.vydya.data.model.HealthData
import com.bumptech.glide.Glide

class Rc1adapter(context:Context): RecyclerView.Adapter<Rc1adapter.ViewHolder>() {
    private lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListner(listner: onItemClickListner){
        mListener = listner
    }
    var dataList = emptyList<FsData>()
    internal fun setData(data:List<FsData>){
        this.dataList=data
        notifyDataSetChanged()
    }


     class ViewHolder(itemView:View,listner: onItemClickListner):RecyclerView.ViewHolder(itemView){
        var img :ImageView
        var text:TextView
        init {
            img=itemView.findViewById<ImageView>(R.id.rc1_image)
            text=itemView.findViewById<TextView>(R.id.rc1_text)
            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_1,parent,false)
        return ViewHolder(view,mListener)
    }


    override fun getItemCount()=dataList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        Glide.with(holder.img).load(data.picurl).into(holder.img)
        // holder.img.setImageResource(data.!!)
        holder.text.text= data.name.toString()
    }


}