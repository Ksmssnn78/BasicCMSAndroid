package com.example.cmsapp.adapters

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cmsapp.R
import com.example.cmsapp.models.UserDataModelItem
import com.google.android.material.textview.MaterialTextView

class UserAdapter(private val context: Context,private val userList:List<UserDataModelItem>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var clickListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        clickListener = listener
    }

    class ViewHolder(userItem: View,listener: OnItemClickListener):RecyclerView.ViewHolder(userItem){

        var idHomeRecycler: MaterialTextView
        var nameHomeRecycler: MaterialTextView
        var emailHomeRecycler: MaterialTextView
        var genderHomeRecycler: MaterialTextView

        init {
            userItem.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            idHomeRecycler = userItem.findViewById(R.id.idHomeRecycler)
            nameHomeRecycler = userItem.findViewById(R.id.nameHomeRecycler)
            emailHomeRecycler = userItem.findViewById(R.id.emailHomeRecycler)
            genderHomeRecycler = userItem.findViewById(R.id.genderHomeRecycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val userItem = LayoutInflater.from(parent.context).inflate(R.layout.individual_item_home,parent,false)
        return ViewHolder(userItem,clickListener)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idHomeRecycler.text = userList[position].id.toString()
        holder.nameHomeRecycler.text = userList[position].name
        holder.emailHomeRecycler.text = userList[position].email
        holder.genderHomeRecycler.text = userList[position].gender
    }
}