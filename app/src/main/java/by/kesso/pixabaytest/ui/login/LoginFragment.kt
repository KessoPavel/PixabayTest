package by.kesso.pixabaytest.ui.login

import android.os.Bundle
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
import by.kesso.pixabaytest.ui.utils.FieldValidator
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
        setupView()
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
            emailIsValid = FieldValidator.validateEmail(
                requireContext(),
                binding.email,
                binding.emailTextInputLayout
            )
            validateLogin()
        }
        binding.password.doOnTextChanged { _, _, _, _ ->
            passwordIsValid = FieldValidator.validatePassword(
                requireContext(),
                binding.password,
                binding.passwordTextInputLayout
            )
            validateLogin()
        }
    }

    private fun setupView() {
        binding.register.setOnClickListener {
            val direction = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(direction)
        }
        viewModel.setEmail(args.email ?:"")
    }

    private fun validateLogin() {
        binding.login.isEnabled = emailIsValid && passwordIsValid
    }
}