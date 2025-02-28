package com.altamirobruno.games4free.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.games4free.R
import com.altamirobruno.games4free.databinding.FragmentGiveawayBinding
import com.altamirobruno.games4free.model.Category
import com.altamirobruno.games4free.model.Giveaway
import com.altamirobruno.games4free.presentation.GiveawayPresenter
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieAdapter

class GiveawayFragment : Fragment() {
  private var _binding: FragmentGiveawayBinding? = null
  private var adapter = GroupieAdapter()
  private lateinit var progressBar: ProgressBar
  private val binding get() = _binding!!
  private lateinit var presenter: GiveawayPresenter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = GiveawayPresenter(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentGiveawayBinding.inflate(inflater, container, false)
    val view = binding.root
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    progressBar = view.findViewById(com.altamirobruno.games4free.R.id.progress_item)
    val id: Int = arguments?.getInt("id") ?: 0
    presenter.loadingGiveAway(id)


  }

  fun showGiveaway(giveaway: Giveaway) {
    val giveawayTitle = binding.giveawayTitle
    val giveawayCover = binding.giveawayImage
    val giveawayDescription = binding.giveawayDescription

    giveawayTitle.text = giveaway.title
    giveawayDescription.text = giveaway.description
    Glide
      .with(giveawayCover)
      .load(giveaway.image)
      .centerCrop()
      .fallback(R.drawable.movie_cover_placeholder)
      .placeholder(R.drawable.movie_cover_placeholder)
      .into(giveawayCover);


  }

  fun showErrorToast(error: String) {
    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
  }

  fun showProgress() {
    progressBar.visibility = View.VISIBLE
  }

  fun hideProgress() {
    progressBar.visibility = View.GONE
  }

}
