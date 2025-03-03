package com.altamirobruno.games4free.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

  fun openLink(view: View, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    view.context.startActivity(intent)

  }

  fun showGiveaway(giveaway: Giveaway) {
    val giveawayTitle = binding.giveawayTitle
    val giveawayCover = binding.giveawayImage
    val giveawayDescription = binding.giveawayDescription
    val giveawayBtn = binding.giveawayBtn
    val giveawayPlatform = binding.giveawayPlatform
    val giveawayStore = binding.giveawayStore
    val giveawayType = binding.giveawayType
    val listPlatforms = giveaway.platforms.split(',')

    giveawayTitle.text = giveaway.title
    giveawayDescription.text = giveaway.description
    giveawayType.setText("${giveaway.type} |")


    Glide
      .with(giveawayCover)
      .load(giveaway.image)
      .centerCrop()
      .fallback(R.drawable.movie_cover_placeholder)
      .placeholder(R.drawable.movie_cover_placeholder)
      .into(giveawayCover);

    if (listPlatforms.isNotEmpty()) {
      giveawayPlatform.setText(listPlatforms[0].toString())
      if (listPlatforms.size > 1) giveawayStore.setText(listPlatforms[1].toString())

    }

    giveawayBtn.setOnClickListener {
      view?.let { it1 -> openLink(it1, giveaway.open_giveaway_url) }


    }

  }

  fun showErrorToast(error: String) {
    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
  }

  fun showProgress() {
    binding.giveawayContent.visibility = View.GONE
    progressBar.visibility = View.VISIBLE
  }

  fun hideProgress() {
    progressBar.visibility = View.GONE
    binding.giveawayContent.visibility = View.VISIBLE
  }

  override fun onResume() {
    super.onResume()
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(true)
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)


  }

  override fun onPause() {
    super.onPause()
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(true)
  }

}
