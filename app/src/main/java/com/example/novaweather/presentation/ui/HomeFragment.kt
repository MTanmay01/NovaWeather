package com.example.novaweather.presentation.ui

import android.Manifest
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.domain.models.Resource
import com.example.novaweather.R
import com.example.novaweather.adapters.ForecastWeatherAdapter
import com.example.novaweather.databinding.FragmentHomeBinding
import com.example.novaweather.presentation.viewmodels.HomeViewModel
import com.example.novaweather.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var mAdapter = ForecastWeatherAdapter(emptyList())

    @Inject
    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionUtils.checkPermissions(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            PermissionUtils.requestPermissions(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) {
                viewModel.fetchWeather()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.forecastRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = mAdapter

        }
        binding.refreshWeather.setOnClickListener {
            Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
            viewModel.fetchWeather()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {     
            viewModel.realtimeWeatherFlow.collect {
                when (it) {
                    Resource.Loading -> {
                        binding.loadingAnim.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        binding.loadingAnim.setColorFilter(0xFF0000)
                    }

                    is Resource.Success -> {
                        val currentData = it.data.current
                        val locationData = it.data.location

                        binding.apply {
                            location.text = locationData.name
                            weatherText.text = currentData.condition.text
                            temp.text = currentData.tempCelsius
                            windSpeed.text = currentData.windKph
                            humidity.text = currentData.humidity.toString()
                            aqiPm25.text = epaIndexText(currentData.airQuality.epaIndex)

                            Glide.with(requireContext())
                                .load(R.drawable.clear)
                                .into(binding.weatherIcon)
                        }
                        binding.loadingAnim.visibility = View.INVISIBLE
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.forecastWeatherFlow.collect {
                when (it) {
                    Resource.Loading -> {
                        binding.forecastWeatherCover.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        binding.forecastWeatherCover.visibility = View.INVISIBLE
                    }

                    is Resource.Success -> {
                        mAdapter.setData(it.data.forecast.forecastDay)
                        binding.forecastWeatherCover.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun epaIndexText(index: Int): String {
        return when (index) {
            1 -> "Good"
            2 -> "Moderate"
            3 -> "Unhealthy"
            4 -> "Unhealthy"
            5 -> "Very Unhealthy"
            6 -> "Hazardous"
            else -> "Unknown"
        }
    }

}
