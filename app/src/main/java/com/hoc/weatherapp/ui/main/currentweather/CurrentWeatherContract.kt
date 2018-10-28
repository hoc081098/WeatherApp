package com.hoc.weatherapp.ui.main.currentweather

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hoc.weatherapp.data.models.entity.CurrentWeather
import io.reactivex.Observable

interface CurrentWeatherContract {
  sealed class PartialStateChange {
    data class Error(
      val throwable: Throwable,
      val showMessage: Boolean
    ) : PartialStateChange()

    data class Weather(val weather: CurrentWeather) : PartialStateChange()

    data class RefreshWeatherSuccess(
      val weather: CurrentWeather,
      val showMessage: Boolean
    ) : PartialStateChange()
  }

  data class ViewState(
    val weather: CurrentWeather? = null,
    val error: Throwable? = null,
    val showError: Boolean = false,
    val showRefreshSuccessfully: Boolean = false
  )

  object InitialRefreshIntent

  interface View : MvpView {
    fun refreshCurrentWeatherIntent(): Observable<Any>

    fun render(state: ViewState)
  }
}