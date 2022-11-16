package fp.humancoding


/*
Single Lambda Argument, it:

only one parameter for lambda expression (문맥에서 유추/ 추론)
람다 파라미터 정의를 생략하고 그냥 it 키워드로 인자를 사용
함수형으로 유명한 LINQ style 로 표현, readability 향상

Function Reference, Import As & Single Lambda Argument, It

Function Reference : 일종의 reflection, 함수 자체의 이름으로 함수 바디를 객체로써 참조함

Method Reference : ClassName :: FunctionName ; String :: isNotEmpty

import as : for name collision, locally rename that as another one
import my.Name // Name is accessible
import your.Name as yName //
yName is accessible for other Name type
*/

//DICE
fun getNumberInt(max: Int) = (Math.random() * max).toInt() + 1

fun getNumberInt2(max: Int) = java.util.Random().nextInt(max) + 1

fun getNumberStream(max: Int) = java.util.Random().ints(1, max)

//.findFirst().asInt //스트림이여서 가장 앞에 있는 요소 리턴
fun getNumberHedron(hed: Hedron) = (Math.random() * hed.faces).toInt() + 1
enum class Hedron(val faces: Int) {
    Tetrahedron(4), Cube(6), Octahedron(8), Dodecahedron(12)
}

//typealias  HedronToInt = (Hedron) -> Int
typealias  HedRandom = (Hedron) -> Int
//함수 타입을 간편하게 표현

class Dice2(val size: Hedron) {
    fun cast(ranfun: (Int) -> Int) = ranfun(size.faces)

    //generate Any Class에서 상속받은 메소드 오버라이딩
    override fun toString(): String {
        return "Dice(${size.name}) casts ${getNumberInt(size.faces)}"
    }

}

data class Dice(
    val size: Hedron
) {
    fun cast(ranFun: (Int) -> Int) = ranFun(size.faces)

    //fun castHedron(ranFun: (Hedron) -> Int) = ranFun(size)
    fun castHedron(ranFun: HedRandom) = ranFun(size)
    override fun toString(): String {
        //return "Dice(${size.name}) casts ${getNumberInt(size.faces)}"
        // return "Dice(${size.name}) casts ${cast(fun (n:Int) = (Math.random() * n).toInt() + 1)}"//익명함수
        // return "Dice(${size.name}) casts ${cast{ (Math.random() * size.faces).toInt() + 1 }}"//익명함수
        // return "Dice(${size.name}) casts ${cast(::getNumberInt)}"// 함수 레퍼런스
        //return "Dice(${size.name}) casts ${cast(::getNumberStream)}"// 함수 레퍼런스
        return "Dice(${size.name}) casts ${castHedron(::getNumberHedron)}"

    }
}
//함수 선언과 함수 본체를 분리할 수 있구나
//익명함수, 람다식, 변수 로

val x = 1024
fun isOdd(x: Int): Boolean {
    return x % 2 != 0
}

fun isEven(x: Int): Boolean {
    return x % 2 == 0
}

fun main(args: Array<String>) {
    val dices = listOf<Dice>(
        Dice(Hedron.Tetrahedron),
        Dice(Hedron.Cube),
        Dice(Hedron.Octahedron),
        Dice(Hedron.Dodecahedron)
    )
    println("\n 0. for loop iteration")
    for (dice in dices) {
        println(dice.toString())
    }

    println(x)
    println(::x.name)
    val numList = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("홀수값 : ${numList.filter { isOdd(it) }}")
    println("홀수값 : ${numList.filter(::isOdd)}")
    println(getNumberStream(7))
    println("\n 1. anonymous function parmeter")
    dices.forEach(fun(dice: Dice) { println(dice.toString()) })
    println("\n 2. lambda expression parmeter")
    dices.forEach ( {dice -> println(dice.toString()) })
    println("\n 3. last parameter to external lambda block")
    dices.forEach() { dice -> println(dice.toString()) }
    println("\n 4. removing for single argument lambda block")
    dices.forEach { dice -> println(dice.toString()) }
    println("\n 5. single argument 'it' for lambda expression")
    dices.forEach { println(it.toString()) }
    println("\n 6. function reference of kotlin.io::println")
    dices.forEach ( ::println )
    //함수를 인자로 받기 때문에 블럭이 아니라 괄호로 감싼다
    //함수를 인자로 받으면 함수의 주소가 아니라 내용(바디,식)이 들어오게 된다
    println("\n 7. custom function reference of ::printDice")
    dices.forEach (::printDice)
    println("\n 8. function reference of System.out::println")
    dices.forEach(System.out::println)
}

fun printDice(d: Dice) = println(d)