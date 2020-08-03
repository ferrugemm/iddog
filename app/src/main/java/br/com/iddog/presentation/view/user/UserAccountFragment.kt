package br.com.iddog.presentation.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.iddog.R
import br.com.iddog.presentation.state.UserAccountStates
import br.com.iddog.presentation.viewmodel.user.UserAccountViewModel
import kotlinx.android.synthetic.main.fragment_user_account.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserAccountFragment : Fragment() {
    private val userAccountViewModel by viewModel<UserAccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEnter.setOnClickListener {
            buttonEnter.startAnimation()
            userAccountViewModel.signUpUser(tiUserIdentifier.editText?.text.toString())
        }

        userAccountViewModel.userAccountObservable.observe(viewLifecycleOwner, Observer {
            when (it) {
                UserAccountStates.Success -> {
                    findNavController().navigate(UserAccountFragmentDirections.actionUserAccountFragmentToDogBreedOptionFragment())
                }
                UserAccountStates.InvalidEmail -> tiUserIdentifier.error = getString(R.string.user_account_incorrect_email)
                UserAccountStates.NetworkError -> Toast.makeText(requireContext(), getString(R.string.user_account_connection_error), Toast.LENGTH_LONG).show()
                else -> Toast.makeText(requireContext(), getString(R.string.user_account_unknown_error), Toast.LENGTH_LONG).show()
            }
            buttonEnter.revertAnimation()
        })

        tiUserIdentifier.editText?.addTextChangedListener {
            tiUserIdentifier.isErrorEnabled = false
        }
    }
}