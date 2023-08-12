package com.mohammedragab.innovaalomovesmock.di

import com.mohammedragab.innovaalomovesmock.datalayer.alomoverepository.AloMoveRepository
import com.mohammedragab.innovaalomovesmock.datalayer.alomoverepository.AloMoveRepositoryImpl
import com.mohammedragab.innovaalomovesmock.domainlayer.AloMoveUseCase
import com.mohammedragab.innovaalomovesmock.presentationlayer.trainingseries.TrainingSeriesScreen
import com.mohammedragab.innovaalomovesmock.presentationlayer.trainingseries.trainingseriesviewmodel.TrainingSeriesViewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val networkModule = module {
    single { getHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single() { provideRetailerApi(get()) }
    single() { provideRetrofit(get(), Generation_base_url) }
}
val repositoryModule = module {

    factory { AloMoveRepositoryImpl(get()) }binds arrayOf(AloMoveRepository::class)



}
val useCaseModule= module {
    factory { AloMoveUseCase(get()) }

}
  val aloMoveViewModel= module {
      factory { TrainingSeriesViewModel(get())  }}
