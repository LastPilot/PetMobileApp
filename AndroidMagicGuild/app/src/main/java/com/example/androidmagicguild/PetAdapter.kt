package com.example.androidmagicguild
import android.widget.ImageView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso


class PetAdapter {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    private lateinit var databaseReference: DatabaseReference
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference


    private val array = mutableListOf<Pet?>()

    fun add(pet: Pet?) {
        databaseReference = database.reference
        var key = databaseReference.child("allPets").push().key
        var ref = databaseReference.child("allPets/$key")
        val childUpdates = mapOf("bio" to pet?.bio, "contact" to pet?.contact,
                                 "name" to pet?.name, "type" to pet?.type,
                                 "zip" to pet?.zipCode)
        ref.updateChildren(childUpdates)
    }

    //This method will display an image of a pet inside a view. Requires a view and the name of the pet
    fun setImageOnView(name: String?, petImageView: ImageView) {
        var imageRef = storageRef.child("${name}.jpg")
        imageRef.downloadUrl.addOnSuccessListener { uri ->
            Picasso.get().load(uri).into(petImageView)
        }.addOnFailureListener {
            // Handle any errors
        }
    }

    //this method will retrieve the data from the firebase at the start of the app process. Only to be called once
    fun retrieveData() {
        databaseReference = database.reference
        var query: Query = databaseReference.child("allPets")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(singleSnapShot in dataSnapshot.children) {
                    val post = singleSnapShot.getValue(Pet::class.java)
                    if(post !in array) {
                        array.add(post)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) { }
        })

    }

    //This will return the array of pets.
    fun returnArray(): MutableList<Pet?> {
        return this.array

    }

    //this will return the size of the array
    fun arraySize(): Int {
        return this.array.size
    }

}