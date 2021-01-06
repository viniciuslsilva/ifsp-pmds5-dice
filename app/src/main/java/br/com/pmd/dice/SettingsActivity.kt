package br.com.pmd.dice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.pmd.dice.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)
    }

    fun onSave(view: View) {
        val dicesAmount = activitySettingsBinding.etDicesAmount.text.toString()?.toInt()
        val facesAmount = activitySettingsBinding.etFacesAmount.text.toString()?.toInt()
        val shared = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

        with(shared.edit()) {
            putInt(getString(R.string.saved_dices_amount), dicesAmount)
            putInt(getString(R.string.saved_faces_amount), facesAmount)
            apply()
        }

        finish()
    }

}