package com.example.androidmagicguild

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.androidmagicguild.databinding.MakeProfileBinding
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import java.io.*
import java.util.*


class MakeProfileFragment: Fragment(), AdapterView.OnItemSelectedListener {
    private var pet_type: String = "Dog"
    private lateinit var imgBitmap: Bitmap
    private var imgUri: Uri? = null

    val petTypes = arrayOf("Dog", "Cat", "Bird", "Reptile", "Other")

    lateinit var binding: MakeProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.make_profile, container, false)

        val adapt = ArrayAdapter(this.context!!,android.R.layout.simple_spinner_item,petTypes)

        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.petType.adapter = adapt
        binding.petType.setOnItemSelectedListener(this)

        //SET AutoFIll to get rid of annoying warnings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.petNameEdit.setAutofillHints(View.AUTOFILL_HINT_NAME)
            binding.ownerEmail.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS)
            binding.ownerNumber.setAutofillHints(View.AUTOFILL_HINT_PHONE)
            binding.ownerZip.setAutofillHints(View.AUTOFILL_HINT_POSTAL_CODE)
        }

        binding.takePhoto.setOnClickListener{view: View ->
                dispatchTakePictureIntent(1)
        }
        binding.submitBt.setOnClickListener { view: View ->
            buttonCheck(view)
        }

        return binding.root
    }

    private fun dispatchTakePictureIntent(actionCode: Int) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, actionCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleSmallCameraPhoto(data)

    }

    private fun handleSmallCameraPhoto(intent: Intent?) {
        val extras = intent?.extras
        imgBitmap = extras!!.get("data") as Bitmap
        binding.petPicture.setImageBitmap(imgBitmap)

    }
    // Method to save an bitmap to a file
    private fun getImageFile(bitmap:Bitmap): File {
        // Initialize a new file instance to save bitmap object
        var file = context?.getDir("Images",Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        try{
            // Compress the bitmap and save in jpg format
            val stream:OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()
        }

        // Return the saved bitmap uri
        return file
    }


    private fun buttonCheck(view: View) {
        var readyForSubmission = true
        var error = ""
        var contactZip = 0
        var name = ""
        var bio = ""
        var contactInfo = "No Contact Info Entered"


        if(binding.petNameEdit.text.toString().equals("") || binding.ownerZip.text.toString().equals("")){
            readyForSubmission = false
            //Inform user that name and zip is required
            error =  "Name and ZipCode required"
            binding.erroMessage.text = error
        }
        else{
            name = binding.petNameEdit.text.toString()
            contactZip = binding.ownerZip.text.toString().toInt()
        }

        if(binding.ownerEmail.text.toString().equals("") && binding.ownerNumber.text.toString().equals("")){
            readyForSubmission = false
            //Inform user that either an email or phone is needed
            error += "\nNeed Email/or Phone"
            binding.erroMessage.text = error

        }
        else{
            if(!(binding.ownerEmail.text.toString().equals(""))){
                contactInfo = "Email: " + binding.ownerEmail.text.toString()
            }
            if(!(binding.ownerNumber.text.toString().equals(""))){
                contactInfo += "\nPhone#: " + binding.ownerNumber.text.toString()
            }
        }

        if(readyForSubmission){
            val pet = Pet(name, pet_type, bio, contactInfo, contactZip)
            petAdapter.add(pet, getImageFile(imgBitmap))
            view.findNavController().navigate(R.id.action_makeProfileFragment_to_homePage)
        }
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        when (position) {
            0 -> pet_type = "Dog"
            1 -> pet_type = "Cat"
            2 -> pet_type = "Bird"
            3 -> pet_type = "Reptile"
            else -> {
                pet_type = "Other"
            }
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


}
