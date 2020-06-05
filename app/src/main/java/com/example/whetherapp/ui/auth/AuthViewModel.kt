package com.example.whetherapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.whetherapp.data.repositories.UserRepository
import com.example.whetherapp.util.ApiException
import com.example.whetherapp.util.Coroutines
import com.example.whetherapp.util.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var passwordconfirm: String? = null

    var authListener: AuthListener? = null


    fun getLoggedUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }

    fun onSignUpButtonClick(view: View) {
        authListener?.onStarted()
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()|| passwordconfirm.isNullOrEmpty()) {
            authListener?.onFailure("all rows is required")
            return
        }
        if(passwordconfirm!=password){
            authListener?.onFailure("passwords are did not match")
        }

        Coroutines.main {
            try {
                val authResponse = repository.userSignUp(name!!,email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
    fun onSignUp(view:View){
        Intent(view.context,SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}