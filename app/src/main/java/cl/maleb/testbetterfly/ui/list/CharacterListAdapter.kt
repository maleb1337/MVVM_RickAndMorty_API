package cl.maleb.testbetterfly.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cl.maleb.testbetterfly.api.list.ResultData
import cl.maleb.testbetterfly.databinding.ItemCharacterListBinding
import com.bumptech.glide.Glide

class CharacterListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ResultData, CharacterListAdapter.CharacterListViewHolder>(CharacterListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val binding =
            ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }


    class CharacterListViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultData: ResultData, listener: OnItemClickListener) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClick(resultData)
                }
                Glide.with(itemView)
                    .load(resultData.image)
                    .into(imageView)

                textViewTitle.text = resultData.name
                textViewSubtitle.text = resultData.species

            }
        }
    }

    class CharacterListComparator : DiffUtil.ItemCallback<ResultData>() {
        override fun areItemsTheSame(oldItem: ResultData, newItem: ResultData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ResultData, newItem: ResultData) =
            oldItem == newItem

    }

    interface OnItemClickListener {
        fun onItemClick(result: ResultData)
    }

}