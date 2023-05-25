package com.bsquare.core.common.constants

import android.util.Log

sealed class Destination(
 protected val route: String,
 vararg arguments: Any) {

 val fullRoute: String = if (arguments.isEmpty()) route else {
  val builder = StringBuilder(route)
  arguments.forEach { builder.append("/{${it}}") }
     Log.e("FullRoute", builder.toString())
  builder.toString()
 }

 sealed class NoArgumentsDestination(route: String) : Destination(route) {
  operator fun invoke(): String = route
 }

 object SplashScreen : NoArgumentsDestination(AppRoutes.SPLASH)

 object LoginScreen : NoArgumentsDestination(AppRoutes.LOGIN)

 object DashboardScreen : NoArgumentsDestination(AppRoutes.DASHBOARD)

 object LeadScreen : Destination(AppRoutes.LEAD, "feature_Id") {
     const val  featureId_KEY = "feature_Id"
  operator fun invoke(featureId: String): String = route.appendParams(
   featureId_KEY to featureId
  )

 }

 object CompanyDetailScreen: NoArgumentsDestination(AppRoutes.COMPANY)


 object FilterScreen: NoArgumentsDestination(AppRoutes.FILTER)


 object AddleadScreen: NoArgumentsDestination(AppRoutes.ADDLEAD)


 object FollowupScreen: NoArgumentsDestination(AppRoutes.FOLLOW)

 object AddTaskScreen: NoArgumentsDestination(AppRoutes.ADDTASK)


 object AddActivityScreen: NoArgumentsDestination(AppRoutes.ADDACTIVITY)


 object FollowFilterScreen: NoArgumentsDestination(AppRoutes.FOLLOWFILTER)


}


private fun String.appendParams(vararg params: Pair<String, Any?>): String {
 val builder = StringBuilder(this)

 params.forEach {
  it.second?.toString()?.let { arg ->
   builder.append("/$arg")
  }
 }

 return builder.toString()
}