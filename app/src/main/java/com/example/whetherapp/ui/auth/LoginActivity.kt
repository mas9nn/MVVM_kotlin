package com.example.whetherapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.whetherapp.R
import com.example.whetherapp.data.db.entities.User
import com.example.whetherapp.databinding.ActivityLoginBinding
import com.example.whetherapp.ui.home.HomeActivity
import com.example.whetherapp.util.hide
import com.example.whetherapp.util.show
import com.example.whetherapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware {

    override val kodein by kodein()

    private val factory :AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedUser().observe(this, Observer {user->
            if(user!=null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
