package com.example.androidmagicguild
import com.google.firebase.database.*

class PetAdapter {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    private lateinit var databaseReference: DatabaseReference

    private val array = mutableListOf<Pet?>()

    constructor() {retrieveData()}

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

    fun returnArray(): MutableList<Pet?> {
        return this.array

    }

    fun arraySize(): Int {
        return this.array.size
    }


    fun printList() {

        for(post in array) {
            System.out.println(post)
        }

    }

}