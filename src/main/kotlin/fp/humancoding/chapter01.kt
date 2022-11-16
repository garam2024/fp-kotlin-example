package fp.humancoding

/*
* 프로그래밍 패러다임
* 절차형 => 객체지향 => 객체지향 + 관점지향 => 객체지향 + 함수형 => 객체지향 + 함수/반응형
* */

/*
* 1급 함수 : 함수를 하나의 타입/자료형으로써 표현이 가능하다
* 1. 변수에 저장
* 2. 인자로 전달
* 3. 반환값으로 전달
* 4. 런타임 시점에 생성 가능
* 5. 익명으로/ 임의로 생성 가능
* */

/*
* HOF : Higher Order Functions (고차함수)
* 함수타입으로 인자를 받거나 함수타입으로 리턴값을 되돌려주는 함수
* 즉 함수를 데이터처럼 소비하거나 함수를 생산 할 수 있음
* */

/*
* 익명함수
* 식처럼 변수에 담을 수 있다
* fun (param : type, ...) {...}
*
* 람다식
* {argument(s) -> code block as the function body}
* 평가될 수 있는 마지막 식이 있다
* */


/* {HC} */
fun myFun(p:()->Unit) = p()

fun takePrintNameFunction(name : String, pnFun : (String)->Unit ){
    val local : (String) -> Unit = pnFun
    local(name)
}

fun supplyPrintNameFunction():(String)-> Unit{
    return {name2 : String -> println(name2)}
}
//호출만 하면 함수가 만들어짐

//고차함수인데 함수를 인자로 받아서 실행한다
fun main (args:Array<String>){


    myFun { println("hello") }

    //1. 변수에 담을 수 있음
    val printName : (String) -> Unit = fun(name : String){
        println("your name is $name!")
    }
    //익명 함수를 변수에 담음
    printName("garam")
    printName("anna")
    printName("printName.javaClass = ${printName.javaClass}")

    val pName2 : (String) -> Unit = printName
    pName2("printcess Ellsa")
    val pName3 : (String) -> Unit = {name: String -> println("your name is $name") }
    //람다식을 변수에 넣었다
    pName3("garam")


    //2. 인자로 전달할 수 있음
    takePrintNameFunction("David", pName2)
    takePrintNameFunction("Lilly") { name: String -> println(name) }
    takePrintNameFunction("hello", fun(n:String){println(n)})

    //3. 반환값으로 리턴할 수 있음
    takePrintNameFunction("garam", supplyPrintNameFunction())
    supplyPrintNameFunction()("HoHoHo")
    val pName4 : (String)-> Unit = supplyPrintNameFunction()
    pName4("last man")

    listOf<Int>(1,2,3,4,5,6,7)
        .showNumbersToString(pre = {n-> n% 2==1}, con = concats)

    (1..7).toList().showNumbersToString(
        pre = fun(n:Int) = n %2 ==0 , con = concats
    )


}

//숫자 리스트 확장 함수
fun List<Int>.showNumbersToString(
    pre : (Int)-> Boolean, con : (List<Int>,String)-> String
){
    val list = arrayListOf<Int>()
    for (n in this ){
        if(pre(n)) list.add(n)
    }
    println(con(list, ","))
}

val concats : (List<Int>, String)-> String =
    fun(ints : List<Int>, sep: String):String{
    var s = ""
    for ((i:Int, n:Int) in ints.withIndex()){
        if(i<ints.lastIndex) s +="$n$sep"
        else s +=n
    }
        return s
    }

//함수를 그냥 변수 간단하게 표시하고 싶어서 익명함수?


