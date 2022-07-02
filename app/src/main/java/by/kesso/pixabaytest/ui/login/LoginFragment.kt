package by.kesso.pixabaytest.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private val args by navArgs<LoginFragmentArgs>()

    private lateinit var binding: FragmentLoginBinding
    private var emailIsValid: Boolean = false
    private var passwordIsValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupValidation()
        setupBinding()
    }

    private fun observeViewModel() = with(viewModel) {
        loginError.observe(viewLifecycleOwner) {
            binding.error.isVisible = it != null
            binding.error.text = it
        }
        success.observe(viewLifecycleOwner) {
            val direction = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(direction)
        }
        loading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
            binding.progressBg.isVisible = it
        }
    }

    private fun setupValidation() {
        binding.email.doOnTextChanged { _, _, _, _ ->
            emailIsValid = validateEmail()
            validateLogin()
        }
        binding.password.doOnTextChanged { _, _, _, _ ->
            passwordIsValid = validatePassword()
            validateLogin()
        }
    }

    private fun setupBinding() {
        binding.register.setOnClickListener {
            val direction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(direction)
        }
        viewModel.setEmail(args.email ?:"")
    }

    private fun validateEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            binding.emailTextInputLayout.error = "Required Field!"
            binding.email.requestFocus()
            return false
        } else if (!isValidEmail(binding.email.text.toString())) {
            binding.emailTextInputLayout.error = "Invalid Email!"
            binding.email.requestFocus()
            return false
        } else {
            binding.emailTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(): Boolean {
        if (binding.password.text.toString().trim().isEmpty()) {
            binding.passwordTextInputLayout.error = "Required Field!"
            binding.password.requestFocus()
            return false
        } else if (binding.password.text.toString().length !in 7..11) {
            binding.passwordTextInputLayout.error = "The password must be between 6 and 12 characters long"
            binding.password.requestFocus()
            return false
        } else {
            binding.passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateLogin() {
        binding.login.isEnabled = emailIsValid && passwordIsValid
    }
}