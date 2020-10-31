package com.example.cronometro


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*


enum class ProviderType {
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnNueva.setOnClickListener {

            val intent:Intent = Intent(this, AgendarActividades::class.java)
            startActivity(intent)
        }

        btnCronometro.setOnClickListener {

            val intent:Intent = Intent(this, ActivityCronometro::class.java)
            startActivity(intent)
        }

        btnCalendario.setOnClickListener {

            val intent:Intent = Intent(this, VerEventos::class.java)
            startActivity(intent)
        }

        //Setup
        val bundle = intent.extras
        val email =  bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Guardado de datos

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }





    private fun setup(email: String, provider: String) {

        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {

            //Borrado de datos

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
           val intent:Intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}