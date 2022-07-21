package com.promedia.imc_calculator

import android.widget.SeekBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
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
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                b.tv150200.text = progress.toString().plus("/200")
                height = progress
            }

            override fun onStartTrackingTouch(seek: SeekBar?) {}
            override fun onStopTrackingTouch(seek: SeekBar?) {
                calcIMC()
            }
        })

        b.sBarPeso.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                b.tv75150.text = progress.toString().plus("/150")
                weight = progress
            }

            override fun onStartTrackingTouch(seek: SeekBar?) {}
            override fun onStopTrackingTouch(seek: SeekBar?) {
                calcIMC()
            }
        })

    //        b.imageView.setOnClickListener { showTable() }
    }

    fun alertCustom(view:View){
        val inflater = this@MainActivity!!.layoutInflater
        val custom_layout = inflater.inflate(R.layout.layout_dialog, null)

        AlertDialog.Builder(this@MainActivity!!)
            .setView(custom_layout)
            .setPositiveButton(R.string.tvAceptar, null)
            .show()
    }

    fun calcIMC() {
        doubleHeight = height.times(height)/10000.0
        IMC = Math.round((weight/doubleHeight)
            .times(100))
            .div(100.0)
        b.tvResultado.text = IMC.toString()
        calcObesidad()
    }
    fun calcObesidad() {
        val msj = when (IMC){
            in 0.0..15.99 -> "Delgadez Severa"
                in 16.00..16.99 -> "Delgadez Moderada"
                in 17.00..18.49 -> "Delgadez Leve"
                in 18.50..24.99 -> "Peso Normal"
                in 25.00..29.99 -> "Preobesidad"
                in 30.00..34.99 -> "Obesidad Leve"
                in 35.00..39.99 -> "Obesidad Media"
                else -> "Obesidad Mórbida"
        }
        val color = when (IMC){
            in 0.0..15.99 -> R.color.dark_blue
            in 16.00..16.99 -> R.color.blue
            in 17.00..18.49 -> R.color.sky_blue
            in 18.50..24.99 -> R.color.green
            in 25.00..29.99 -> R.color.lima
            in 30.00..34.99 -> R.color.cake_orange
            in 35.00..39.99 -> R.color.orange
            else -> R.color.red
        }

        val s = Snackbar.make(b.root, msj, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(ContextCompat.getColor(this, color))
        .setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
        .setAction("Ver Tabla") { showTable() }
        s.show()
    }
    fun showTable(){
        val dialog = TablaIMC_Fragment()
        dialog.show(supportFragmentManager, "TablaPeso")
    }
}









