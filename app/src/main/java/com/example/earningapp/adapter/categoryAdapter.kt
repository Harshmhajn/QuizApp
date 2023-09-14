package com.example.earningapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.QuizActivity
import com.example.earningapp.databinding.CategoryItemBinding
import com.example.earningapp.modelClass.categoryModelClass

class categoryAdapter(
    var categoryList: ArrayList<categoryModelClass>,
    var requireActivity: FragmentActivity
):
    RecyclerView.Adapter<categoryAdapter.MyCategoryViewHolder>() {
    class MyCategoryViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
        return MyCategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = categoryList.size
    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        var datlist = categoryList[position]
        holder.binding.categoryImg.setImageResource(datlist.catImage)
        holder.binding.categoryTxt.text = datlist.catText
        holder.binding.categorybtn.setOnClickListener {
            var intent = Intent(requireActivity,QuizActivity::class.java)
            intent.putExtra("cat_img",datlist.catImage)
            intent.putExtra("questionType",datlist.catText)
            requireActivity.startActivity(intent)
        }
    }
}