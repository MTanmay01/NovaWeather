package com.example.novaweather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.models.ForecastWeatherDomain
import com.example.novaweather.databinding.ForecastListItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ForecastWeatherAdapter(
    private var daysData: List<ForecastWeatherDomain.ForecastDomain.ForecastDayDomain>
): RecyclerView.Adapter<ForecastWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastWeatherViewHolder {
        val binding = ForecastListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastWeatherViewHolder, position: Int) {
        holder.bind(daysData[position])
    }

    override fun getItemCount(): Int = daysData.size

    fun setData(data: List<ForecastWeatherDomain.ForecastDomain.ForecastDayDomain>) {
        daysData = data
        notifyDataSetChanged()
    }
}

class ForecastWeatherViewHolder(
    private val binding: ForecastListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ForecastWeatherDomain.ForecastDomain.ForecastDayDomain) {
        binding.apply {
            weekDay.text = LocalDate.parse(item.date, DateTimeFormatter.ISO_DATE).dayOfWeek.name.substring(0..2)
            temp.text = item.day.avgTempCelsius.toString()

            Glide.with(binding.root.context)
                .load(item.day.condition.iconUrl)
                .into(binding.weatherIcon)
        }
    }
}