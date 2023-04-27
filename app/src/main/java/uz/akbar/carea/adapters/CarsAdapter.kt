package uz.akbar.carea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.akbar.carea.R
import uz.akbar.carea.dataClass.CarsData
import java.io.Serializable

class CarsAdapter(var list: MutableList<CarsData>,
                  val onPressed: OnPressed, var onSelected: OnSelected): RecyclerView.Adapter<CarsAdapter.MyHolder>(),
    Serializable {

    class MyHolder(view: View): RecyclerView.ViewHolder(view) {
        var rasmi = view.findViewById<ImageView>(R.id.carimage)
        var nomi = view.findViewById<TextView>(R.id.carname)
        var reyting = view.findViewById<TextView>(R.id.reyting)
        var price = view.findViewById<TextView>(R.id.narxi)
        var yurakk = view.findViewById<ImageView>(R.id.yurakcha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val myholder = MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.cars_item, parent, false))
        return myholder
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val a = list.get(position)
        holder.rasmi.setImageResource(a.carrasm)
        holder.nomi.text = a.carname
        holder.price.text = a.carprice
        holder.reyting.text = a.carreyting

        if (a.status){
            holder.yurakk.setImageResource(R.drawable.yurak2)
        }else{
            holder.yurakk.setImageResource(R.drawable.heart)
        }

        holder.yurakk.setOnClickListener {
            if (!a.status){
                holder.yurakk.setImageResource(R.drawable.yurak2)
                a.status=true
            }else{
                holder.yurakk.setImageResource(R.drawable.heart)
                a.status=false
            }
            onSelected.onSelect(a, list)
        }
        holder.itemView.setOnClickListener {
            onPressed.onPressed(a)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnSelected{
        fun onSelect(carsData: CarsData,list:MutableList<CarsData>)
    }


    interface OnPressed{
        fun onPressed(carsData: CarsData)
    }
}