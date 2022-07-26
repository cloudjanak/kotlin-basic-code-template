package com.zw.template.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zw.template.R
import com.zw.template.core.CommonUtils.isValidPassword
import com.zw.template.core.ViewModelFactory
import com.zw.template.core.getViewModelFromFactory
import com.zw.template.core.showToast
import com.zw.template.databinding.ActivityLoginBinding
import com.zw.template.di.ZwApplication
import com.zw.template.models.LoginRequest
import com.zw.template.viewmodels.LoginState
import com.zw.template.viewmodels.LoginViewModel
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ZwApplication.component.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClicks(binding.btnLogin, binding.tvForgot)

        viewModel = getViewModelFromFactory(viewModelFactory)

        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.liveDataObserver.observe(this) {
            when (it) {
                is LoginState.Loading -> {
                    if (it.isLoading) {
                        mDialog.show()
                    } else {
                        mDialog.dismiss()
                    }
                }
                is LoginState.Success -> {
                    mDialog.dismiss()
                    startActivity(Intent(mActivity, MainActivity::class.java))
                    finish()
                }
                is LoginState.Error -> {
                    mDialog.dismiss()
                    showToast(it.error)
                }
            }
        }
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.btnLogin -> if (isValid()) {
                viewModel.login(
                    LoginRequest(
                        password = binding.etPassword.text.toString(), customerRelationShipNumber = binding.etCRN.text.toString()
                    )
                )

            }
        }
    }

    private fun isValid(): Boolean {
        if (binding.etCRN.text.toString().trim { it <= ' ' }.isEmpty()) {
            showToast(getString(R.string.enter_crn))
            binding.etCRN.requestFocus()
            return false
        } /*else if (!isValidEmail(binding!!.edtCRN.text.toString())) {
            showToast(getString(R.string.enter_valid_email))
            binding!!.edtCRN.requestFocus()
            return false
        } */ else if (binding.etPassword.text.toString().trim { it <= ' ' }.isEmpty()) {
            showToast(getString(R.string.enter_password))
            binding.etPassword.requestFocus()
            return false
        } else if (!isValidPassword(binding.etPassword.text.toString())) {
            showToast(getString(R.string.enter_valid_password))
            binding.etPassword.requestFocus()
            return false
        }
        return true
    }
}