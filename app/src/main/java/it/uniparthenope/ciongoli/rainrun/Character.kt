package it.uniparthenope.ciongoli.rainrun

class Character {
    var speedy: Double = 0.0
    var posy=0.0
    val gravity: Double =1.3
    val initial_sp=-30.0
    fun jump() {
        speedy = initial_sp}
    fun frame(){
        speedy = speedy+gravity
        posy = posy+speedy
        if (posy > 0.0) {
            posy= 0.0
            speedy = 0.0
        }
    }
    fun reset(){
         speedy = 0.0
         posy=0.0
    }
    }
