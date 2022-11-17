package fp.humancoding



/*
CLOSURE, FUNCTORS & STDLIB FUNCTIONS

closure; let()
functor; map()
stdlib functions : run(), with(), apply(), also(), ...


 */

class StandardFunctions{
    fun letFunc() : Unit{
        //확장함수 , 오브젝트 자체가 확장함수의 리시버 오브젝트처럼 it 으로 넘어 온다
        //람다식을 수행해서 평가한다음 마지막 람다식의 값을 리턴한다
        val obj  = "ABCDE"
        var  count = 0
        val result : Char = obj.let{
            count++
            println("[$count] in the let() function : ")
            println("this = $this") //this = fp.humancoding.StandardFunctions@3d8c7aca
            //여기서 this 는 Class 를 나타낸다
            println("this@StandardFunctions = ${this@StandardFunctions}")
            println("it = $it")
            it[0]
            //println("obj => $obj and count -> $count \n\n")
          //  'A'
        }
        println("result => $result and count -> $count and obj => $obj\n\n")
    }
    fun withFunc() : Unit {

    }
    fun applyFlunc() : Unit {

    }
    fun runFlunc() : Unit {

    }
    fun alsoFunc() : Unit {

    }



}

fun main(args: Array<String>){
    println("letFunc return : "+StandardFunctions().letFunc())//letFunc return : kotlin.Unit
}