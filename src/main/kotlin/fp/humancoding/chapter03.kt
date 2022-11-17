package fp.humancoding


import java.util.Collections.sort

/*
* JAVA SAM FUNCTIONAL INTERFACE & ANONYMOUS OBJECT EXPRESSION
*
* -> object expression of anonymous class
*
* anonymous object expression : 오브젝트 식 <=> 오브젝트 선언 (declaration)
* 클래스, 인터페이스 등을 상속받거나 받지 않더라도 익명의 (내부) 클래스의 객체를 생성함
* val o1 = object {
* val a : Int = 0
* val b = "string"
* }
*
* val o2: C = object : Class(1), Interface {
* ovveride val propClass = "xyz"
* ovveride fun getInterface () = ...
* }
*
* object 로 클래스와 인터페이스를 상속받아 익명 객체를 만들 수 있다
*
*
* -> Java Single Abstract Method Functional Interface
*
* Java Sam Fi(Functional Interface)
* 인터페이스를 통해 함수형 람다식을 처리할 수 있음
* @FunctionallInterface 어노테이션을 적용함
* 오직 하나의 추상 메소드 (SAM; Single Abstract Method) 만 가진 인터페이스
*
* java.util.function 패키지 :
*
*  T get() - 함수 생산 Supply, Produce
* void accept(T t) - 함수 소비 Consume
* R apply(T t) - 함수 적용 T => R 변환 (Function<T, R>) Apply
* boolean test(T t) - 조건식 함수 콜백, Predicate
*
* java.lang 패키지 :
* interface Runnable {void run();} - 쓰레드 Thread
*
* java.io package 패키지 :
* interface FileFilter {boolean accept(File pathName);} - 파일 필터링 File Filtering
*
* java.util 패키지 :
* interface Comparator<T> {boolean compare(T x, T y);} - 객체 비교 Comparison
*
* java.awt.event 패키지 :
* interface ActionListener {void actionPerformed(ActionEvent e);} - 이벤트 핸들러
*  Event Handler
*
* Kotlin 에서 Java SAM FI 는 익명객체 (object exp.) 나 람다식으로 축약표현이 가능함
* val obj1 = object : JavaSAMFI {...} val obj2 = javaSAMFI {it?.xxx}
*
* */

fun main(args: Array<String>) {
    //1. object expression
    val objHuman = object {
        val name = "eienstein";
        val job = "scientist"
    }
    println("objHuman = $objHuman, objHuman.name = ${objHuman.name}, objHuman.job = ${objHuman.job}")

    //2. Java SAM FI expression
    val numbers = arrayListOf<Int>(4, 8, 3, 1, 9, 6, 2, 7, 5)
    numbers.sort()
    println("numbers,sort() = ${numbers}")

    numbers.sortDescending()
    println("numbers,sort() = ${numbers}")
    println("numbers,sort() = ${numbers.sorted()}")
    println("numbers,sort() = ${numbers.apply(ArrayList<Int>::sort)}")

    //
    sort(numbers, object : Comparator<Int> {
        //compare 라는 단 하나의 추상 메소드
        override fun compare(p0: Int, p1: Int): Int {
            return p0 - p1
        }
    })

    sort(numbers, Comparator<Int> {  //java.util.Comparator<T> SAM FI
        //단하나의 메소드를 가지고 있기 때문에 메소드 이름이 필요 없다
        //compare 라는 단 하나의 추상 메소드
            p0, p1 ->
        p0 - p1
    })
    println(numbers)
    sort(numbers) {  //java.util.Comparator<T> SAM FI
        //단하나의 메소드를 가지고 있기 때문에 메소드 이름이 필요 없다
        //compare 라는 단 하나의 추상 메소드
            p0, p1 ->
        p1 - p0
    }
    println(numbers)

    val objComparable : Comparator<Int> = object : Comparator<Int>{
        override fun compare(o1: Int, o2:Int):Int{
            return o1- o2
        }
    }
    sort(numbers, objComparable)
    //equals 는 추상메소드가 아닌가?

    //custom SAM
//    val obCountJava = object : MyCountable{
//        override fun myCount(list: MutableList<Int>?): Int {
//            return list?.size?:0
//        }
//    }

    val obCountJava =  MyCountable { list -> list?.size?:0}
    val objCountjava = MyCountable{it?.size?:0} //SAM 축약표현
    println()
    println("java SAMFI objCountJava.MyCount(null) = ${objCountjava.myCount(null)}")
    println("java SAMFI objCountJava.MyCount(numbers) = ${objCountjava.myCount(numbers)}")
    //
//    val objCountKt = object : MyCountableKt {
//        override fun myCount(list: List<Int>): Int {
//            return list.size
//        }
//    }
    //자바와 달리 코틀린에서느 sam 을 사용할 수 없다?
   // val objCountKt =  MyCountableKt { list:List<Int> ->  list.size }
   // val objCountKt =  MyCountableKt { it.size } //error
    val objCountTypeAlias : MyCountableTypeAlias = object : MyCountableTypeAlias{
        override fun invoke(p1: MutableList<Int>): Int {
           //코틀린에서 함수형은 제네릭 인터페이스
            //invoke 라는 추상 메소드를 가지고 있다  call 하는 기호
            return p1.size
        }

    }
    /*

     */
    println("objCountTypeAlias.invoke() = ${objCountTypeAlias.invoke(numbers)}")
    println("objCountTypeAlias()  ${objCountTypeAlias(numbers)}")

    val objCountTypeAliasLambda : MyCountableTypeAlias = {
        list: MutableList<Int> -> list.size
    }
    val objCountTypeAliasAnony : (MutableList<Int>) -> Int = fun (list:MutableList<Int>) = list.size

    println("objCountTypeAliasLambda() = ${objCountTypeAliasLambda(numbers)}")
    println("objCountTypeAliasAnony() = ${objCountTypeAliasAnony(numbers)}")

    val mcList : List<MyCountableTypeAlias> = listOf(objCountTypeAlias,objCountTypeAliasLambda ,objCountTypeAliasAnony)

    mcList.map { it (numbers) }.forEach(::println)



}

interface MyCountableKt{
    fun myCount(list:List<Int>) :Int
}

typealias MyCountableTypeAlias = (MutableList<Int>) -> Int