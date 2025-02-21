package com.altamirobruno.games4free.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.altamirobruno.games4free.databinding.FragmentHomeBinding
import com.altamirobruno.games4free.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment() {
  private var _binding: FragmentHomeBinding? = null
  private var adapter =  GroupieAdapter()
  private val binding get() = _binding!!
  private lateinit var presenter: HomePresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = HomePresenter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.loadingGiveAways()


  }
}
