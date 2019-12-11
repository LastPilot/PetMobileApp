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
import android.os.Environment
import android.provider.MediaStore.Images
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
    private fun getImageUri(bitmap:Bitmap): Uri {
        // Initialize a new file instance to save bitmap object
        val filePath = Environment.DIRECTORY_PICTURES
        val dir = File(filePath)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(dir, "${UUID.randomUUID()}" + ".jpeg")
        val fOut = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
        fOut.flush()
        fOut.close()

        return Uri.parse(file.path)
    }


    private fun buttonCheck(view: View) {
        var name = binding.petName.text.toString()
        var bio = binding.editBio.text.toString()
        var contactEmail = binding.emailEdit.text.toString()
        var contactPhone = binding.phoneEdit.text.toString()
        var contactZip = binding.zipEdit.text.toString().toInt()
        val pet = Pet(name, pet_type, bio, contactEmail, contactZip)
        petAdapter.add(pet, getImageUri(imgBitmap))
        view.findNavController().navigate(R.id.action_makeProfileFragment_to_homePage)
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
