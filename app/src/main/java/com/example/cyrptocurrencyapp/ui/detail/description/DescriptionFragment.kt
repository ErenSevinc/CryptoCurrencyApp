package com.example.cyrptocurrencyapp.ui.detail.description

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cyrptocurrencyapp.data.model.InformationArgsDataModel
import com.example.cyrptocurrencyapp.databinding.FragmentDescriptionBinding
import com.example.cyrptocurrencyapp.ui.detail.CoinDetailViewModel
import com.example.cyrptocurrencyapp.ui.detail.info.InformationFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private val sharedViewModel by viewModels<CoinDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }


    private fun initLayout() = with(binding) {
        val desc = arguments?.getString(DESCRIPTION)
            coinDescription.text = Html.fromHtml(desc)

    }

    companion object {
        private const val DESCRIPTION = "DESCRIPTION"

        fun newInstance(description: String): DescriptionFragment {
            val fragment = DescriptionFragment()
            val args =  Bundle()
            args.putString(DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}


