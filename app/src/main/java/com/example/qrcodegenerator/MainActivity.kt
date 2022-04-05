package com.example.qrcodegenerator

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var bitmap : Bitmap?=null
    var qrgCoder : QRGEncoder?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idBtnGenerateQr.setOnClickListener {
            if (TextUtils.isEmpty(idEdt.text.toString())){
                Toast.makeText(this,"Enter Some text to generate QR code",Toast.LENGTH_SHORT).show()
            }
            else{
                val manager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen  = if (width<height) width else height
                dimen = dimen *3/4
                qrgCoder = QRGEncoder(idEdt.text.toString(),null,QRGContents.Type.TEXT,dimen)

                try {
                    bitmap = qrgCoder!!.encodeAsBitmap()
                    idIVQrcode.setImageBitmap(bitmap)
                }catch (e:Exception){
                    Log.e("Tag",e.toString())
                }
            }
        }
    }
}