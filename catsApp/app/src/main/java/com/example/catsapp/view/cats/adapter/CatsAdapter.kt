
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catsapp.R
import com.example.catsapp.model.Image
import com.squareup.picasso.Picasso


class CatsAdapter(private val clickListener: CatsClickListener) : ListAdapter<Image, CatsAdapter.CatViewHolder>(CatsDiffCallback()) {

    class CatViewHolder private constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catImage : ImageView = itemView.findViewById(R.id.imageViewCatImage)
        fun bin(clickListener: CatsClickListener, item: Image) {
            Picasso.get().load(item.thumbLink ?: item.link).fit().into(catImage)
            itemView.setOnClickListener{
                clickListener.onClick(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CatViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.cats_item_view, parent, false)
                return CatViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItem(position)
        holder.bin(clickListener, item)
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

class CatsClickListener(val clickListener: (catId: Int) -> Unit) {
    fun onClick(image: Image) = clickListener(Integer.parseInt(image.id))
}