package com.rakamin.news.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakamin.news.databinding.FragmentNewsBinding
import com.rakamin.news.di.Injection
import com.rakamin.news.presentation.list_adapter.AllNewsAdapter
import com.rakamin.news.presentation.list_adapter.TopHeadlineAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding


    private val viewModel: NewsViewModel by viewModels<NewsViewModel> {
        viewModelFactory {
            addInitializer(clazz = NewsViewModel::class) {
                NewsViewModel(Injection.provideRepository)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            viewModel.uiState.observe(viewLifecycleOwner) {
                Log.d(TAG, "onViewCreated: $it")

                if (it.isLoading) {
                    loadingIndicator.visibility = View.VISIBLE
                } else {
                    loadingIndicator.visibility = View.GONE
                }



                val topHeadlineAdapter = TopHeadlineAdapter()
                topHeadlineAdapter.setData(it.topHeadlineItems)
                topHeadlineRv.layoutManager = LinearLayoutManager(this@NewsFragment.context, LinearLayoutManager.HORIZONTAL, false)
                topHeadlineRv.adapter = topHeadlineAdapter

                lifecycleScope.launch {

                    for (i in it.topHeadlineItems.indices) {
                        topHeadlineRv.smoothScrollToPosition(i)
                        delay(3000L)
                    }

                }

                val allNewsAdapter = AllNewsAdapter(it.allNewsItems)
                allNewsRecyclerView.layoutManager = LinearLayoutManager(this@NewsFragment.context, LinearLayoutManager.VERTICAL, false)
                allNewsRecyclerView.adapter = allNewsAdapter

            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}