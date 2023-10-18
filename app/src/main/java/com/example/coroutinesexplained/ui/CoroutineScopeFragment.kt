package com.example.coroutinesexplained.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesexplained.databinding.FragmentCoroutineScopeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 * Use the [coroutineScopeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoroutineScopeFragment : Fragment() {
    private val binding by lazy {
      FragmentCoroutineScopeBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<MyViewModel>()

    //Coroutinescope determines a scope's lifecycle
    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLaunch.setOnClickListener {

            //asynchronous
            //traditional Async approaches: Callbacks and Futures
            //Coroutines allow to write async code in sequential and structured manner
            lifecycleScope.launch {
                delay(3000)
                Toast.makeText(requireContext(),"Toast after 3 seconds", Toast.LENGTH_SHORT).show()

            }

            Toast.makeText(requireContext(),"instant toast", Toast.LENGTH_SHORT).show()
        }

        binding.btnLaunchInCustomCoroutine.setOnClickListener {
            coroutineScope.launch {
                delay(3000)
                Toast.makeText(requireContext(),"Toast after 3 seconds", Toast.LENGTH_SHORT).show()
            }

            Toast.makeText(requireContext(),"instant toast", Toast.LENGTH_SHORT).show()
        }

        binding.btnLaunchInIo.setOnClickListener {
            ioScope.launch {
               //Synchronous
                withContext(Dispatchers.Main){
                    delay(3000)
                    Toast.makeText(requireContext(),"Toast after 3 seconds", Toast.LENGTH_SHORT).show()
                }

                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"instant toast", Toast.LENGTH_SHORT).show()
                }

            }
        }


        binding.btnFetchProfile.setOnClickListener {
            viewModel.fetchUserProfile { userProfileData ->
                Toast.makeText(requireContext(),userProfileData.name + userProfileData.age, Toast.LENGTH_SHORT).show()
            }
        }


    }



    companion object {

    }

    override fun onStop() {
        super.onStop()
        coroutineScope.cancel()
    }
}