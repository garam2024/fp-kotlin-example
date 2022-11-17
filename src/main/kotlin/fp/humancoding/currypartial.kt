package fp.humancoding

/*
CURRYING

Currying : 커링, 여러개의 인자를 가진 함수를 호출 할 경우, 한개의 인자를 받는 연속적인 호출들을 통해서 누락된 모든 파라미터들을 처리하는 함수형 방식

Computer scientist, Haskell Curry분의 이름애서 유래 됨
https://en.wikipedia.org/wiki/Currying

f:(X, Y, Z) -> N curried into curry(f): X -> (Y -> (Z -> N))
f(X) => new g(Y) => new h(Z)
f(1,2) === g(1)(2)


PARTIAL APPLICATION

Partial Application : 커링과 비슷하지만 인자를 바인딩 하여 재사용하고자 하는 용도의 함수형 방식

인자의 일부분이 curry 된 함수를 통해 독립적인 '부분기능'들을 만들어 놓고 조립 하듯이 사용할 수 있음
f : (X,Y,Z) -> N partially applied to (with x binding)
partial (f) bind x : (Y, Z) -> N
f(bind X) => g(Y, Z)
 */




fun normalSum(a:Int, b:Int) = a+b
//인자를 받는 순서를 분리할 수 있다
//파라미터를 호출하는 시점을 분리한다
fun currySumLambda(a:Int) = {b : Int -> a+b}
fun currySumAnonymous(a:Int)=fun (b:Int) = a + b
fun curryIntSum2(a:Int)=fun (b:Int) = a+ b
fun curryIntSum3(a:Int)=fun (b:Int) = fun(c:Int) =  a+ b + c
fun curryIntSum4(a:Int)=fun (b:Int) = fun(c:Int) =  fun(c:Int) = a+ b + c


fun curryStringConcat4(s1 : String) = fun (s2:String) = fun (s3 : String) = fun(s4:String) = "$s1$s2$s3$s4"

typealias LtoLFunction = (Long) -> Long
fun listMap(f: LtoLFunction , list : List<Long>) = list.map{f(it)}

fun partialApplication(remain : (LtoLFunction, List<Long>) -> List<Long>, bind : LtoLFunction) = {list:List<Long>-> remain(bind , list) }
//list가 ltofunction 통과한 결과를 리스트에 담고

//바인딩(binding)이란 프로그램에 사용된 구성 요소의 실제 값 또는 프로퍼티를 결정짓는 행위를 의미합니다.
//
//예를 들어 함수를 호출하는 부분에서 실제 함수가 위치한 메모리를 연결하는 것도 바로 바인딩입니다.

//고정 바인드 함수
fun square(x:Long) = x * x
fun cubic(x:Long) = x * x * x
fun doubleNum (x:Long) = 2 * x
fun decaNum (x :Long) = 10 * x
fun factorialNum(x:Long) : Long{
    var fac : Long = x
    (x-1 downTo 1).forEach{ fac *= it }
    return fac
}


fun main(args: Array<String>){
    println("normalSum(3,4) = ${normalSum(3,4)}")
    println("currySumLambda(3)(4) = ${currySumLambda(3)(4)}")
    //연속적으로 함수가 호출되는 것이다
    println("currySumAnonymous(3,4) = ${currySumAnonymous(3)(4)}")
    println("curryIntSum2(3)(4) = ${curryIntSum2(3)(4)}}")
    println("curryIntSum3(3)(4)(5) = ${curryIntSum3(3)(4)(5)}}")
    println("curryIntSum4(3)(4)(6) = ${curryIntSum4(3)(4)(5)(6)}}")

    val curriedBy_3_4_5 : (Int)-> Int = curryIntSum4(3)(4)(5)
    println("curriedBy_3_4_5(6) = ${curriedBy_3_4_5(6)}")
    println("curriedBy_3_4_5(8) = ${curriedBy_3_4_5(8)}")
    println("curriedBy_3_4_5(12) = ${curriedBy_3_4_5(12)}")
    println("durryStirngConcat4(A) = ${curryStringConcat4("A")("B")("C")("D")}")
    //
    val partialSquare : (List<Long>) -> List<Long> = partialApplication(::listMap,::square)
    val partialCubic : (List<Long>) -> List<Long> = partialApplication(::listMap,::cubic)
    val partialDoubleNum : (List<Long>) -> List<Long> = partialApplication(::listMap,::doubleNum)
    val partialDecaNum : (List<Long>) -> List<Long> = partialApplication(::listMap,::decaNum)
    val partialFactorialNum: (List<Long>) -> List<Long> = partialApplication(::listMap,::factorialNum)
    //
    val testDataLists : List<List<Long>> = listOf(
        (1L..20L step 2L).toList(),
        (1L..10L).map(::doubleNum))
    //
    for (list in testDataLists){
        println("target list -> $list")
        println("partialSquare(list) =  ${partialSquare(list)}")
        println("partialCubic(list) =  ${partialCubic(list)}")
        println("partialDoubleNum(list) =  ${partialDoubleNum(list)}")
        println("partialDecaNum(list) =  ${partialDecaNum(list)}")
        println("partialFactorialNum(list) =  ${partialFactorialNum(list)}")
        println()
    }


}