package br.com.pmd.dice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import br.com.pmd.dice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }

    fun onClick(view: View) {
        val shared = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

        val dicesAmount = shared.getInt(getString(R.string.saved_dices_amount), 1)
        val facesAmount = shared.getInt(getString(R.string.saved_faces_amount), 6)

        if (view == activityMainBinding.jogarBt) {
            activityMainBinding.llDices.removeAllViews()
            for (i in 1..dicesAmount) {
                val resultado: Int = Random(System.currentTimeMillis()).nextInt(facesAmount) + 1;

                activityMainBinding.resultadoTv.text = activityMainBinding.resultadoTv.text.let { "$it  $resultado" }
                val resultadoImagem = "dice_$resultado"

                val iv = ImageView(this)
                iv.setImageResource(resources.getIdentifier(resultadoImagem, "drawable", packageName))
                activityMainBinding.llDices.addView(iv)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_settings -> {
                openSettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}