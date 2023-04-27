package uz.akbar.carea.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.akbar.carea.R

class DealsCategoriesAdapter(var list: MutableList<String>, val choosedTopic: ChoosedTopic):
    RecyclerView.Adapter<DealsCategoriesAdapter.MyHolder>() {

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var text = itemView.findViewById<TextView>(R.id.dealsname)
        var layout = itemView.findViewById<RelativeLayout>(R.id.dealsrealtive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val myholder = MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.topdeals_item, parent, false))
        return myholder
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val a = list.get(position)
        holder.text.text = a
        holder.itemView.setOnClickListener {
            choosedTopic.Topic(a)
            holder.itemView.setBackgroundResource(R.drawable.outlinedboxdark)
            holder.text.setTextColor(Color.parseColor("#ffffff"))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ChoosedTopic{
        fun Topic(string: String)
    }
}