package mx.tecnm.tepic.ladm_u2_ejercicio7_coroutinescanvas2

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

import kotlin.random.Random.Default.nextLong

class Lienzo(este:MainActivity): View(este) {
    var este = este
    var circulos = Array<Circulos>(400,{Circulos(this@Lienzo)})

    var scoope = CoroutineScope(Job()+Dispatchers.Main)
    var oCorutine = scoope.launch(EmptyCoroutineContext,CoroutineStart.LAZY){}
    override fun onDraw(c: Canvas) {
        val fondo= Color.BLACK
        c.drawColor(fondo)
        super.onDraw(c)
        var p = Paint()
        for (i in 0..circulos.size-1){
            p.color = circulos[i].color
            c.drawCircle(circulos[i].cX.toFloat(), circulos[i].cY.toFloat(),20f,p)
        }

        Nenuko()
        invalidate()
    }
    fun Nenuko(){
        oCorutine.start()
        oCorutine = scoope.launch(EmptyCoroutineContext,CoroutineStart.LAZY) {

            for (i in 0..circulos.size-1){
                if (circulos[i].cX > 708.0){
                    circulos[i].sentidoX = false
                }
                if(circulos[i].cX < 5.0){
                    circulos[i].sentidoX = true
                }

                if (circulos[i].cY > 1300.0){
                    circulos[i].sentidoY = false
                }
                if(circulos[i].cY < 5.0){
                    circulos[i].sentidoY = true
                }



                if (circulos[i].sentidoX){
                    //circulos[i].cX=circulos[i].cX+circulos[i].movX
                    circulos[i].moverXD()
                    //circulos[0].cY=circulos[0].cY+circulos[0].movY
                }else{
                    //circulos[i].cX=circulos[i].cX-circulos[i].movX
                    circulos[i].moverXI()
                    //circulos[0].cY=circulos[0].cY+circulos[0].movY
                }
                if (circulos[i].sentidoY){
                    //circulos[0].cX=circulos[0].cX+circulos[0].movX
                    //circulos[i].cY=circulos[i].cY+circulos[i].movY
                    circulos[i].moverYD()
                }else{
                    //circulos[0].cX=circulos[0].cX+circulos[0].movX
                    //circulos[i].cY=circulos[i].cY-circulos[i].movY
                    circulos[i].moverYU()
                }
                //c.drawCircle(circulos[i].cX.toFloat(), circulos[i].cY.toFloat(),20f,p)
            }
            delay(3000L)
        }

    }
}


class Circulos(lienzo: Lienzo){
    //var li=lienzo
    var sentidoX = true
    var sentidoY = true
    val rgb1 = List(3) { Random.nextInt(0, 255) }
    val color = Color.rgb(rgb1[0],rgb1[1],rgb1[2])
    var cX= Random.nextDouble(5.0,708.0)
    var cY= Random.nextDouble(5.0,1300.0)
    val movX = Random.nextDouble(1.0,5.0)
    val movY = Random.nextDouble(1.0,5.0)
    fun moverXI(){
        cX=cX-movX
    }
    fun moverXD(){
        cX=cX+movX
    }
    fun moverYU(){
        cY=cY-movY
    }
    fun moverYD(){
        cY=cY+movY
    }

}