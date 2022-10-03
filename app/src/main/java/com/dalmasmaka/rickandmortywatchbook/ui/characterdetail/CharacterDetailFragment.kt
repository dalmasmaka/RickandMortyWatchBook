package com.dalmasmaka.rickandmortywatchbook.ui.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dalmasmaka.rickandmortywatchbook.R
import com.dalmasmaka.rickandmortywatchbook.databinding.FragmentCharacterDetailBinding
import com.dalmasmaka.rickandmortywatchbook.ui.characterdetail.EpisodesAdapter
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    lateinit var binding: FragmentCharacterDetailBinding
    val args by navArgs<CharacterDetailFragmentArgs>()
    lateinit var viewModel: CharacterDetailViewModel
    lateinit var adapter : EpisodesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CharacterDetailViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EpisodesAdapter(this::onEpisodeClick)
        viewModel.getCharacterById(args.characterId)
        binding.episodes.layoutManager = LinearLayoutManager(requireActivity())
        binding.episodes.adapter = adapter

        viewModel.characterResponse.observe(viewLifecycleOwner) {
            with(it) {
                binding.characterName.text = name
                Picasso.get().load(image).into(binding.characterImage)

                binding.genderInfo.tagTextView.text = "Gender"
                binding.genderInfo.dataTextView.text = gender

                binding.statusInfo.tagTextView.text = "Status"
                binding.statusInfo.dataTextView.text = status

                binding.speciesInfo.tagTextView.text = "Species"
                binding.speciesInfo.dataTextView.text = species

                binding.originInfo.tagTextView.text = "Origin"
                binding.originInfo.dataTextView.text = origin.name

                binding.typeInfo.tagTextView.text = "Type"
                binding.typeInfo.dataTextView.text = type

                binding.locationInfo.tagTextView.text = "Location"
                binding.locationInfo.dataTextView.text = location.name
            }
        }
        adapter
        viewModel.episodeResponse.observe(viewLifecycleOwner){
            adapter.episodes = it
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_characterDetailFragment)
        }
    }
    fun onEpisodeClick(episodeId : String){
        println("Clicked Episode $episodeId")
    }
}