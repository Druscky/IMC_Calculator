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

        customLayout!!.findViewById<TextView>(R.id.tvToastTitle).text = "VER TABLA"

        sbLayout.addView(customLayout, 0)
        s.setBackgroundTint(Color.YELLOW)
        s.show()
    }
    fun alertDialog(view:View){
        AlertDialog.Builder(view.context)
            .setPositiveButton("Aceptar") { dialog, id -> /* Acción */ }
            .show()

    }
}





