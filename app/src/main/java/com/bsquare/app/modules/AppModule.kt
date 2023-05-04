package com.bsquare.app.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bsquare.app.data.repositories.*
import com.bsquare.app.utills.helper_impl.AppStoreImpl
import com.bsquare.core.domain.repositories.dashboard.DashboardRepository
import com.bsquare.core.domain.repositories.followup.AddTaskRepository
import com.bsquare.core.domain.repositories.followup.FollowupRepository
import com.bsquare.core.domain.repositories.intro.SplashRepository
import com.bsquare.core.domain.repositories.lead.AddLeadRepository
import com.bsquare.core.domain.repositories.lead.CompanyDetailsRepository
import com.bsquare.core.domain.repositories.lead.LeadRepository
import com.bsquare.core.domain.repositories.login.LoginRepository
import com.bsquare.core.utils.helper.AppStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        private val Context.dataStore by preferencesDataStore("BSqureApp")

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        appContext.dataStore

    }


//    @Binds
//    fun bindAppInfo(appInfo: AppInfo): Info

    @Binds
    fun bindSplashRepository(splashRepository: SplashRepositoryImpl): SplashRepository

    @Binds
    fun bindAppStore (appStoreImpl: AppStoreImpl): AppStore

    @Binds
    fun bindLoginRepository(repo: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindDashboardRepository(repo:DashboardRepositoryImpl) : DashboardRepository


    @Binds
    fun bindLeadRepository(repo:LeadRepositoryImpl) : LeadRepository


    @Binds
    fun  bindCompanyDetailRepository(repo: DeatilRepositoryImpl) :CompanyDetailsRepository


    @Binds
    fun bindAddLeadRepository(repo:AddLeadRepositoryImpl) : AddLeadRepository


    @Binds
    fun bindFollowRepository(repo:FollowupRepositoryImpl) : FollowupRepository


    @Binds
    fun bindAddTaskRepository(repo:AddTaskRepositoryImpl) : AddTaskRepository
}