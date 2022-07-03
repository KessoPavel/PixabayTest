package by.kesso.pixabaytest.ui.register

import android.os.Bundle
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
import by.kesso.pixabaytest.ui.utils.FieldValidator
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
            emailIsValid = FieldValidator.validateEmail(
                requireContext(),
                binding.email,
                binding.emailTextInputLayout,
            )
            validateRegistration()
        }
        binding.password.doOnTextChanged { _, _, _, _ ->
            passwordIsValid = FieldValidator.validatePassword(
                requireContext(),
                binding.password,
                binding.passwordTextInputLayout,
            )
            validateRegistration()
        }
        binding.confirmPassword.doOnTextChanged { _, _, _, _ ->
            passwordConfirmationIsValid = FieldValidator.validateConfirmPassword(
                requireContext(),
                binding.confirmPassword,
                binding.confirmPasswordTextInputLayout,
                binding.password
            )
            validateRegistration()
        }
    }

    private fun validateRegistration() {
        binding.register.isEnabled = emailIsValid && passwordIsValid && passwordConfirmationIsValid
    }
}