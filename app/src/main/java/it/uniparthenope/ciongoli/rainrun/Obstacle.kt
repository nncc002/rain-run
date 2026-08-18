package it.uniparthenope.ciongoli.rainrun

class Obstacle {
    var pos_x=0.0
    val speed=-8.0
    fun frame(){
        pos_x=pos_x+speed
        if(pos_x<-2000.0){
            pos_x=0.0
        }
    }
}