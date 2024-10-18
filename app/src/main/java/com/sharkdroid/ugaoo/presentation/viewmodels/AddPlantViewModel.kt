package com.sharkdroid.ugaoo.presentation.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.sharkdroid.ugaoo.domain.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseDatabase: FirebaseDatabase
) : ViewModel() {

    private val currentUser = firebaseAuth.currentUser

    private val userId = currentUser?.uid

    // store image in firebaseStorage for specific user
    private fun uploadPlantImage(
        imageUri: Uri,
        userId: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val storageRef =
            firebaseStorage.reference.child("plant_images/$userId/${UUID.randomUUID()}.jpg")

        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->

                    onSuccess(uri.toString())

                }
            }
            .addOnFailureListener { exception ->

                onFailure(exception)
            }

    }


    // store plant detail
    private fun storePlantDetails(plant: Plant) {

        val databaseRef =
            userId?.let { firebaseDatabase.getReference("users").child(it).child("plants") }

        val plantId = databaseRef?.push()?.key // Generate a unique ID for the plant

        if (plantId != null) {

            databaseRef.child(plantId).setValue(plant).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Log.d("Firebase", "Plant details stored successfully")

                } else {

                    Log.d("Firebase", "Failed to store plant details")

                }
            }
        }

    }

    // Bringing it all together
    fun addPlantForCurrentUser(
        imageUri: Uri,
        plantName: String,
        plantPrice: String,
        plantType: String
    ) {


        if (userId != null){

            uploadPlantImage(imageUri, userId = userId, onSuccess = { imageUrl ->

                val plant = Plant(name = plantName, price = plantPrice, type = plantType, imageUrl =imageUrl )

                storePlantDetails(plant)

            }, onFailure = { exception ->

                Log.d("Firebase", "Image upload failed: ${exception.message}")
            })
        }
    }


}