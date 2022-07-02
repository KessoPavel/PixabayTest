package by.kesso.pixabaytest.ui.register

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
import by.kesso.pixabaytest.R
import by.kesso.pixabaytest.databinding.FragmentRegisterBinding
import by.kesso.pixabaytest.ui.login.LoginFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment: Fragment() {
    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: FragmentRegisterBinding
    private var emailIsValid: Boolean = false
    private var passwordIsValid: Boolean = false
    private var passwordConfirmationIsValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupValidation()
    }

    private fun observeViewModel() = with(viewModel) {
        error.observe(viewLifecycleOwner) {
            binding.error.isVisible = it != null
            binding.error.text = it
        }
        success.observe(viewLifecycleOwner) {
            val direction =
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(binding.email.text?.toString())
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
            validateRegistration()
        }
        binding.password.doOnTextChanged { _, _, _, _ ->
            passwordIsValid = validatePassword()
            validateRegistration()
        }
        binding.confirmPassword.doOnTextChanged { _, _, _, _ ->
            passwordConfirmationIsValid = validateConfirmPassword()
            validateRegistration()
        }
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

    private fun validateConfirmPassword(): Boolean {
        when {
            binding.confirmPassword.text.toString().trim().isEmpty() -> {
                binding.confirmPasswordTextInputLayout.error = "Required Field!"
                binding.confirmPassword.requestFocus()
                return false
            }
            binding.confirmPassword.text.toString() != binding.password.text.toString() -> {
                binding.confirmPasswordTextInputLayout.error = "Passwords don't match"
                binding.confirmPassword.requestFocus()
                return false
            }
            else -> {
                binding.confirmPasswordTextInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    private fun validateRegistration() {
        binding.register.isEnabled = emailIsValid && passwordIsValid && passwordConfirmationIsValid
    }
}