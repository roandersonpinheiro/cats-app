package com.example.catsapp.view

import CatsAdapter
import CatsClickListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catsapp.R
import com.example.catsapp.databinding.FragmentCatsBinding
import com.example.catsapp.model.Image
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatsFragment : Fragment() {
    companion object {
        const val IMAGE_SIZE = 't'
    }

    private val images = mutableListOf<Image>()
    private val catsFragmentViewModel: CatsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCatsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cats, container, false)



        val adapter = CatsAdapter(CatsClickListener { catId ->
            Log.d("TAG_", "cat id: $catId")
        })

        binding.rvCats.adapter = adapter
        binding.rvCats.layoutManager = GridLayoutManager(activity, 3)

        catsFragmentViewModel.catList.observe(viewLifecycleOwner) { catResult ->
            catResult.data.forEach { item->
                item.images?.forEach { image ->
                    if (image.link != null) {
                        val idx = image.link.lastIndexOf(".")
                        image.thumbLink = StringBuilder(image.link).insert(idx, IMAGE_SIZE).toString()
                        images.add(image)
                    }
                }
            }
            adapter.submitList(images)
        }
        catsFragmentViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(activity,it,Toast.LENGTH_SHORT).show()
        }

        catsFragmentViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        catsFragmentViewModel.getFetchCats()
        return binding.root
    }
}