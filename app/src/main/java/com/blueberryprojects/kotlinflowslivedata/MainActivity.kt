package com.blueberryprojects.kotlinflowslivedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.blueberryprojects.kotlinflowslivedata.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this) {
            val liveData = "LiveData: $it"

            binding.txtLiveData.text = liveData
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.stateFlow.collectLatest {
                        val stateFlow = "StateFlow: $it"

                        binding.txtStateFlow.text = stateFlow
                    }
                }

                launch {
                    viewModel.sharedFlow.collectLatest {
                        val sharedFlow = "SharedFlow: $it"

                        binding.txtSharedFlow.text = sharedFlow
                    }
                }
            }
        }

        binding.btnStartLiveData.setOnClickListener {
            viewModel.startLiveData()
        }

        binding.btnStartStateFlow.setOnClickListener {
            viewModel.startStateFlow()
        }

        binding.btnStartSharedFlow.setOnClickListener {
            viewModel.startSharedFlow()
        }

        binding.btnStartFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.startFlow().collectLatest {
                    val flow = "Flow: $it"

                    binding.txtFlow.text = flow
                }
            }
        }
    }
}