package com.dalmasmaka.rickandmortywatchbook.ui.characterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dalmasmaka.rickandmortywatchbook.databinding.FragmentCharacterDetailBinding
import com.dalmasmaka.rickandmortywatchbook.ui.characterdetail.EpisodeAdapter
import com.mirtneg.rickandmortywarchbool.ui.characterDetail.CharacterDetailFragmentArgs
import com.mirtneg.rickandmortywarchbool.ui.characterDetail.CharacterDetailViewModel
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    lateinit var binding: FragmentCharacterDetailBinding
    val args by navArgs<CharacterDetailFragmentArgs>()
    lateinit var viewModel: CharacterDetailViewModel
    lateinit var adapter : EpisodeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CharacterDetailViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacterById(args.characterId)

        viewModel.character.observe(viewLifecycleOwner) {
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

        viewModel.episodeResponse.observe(viewLifecycleOwner) {
            val episodes = it
            //todo complete this part adapter per episodes
        }
    }
}