package com.p

import android.graphics.Color
import android.widget.SeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.promedia.imc_calculator.R
import com.promedia.imc_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private var height = 150
    private var weight = 75
    private var doubleHeight = 2.25  //
    private var IMC = 33.33
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.sBarAltura.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int,
                fromUser: Boolean) {
                b.tv150200.text = progress.toString().plus("/200")
                height = progress
            }
            override fun onStartTrackingTouch(seek: SeekBar?) {}
            override fun onStopTrackingTouch(seek: SeekBar?) {calcIMC()}
        })

        b.sBarPeso.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int,
                fromUser: Boolean) {
                b.tv75150.text = progress.toString().plus("/150")
                weight = progress
            }
            override fun onStartTrackingTouch(seek: SeekBar?) {}
            override fun onStopTrackingTouch(seek: SeekBar?) {calcIMC()}
        })

//        b.imageView.setOnClickListener { showTable() }
    }

    fun calcIMC() {
        doubleHeight = height.times(height)/10000.0
        IMC = Math.round((weight/doubleHeight)
            .times(100))
            .div(100.0)
        b.tvResultado = IMC.toString()
        calcObesidad()
    }
    fun calcObesidad() {


    }

    fun mySnackBarCustom (view: View){
        val s = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        val sbLayout = s.view as Snackbar.SnackbarLayout
        val customLayout = layoutInflater.inflate(R.layout.toast, null)
        var msj = when (IMC){
            in 0.0..16.0 -> "Delgadez Severa"
                in 16.01..16.99 -> "Delgadez Moderada"
                in 17.00..18.49 -> "Delgadez Leve"
                in 18.50..24.99 -> "Peso Normal"
                in 25.00..29.99 -> "Preobesidad"
                in 30.00..34.99 -> "Obesidad Leve"
                in 35.00..40.00 -> "Obesidad Media"
                in > 40.01 -> "Obesidad Mórbida"
        }
        var color = when (IMC){
            in 0.0..16.0 -> R.color.dark_blue
            in 16.01..16.99 -> R.color.blue
            in 17.00..18.49 -> R.color.sky_blue
            in 18.50..24.99 -> R.color.green
            in 25.00..29.99 -> R.color.lima
            in 30.00..34.99 -> R.color.cake_orange
            in 35.00..40.00 -> R.color.orange
            in > 40.01 -> R.color.red
        }

            customLayout!!.findViewById<TextView>(R.id.tvToastTitle).text = "VER TABLA"

        sbLayout.addView(customLayout}, 0)
        s.setBackgroundTint(color)
        s.show()
    }
    fun alertDialog(view:View){
        AlertDialog.Builder(view.context)
            .setPositiveButton("Aceptar") { dialog, id -> /* Acción */ }
            .show()

    }
}





