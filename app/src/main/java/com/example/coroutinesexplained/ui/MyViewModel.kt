package com.example.coroutinesexplained.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesexplained.model.ProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {


    fun fetchUserProfile(userProfileData : (ProfileData) -> Unit){
     viewModelScope.launch {
         val token = "yourTokenHere"

         val userIdDeferred = async { fetchUserIdwithToken(token) }
         val userId = userIdDeferred.await()

         val profileDeferred = async { fetchProfile(userId) }
         val profileData = profileDeferred.await()

         // update the UI or perform other actions with the fetched data
         userProfileData(profileData)
     }
    }

    fun fetchUserProfileWithCallbackHell(){

    }

    private suspend fun fetchUserIdwithToken(token: String): String = withContext(Dispatchers.Default) {
        delay(3000)
        return@withContext token + "abc"
    }

    private fun fetchProfile(userId: String) : ProfileData{
        //in real scenario, consider this an api call with userId as param, returns response of type ProfileData
        return ProfileData(
            age = 27,
            name = "Jessi",
            email = "Jessi@Jessi.com"
        )
    }
}