package com.example.androidmagicguild
import android.net.Uri
import android.widget.ImageView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

val copy = mutableListOf<Pet?>()

class PetAdapter {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    private lateinit var databaseReference: DatabaseReference
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private var useCopy = false


    private var array = mutableListOf<Pet?>()

    fun add(pet: Pet?, imgUri: Uri) {
        databaseReference = database.reference
        var key = databaseReference.child("allPets").push().key
        var ref = databaseReference.child("allPets/$key")
        val childUpdates = mapOf("bio" to pet?.bio, "contact" to pet?.contact,
                                 "name" to pet?.name, "type" to pet?.type,
                                 "zip" to pet?.zip)
        ref.updateChildren(childUpdates)
        array.add(pet)

        val refStore = storageRef.child("${pet?.name}")
        refStore.putFile(imgUri)
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

    fun sort() {
        val sortedList = array.sortedWith(compareBy({it?.zip}))
        array = sortedList.toMutableList()

    }

    fun sortBy(zip: Int?) {
        copy.clear()
        for(pet in array){
            System.out.println(pet?.zip)
            if(pet?.zip == zip) {
                copy.add(pet)
            }
        }
        if(copy.size > 0) {
            useCopy = true
        }
        else{
            useCopy = false
        }
    }

    fun reset() {
        useCopy = false
    }

    //This will return the array of pets.
    fun returnArray(): MutableList<Pet?> {
        if(useCopy) return copy
        return array
    }

    //this will return the size of the array
    fun arraySize(): Int {
        if(useCopy) return copy.size
        return this.array.size
    }

}