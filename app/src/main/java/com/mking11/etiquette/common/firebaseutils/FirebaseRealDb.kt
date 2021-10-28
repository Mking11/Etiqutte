package com.mking11.etiquette.common.firebaseutils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject


class FirebaseRealDb (
    private val realDb: FirebaseDatabase,
) {

    fun connectionState(): DatabaseReference {
        return realDb.getReference(".info/connected")
    }


    fun categories(): DatabaseReference {
        return realDb.getReference("categories")
    }

    fun countries(): DatabaseReference {
        return realDb.getReference("countries")
    }

    fun questions(): DatabaseReference {
        return realDb.getReference("questions")
    }


}