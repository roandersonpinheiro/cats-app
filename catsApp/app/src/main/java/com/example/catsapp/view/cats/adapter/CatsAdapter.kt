import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapp.databinding.CatsItemViewBinding
import com.example.catsapp.model.Image

class CatsAdapter(private val clickListener: CatsClickListener) :
    ListAdapter<Image, CatsAdapter.CatViewHolder>(CatsDiffCallback()) {

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    class CatViewHolder private constructor(private val binding: CatsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(clickListener: CatsClickListener, item: Image) {
            with(binding) {
                imageDto = item
                itemView.setOnClickListener {
                    clickListener.onClick(item)
                }
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): CatViewHolder {
                val binding: CatsItemViewBinding = CatsItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CatViewHolder(binding)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder.from(parent)
    }

}

class CatsDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}

class CatsClickListener(val clickListener: (catId: String) -> Unit) {
    fun onClick(image: Image) = clickListener(image.id)
}