package com.example.catsapp.view

import CatsAdapter
import CatsClickListener
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catsapp.R
import com.example.catsapp.databinding.FragmentCatsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CatsFragment : Fragment() {
    private val catsFragmentViewModel: CatsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCatsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cats, container, false)
        val adapter = CatsAdapter(CatsClickListener { catId ->
            Toast.makeText(activity,"cat id: $catId",Toast.LENGTH_SHORT).show()
            Log.d("TAG_", "cat id: $catId")
        })
        setHasOptionsMenu(true);
        binding.rvCats.adapter = adapter
        binding.rvCats.layoutManager = GridLayoutManager(activity, 3)

        catsFragmentViewModel.catListImages.observe(viewLifecycleOwner) { imageList ->
            adapter.submitList(imageList)
        }
        catsFragmentViewModel.errorMessage.observe(viewLifecycleOwner) {

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_refresh) {
            catsFragmentViewModel.getFetchCats()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}