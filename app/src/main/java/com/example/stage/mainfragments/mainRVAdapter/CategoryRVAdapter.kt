package com.example.stage.mainfragments.mainRVAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainfragments.MenuFragment

class CategoryRVAdapter(var context: Context, categoryList : ArrayList<ArrayList<String>>):

    RecyclerView.Adapter<CategoryRVAdapter.Holder>() {
    var myCategoryList = categoryList
    var isChecked = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myCategoryList,position)
    }

    override fun getItemCount(): Int {
        return myCategoryList.size
    }

    fun listChanged(newCategoryList: ArrayList<ArrayList<String>>){
        myCategoryList.clear()
        myCategoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var categoryName = itemView?.findViewById<TextView>(R.id.category_name)
        var categoryEnglishName = itemView?.findViewById<TextView>(R.id.category_english_name)
        var categoryImg = itemView?.findViewById<ImageView>(R.id.category_image)

        fun bind (category: ArrayList<ArrayList<String>>, position: Int){

            categoryName?.text = category[position][1]
            categoryEnglishName?.text = category[position][2]
            val resourceId = context.resources.getIdentifier(category[position][3], "drawable", context.packageName)
            Glide.with(context)
                .load(resourceId)
                .into(categoryImg!!)
            itemView.setOnClickListener {
                // 아이템 위치를 전달
                var bundle = Bundle()
                var mainActivity = context as MainActivity
                var menuFragment = MenuFragment()
                bundle.putInt("title",position)
                bundle.putBoolean("isChecked",isChecked)
                if(mainActivity.applang == "ko") {
                    bundle.putString("categoryName", category[position][1])
                }else if (mainActivity.applang == "en"){
                    bundle.putString("categoryName", category[position][2])
                }
                menuFragment.arguments = bundle
                mainActivity.addFragment(menuFragment,"menu")
            }
        }
    }
}