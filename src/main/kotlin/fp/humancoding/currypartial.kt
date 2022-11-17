package fp.humancoding

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
fun partialapplication(remain: (LtoLFunction, List<Long>) , bind : LtoLFunction) = {list : List<Long> -> }


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

}