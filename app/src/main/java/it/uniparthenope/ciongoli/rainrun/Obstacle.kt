package it.uniparthenope.ciongoli.rainrun

class Obstacle {
    var posx=0.0
    val speed=-10.0
    fun frame(){
        posx=posx+speed
        if(posx<-1500.0){
            posx=0.0
        }
    }
    fun reset(){
        posx=0.0
    }
}