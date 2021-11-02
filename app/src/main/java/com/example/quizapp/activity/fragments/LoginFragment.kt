package com.example.quizapp.activity.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.quizapp.R
import com.example.quizapp.activity.cache.Cache
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private val CONTACT_PERMISSION_CODE = 1
    private val CONTACT_PICK_CODE = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        createUIElements(view)
        return view
    }

    private fun navigateTo(fragment: Fragment, string : String) {
        val bundle = Bundle()
        bundle.putString("string",string)
        fragment.arguments = bundle
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.popBackStackImmediate()
        fragmentManager?.beginTransaction()?.replace(R.id.main_fragment_container, fragment)?.commit()
    }

    private fun createUIElements(view : View) {
        val startButton = view.findViewById<Button>(R.id.started_button)
        val chooseFromContactButton = view.findViewById<Button>(R.id.choose_button)
        val inputName = view.findViewById<TextInputLayout>(R.id.fullname)
        val inputPassword = view.findViewById<TextInputLayout>(R.id.password)

        startButton.setOnClickListener {
            if(isValidName(inputName, inputName) && isValidPassword(inputPassword,inputPassword)) {
                Cache.setPlayerName(inputName.editText?.text.toString())
                navigateTo(QuizStartFragment(), inputName.editText?.text.toString())
            }
        }

        chooseFromContactButton.setOnClickListener {
            showSnackbar(inputName, "Choose from contact", Snackbar.LENGTH_SHORT)
            if (checkContactPermission()) {
                pickContact()
            } else {
                requestContactPermission()
            }
        }
    }

    private fun checkContactPermission() : Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(requireActivity(), permission, CONTACT_PERMISSION_CODE)
    }

    private fun pickContact() {
        val contactIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(contactIntent, CONTACT_PICK_CODE)
    }

    private fun isValidName(name: TextInputLayout, view: View) : Boolean {
        val value: String = name.editText?.text.toString()
        val whiteSpaces = "\\A\\w{4,20}\\z"

        return if (value.isEmpty()) {
            showSnackbar(view, "Username cannot be empty!", Snackbar.LENGTH_SHORT)
            false
        } else if (value.length >= 20) {
            showSnackbar(view, "Username too long!", Snackbar.LENGTH_SHORT)
            false
        } else if (!value.matches(whiteSpaces.toRegex())) {
            showSnackbar(view, "Invalid username!", Snackbar.LENGTH_SHORT)
            false
        } else {
            true
        }
    }

    private fun isValidPassword(inputPassword: TextInputLayout, view: View): Boolean {
        val value: String = inputPassword.editText?.text.toString()
        val passwordValue = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=!])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$"
        return if (value.isEmpty()) {
            showSnackbar(view, "Password cannot be empty!", Snackbar.LENGTH_SHORT)
            false
        } else if (!value.matches(passwordValue.toRegex())) {
            showSnackbar(view, "Password is to weak!", Snackbar.LENGTH_SHORT)
            false
        } else {
            true
        }
    }

    private fun showSnackbar(view : View, message : String, duration : Int) {
        Snackbar.make(view, message, duration).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickContact()
            } else {
                Toast.makeText(context, "Permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == CONTACT_PICK_CODE) {
                val cursor : Cursor
                val uri = data!!.data

                cursor = requireActivity().contentResolver.query(uri!!, null, null, null, null)!!

                if (cursor.moveToFirst()) {
                    val result = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    Cache.setPlayerName(result)
                    navigateTo(QuizStartFragment(), result)
                }
            }
        }
    }
}