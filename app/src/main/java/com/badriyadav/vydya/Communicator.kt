package com.badriyadav.vydya

interface Communicator {
    fun passData(diseaseName:String,cause:ArrayList<String>,picurl:String,picurl2: String
                 ,preventions:ArrayList<String>,tablets:ArrayList<String>)
}