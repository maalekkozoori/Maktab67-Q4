package ir.mohsenafshar.apps.mkbarchitecture.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktab_q4.databinding.ItemUserBinding
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse


class RecyclerAdapter(private val items: List<UserResponse>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(username: String) {
            binding.tvUsername.text = username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemUserBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position].firstName+" "+items[position].lastName)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}