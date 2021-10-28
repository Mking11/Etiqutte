package com.mking11.etiquette.features.countries.data.data_soruce

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import com.mking11.etiquette.common.firebaseutils.FirebaseCrash
import com.mking11.etiquette.common.firebaseutils.FirebaseRealDb
import com.mking11.etiquette.common.utils.repo_utils.FirebaseValueDataSource
import com.mking11.etiquette.features.countries.domain.models.CountriesDto

class CountriesFirebaseSource(
    private val firebaseRealDb: FirebaseRealDb,
    private val firebaseCrash: FirebaseCrash
) : FirebaseValueDataSource<CountriesDto>() {

    override val docRef: DatabaseReference
        get() = firebaseRealDb.countries()

    override fun onDataChanged(snapshot: DataSnapshot) {
        try {
            if (snapshot.exists()) {
                val data = snapshot.getValue<HashMap<String, CountriesDto>>()
                repoHashedLive.value = data ?: hashMapOf()
            }
        } catch (e: Exception) {
            firebaseCrash.setErrorToFireBase(e, "onDataChanged CountriesFirebaseSource.kt  27: ")
        }
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("CountriesFirebaseSource", "onCancelled: $error")
    }
}