package com.example.naturalgasdetector

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    private var isListQMoved = false
    private var isListCMoved = false
    private var isListAMoved = false
    private var isButtonMoved = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        //Instancia y declaracion de botones y texto de la lista de información. [BTI]

        val listQ = findViewById<Button>(R.id.listQ)
        val listC = findViewById<Button>(R.id.listC)
        val listA = findViewById<Button>(R.id.listA)
        val textQ = findViewById<TextView>(R.id.textQ)
        val textC = findViewById<TextView>(R.id.textC)

        //Instancia y declaracion de sub botones de la lista. [SBI]

        val listJD = findViewById<Button>(R.id.listJD)
        val listJG = findViewById<Button>(R.id.listJG)
        val listLM = findViewById<Button>(R.id.listLM)
        val listAA = findViewById<Button>(R.id.listAA)
        val listJJ = findViewById<Button>(R.id.listJJ)
        val listCR = findViewById<Button>(R.id.listCR)

        //Instancia y declaracion de textos e informacion de los sub botones de la lista [TSBL]

        val textJD = findViewById<TextView>(R.id.textJD)
        val textJG = findViewById<TextView>(R.id.textJG)
        val textLM = findViewById<TextView>(R.id.textLM)
        val textAA = findViewById<TextView>(R.id.textAA)
        val textJJ = findViewById<TextView>(R.id.textJJ)
        val textCR = findViewById<TextView>(R.id.textCR)

        val btnSabMas = findViewById<Button>(R.id.btnSabMas)


        //Codigo de los botones [BTI]

        listQ.setOnClickListener {
            if (!isListQMoved) {
                val translationValue = -150f
                val animacion = ObjectAnimator.ofFloat(listQ, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listC.animate().alpha(0f).setDuration(300).start()
                listC.isClickable = false
                listA.animate().alpha(0f).setDuration(300).start()
                listA.isClickable = false

                textQ.alpha = 0f
                textQ.visibility = View.VISIBLE
                textQ.animate().alpha(1f).setDuration(300).start()

            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listQ, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listC.animate().alpha(1f).setDuration(300).start()
                listC.isClickable = true
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                textQ.animate().alpha(0f).setDuration(300).withEndAction {
                    textQ.visibility = View.INVISIBLE
                }.start()
            }
            isListQMoved = !isListQMoved
        }

        listC.setOnClickListener {
            if (!isListCMoved) {
                val translationValue = -310f
                val animacion = ObjectAnimator.ofFloat(listC, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listQ.animate().alpha(0f).setDuration(300).start()
                listQ.isClickable = false
                listA.animate().alpha(0f).setDuration(300).start()
                listA.isClickable = false

                textC.alpha = 0f
                textC.visibility = View.VISIBLE
                textC.animate().alpha(1f).setDuration(300).start()

            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listC, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listQ.animate().alpha(1f).setDuration(300).start()
                listQ.isClickable = true
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                textC.animate().alpha(0f).setDuration(300).withEndAction {
                    textC.visibility = View.INVISIBLE
                }.start()
            }
            isListCMoved = !isListCMoved
        }

        listA.setOnClickListener {
            if (!isListAMoved) {
                val translationValue = -470f
                val animacion = ObjectAnimator.ofFloat(listA, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listQ.animate().alpha(0f).setDuration(300).start()
                listQ.isClickable = false
                listC.animate().alpha(0f).setDuration(300).start()
                listC.isClickable = false

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listA, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listQ.animate().alpha(1f).setDuration(300).start()
                listQ.isClickable = true
                listC.animate().alpha(1f).setDuration(300).start()
                listC.isClickable = true

                listJD.animate().alpha(0f).setDuration(300).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(300).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(300).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(300).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(300).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(300).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()
            }
            isListAMoved = !isListAMoved
        }

        //Codigo de los sub botones [SBI]

        //JD, JG, LM, AA, JJ, CR

        listJD.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -160f
                val animacion = ObjectAnimator.ofFloat(listJD, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(200).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(200).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(200).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(200).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()

                textJD.alpha = 0f
                textJD.visibility = View.VISIBLE
                textJD.animate().alpha(1f).setDuration(300).start()
                textJD.isClickable = true


            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listJD, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

                textJD.animate().alpha(0f).setDuration(200).withEndAction {
                    textJD.visibility = View.INVISIBLE
                    textJD.isClickable = false
                }.start()

            }
            isButtonMoved = !isButtonMoved
        }

        listJG.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -270f
                val animacion = ObjectAnimator.ofFloat(listJG, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJD.animate().alpha(0f).setDuration(200).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(200).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(200).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(200).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()

                //Cambiar por la ID respectiva

                textJG.alpha = 0f
                textJG.visibility = View.VISIBLE
                textJG.animate().alpha(1f).setDuration(300).start()
                textJG.isClickable = true

            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listJG, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

                textJG.animate().alpha(0f).setDuration(200).withEndAction {
                    textJG.visibility = View.INVISIBLE
                    textJG.isClickable = false
                }.start()

            }
            isButtonMoved = !isButtonMoved
        }

        listLM.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -385f
                val animacion = ObjectAnimator.ofFloat(listLM, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(200).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listJD.animate().alpha(0f).setDuration(200).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(200).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(200).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()

                textLM.alpha = 0f
                textLM.visibility = View.VISIBLE
                textLM.animate().alpha(1f).setDuration(300).start()
                textLM.isClickable = true


            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listLM, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

                textLM.animate().alpha(0f).setDuration(200).withEndAction {
                    textLM.visibility = View.INVISIBLE
                    textLM.isClickable = false
                }.start()

            }
            isButtonMoved = !isButtonMoved
        }

        listAA.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -500f
                val animacion = ObjectAnimator.ofFloat(listAA, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(200).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listJD.animate().alpha(0f).setDuration(200).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(200).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(200).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()

                textAA.alpha = 0f
                textAA.visibility = View.VISIBLE
                textAA.animate().alpha(1f).setDuration(300).start()
                textAA.isClickable = true

                btnSabMas.alpha = 0f
                btnSabMas.visibility = View.VISIBLE
                btnSabMas.animate().alpha(1f).setDuration(300).start()
                btnSabMas.isClickable = true


            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listAA, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

                textAA.animate().alpha(0f).setDuration(200).withEndAction {
                    textAA.visibility = View.INVISIBLE
                    textAA.isClickable = false
                }.start()

                btnSabMas.animate().alpha(0f).setDuration(200).withEndAction {
                    btnSabMas.visibility = View.INVISIBLE
                    btnSabMas.isClickable = false
                }

            }
            isButtonMoved = !isButtonMoved
        }

        listJJ.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -610f
                val animacion = ObjectAnimator.ofFloat(listJJ, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(200).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listJD.animate().alpha(0f).setDuration(200).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(200).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(200).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listCR.animate().alpha(0f).setDuration(200).withEndAction {
                    listCR.visibility = View.INVISIBLE
                    listCR.isClickable = false
                }.start()

                textJJ.alpha = 0f
                textJJ.visibility = View.VISIBLE
                textJJ.animate().alpha(1f).setDuration(300).start()
                textJJ.isClickable = true


            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listJJ, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listCR.alpha = 0f
                listCR.visibility = View.VISIBLE
                listCR.animate().alpha(1f).setDuration(300).start()
                listCR.isClickable = true

                textJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    textJJ.visibility = View.INVISIBLE
                    textJJ.isClickable = false
                }.start()

            }
            isButtonMoved = !isButtonMoved
        }

        listCR.setOnClickListener {
            if (!isButtonMoved) {
                val translationValue = -720f
                val animacion = ObjectAnimator.ofFloat(listCR, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.animate().alpha(0f).setDuration(200).withEndAction {
                    listA.visibility = View.INVISIBLE
                    listA.isClickable = false
                }.start()

                listJG.animate().alpha(0f).setDuration(200).withEndAction {
                    listJG.visibility = View.INVISIBLE
                    listJG.isClickable = false
                }.start()

                listJD.animate().alpha(0f).setDuration(200).withEndAction {
                    listJD.visibility = View.INVISIBLE
                    listJD.isClickable = false
                }.start()

                listLM.animate().alpha(0f).setDuration(200).withEndAction {
                    listLM.visibility = View.INVISIBLE
                    listLM.isClickable = false
                }.start()

                listAA.animate().alpha(0f).setDuration(200).withEndAction {
                    listAA.visibility = View.INVISIBLE
                    listAA.isClickable = false
                }.start()

                listJJ.animate().alpha(0f).setDuration(200).withEndAction {
                    listJJ.visibility = View.INVISIBLE
                    listJJ.isClickable = false
                }.start()

                textCR.alpha = 0f
                textCR.visibility = View.VISIBLE
                textCR.animate().alpha(1f).setDuration(300).start()
                textCR.isClickable = true


            } else {
                val translationValue = 0f
                val animacion = ObjectAnimator.ofFloat(listCR, "translationY", translationValue)
                animacion.duration = 300
                animacion.interpolator = AccelerateDecelerateInterpolator()
                animacion.start()

                listA.alpha = 0f
                listA.visibility = View.VISIBLE
                listA.animate().alpha(1f).setDuration(300).start()
                listA.isClickable = true

                listJG.alpha = 0f
                listJG.visibility = View.VISIBLE
                listJG.animate().alpha(1f).setDuration(300).start()
                listJG.isClickable = true

                listJD.alpha = 0f
                listJD.visibility = View.VISIBLE
                listJD.animate().alpha(1f).setDuration(300).start()
                listJD.isClickable = true

                listLM.alpha = 0f
                listLM.visibility = View.VISIBLE
                listLM.animate().alpha(1f).setDuration(300).start()
                listLM.isClickable = true

                listAA.alpha = 0f
                listAA.visibility = View.VISIBLE
                listAA.animate().alpha(1f).setDuration(300).start()
                listAA.isClickable = true

                listJJ.alpha = 0f
                listJJ.visibility = View.VISIBLE
                listJJ.animate().alpha(1f).setDuration(300).start()
                listJJ.isClickable = true

                textCR.animate().alpha(0f).setDuration(200).withEndAction {
                    textCR.visibility = View.INVISIBLE
                    textCR.isClickable = false
                }.start()

            }
            isButtonMoved = !isButtonMoved
        }


        //Codigo de los textos de los sub botones [TSBL]

        btnSabMas.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tecnologiaypedagogia.net/p/lic.html"))
            startActivity(intent)
        }


        //Botones del menú

        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        btnHome.setOnClickListener {
            entrarHome()
        }

        val btnPanel = findViewById<ImageButton>(R.id.btnPanel)
        btnPanel.setOnClickListener {
            entrarPanel()
        }

        val btnInfo = findViewById<ImageButton>(R.id.btnInfo)
        btnInfo.setOnClickListener {
            entrarInfo()
        }
    }

    //Metodos del menú

    private fun entrarInfo() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun entrarHome() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun entrarPanel() {
        val intent = Intent(this, PanelActivity::class.java)
        startActivity(intent)
    }
}