package com.example.whetherapp.ui.home.quates

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.whetherapp.R
import com.example.whetherapp.util.Coroutines
import com.example.whetherapp.util.toast
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class QuatesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: QuotesViewModelFactory by instance()
    private lateinit var viewModel: QuatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quates_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuatesViewModel::class.java)

        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }
    }

}
