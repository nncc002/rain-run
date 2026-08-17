package it.uniparthenope.ciongoli.rainrun

class Character {
    var speed_y: Double = 0.0
    var pos_y=0.0
    val gravity: Double =1.5
    val initial_sp=-20.0
    fun jump() {
        speed_y = initial_sp}
    fun frame(){
        speed_y = speed_y+gravity
        pos_y = pos_y+speed_y
        if (pos_y > 0.0) {
            pos_y= 0.0
            speed_y = 0.0
        }
    }
    }
