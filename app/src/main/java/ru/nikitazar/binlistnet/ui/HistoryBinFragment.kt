package ru.nikitazar.binlistnet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.binlistnet.R
import ru.nikitazar.binlistnet.databinding.FragmentHistoryBinBinding
import ru.nikitazar.binlistnet.dto.CardBin
import ru.nikitazar.binlistnet.ui.adapter.CardBinAdapter
import ru.nikitazar.binlistnet.ui.adapter.OnInteractionListener
import ru.nikitazar.binlistnet.ui.view.utils.SpacingItemDecorator
import ru.nikitazar.binlistnet.viewModel.CardViewModel

const val ITEM_VERTICAL_SPACE = 20

@AndroidEntryPoint
class HistoryBinFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinBinding
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinBinding.inflate(inflater, container, false)

        val adapter = CardBinAdapter(object : OnInteractionListener {
            override fun onRemove(bin: CardBin) {
                viewModel.removeById(bin.id)
            }
        })
        binding.binList.adapter = adapter
        binding.binList.addItemDecoration(SpacingItemDecorator(ITEM_VERTICAL_SPACE))

        binding.btRemoveAll.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.remove_all_mes))
                .setPositiveButton(getString(R.string.yes_text)) { _, _ ->
                    viewModel.removeAll()
                }
                .setNegativeButton(getString(R.string.no_text)) { _, _ -> }
                .show()
        }

        viewModel.binData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }
}