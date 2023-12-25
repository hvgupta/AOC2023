import com.fileReader.getLines
import kotlin.math.min


fun part1(input:List<String>):Int{
    var sum:Int = 0
    for (line in input){
        val numArray: MutableList<Char> = mutableListOf<Char>()
        line.forEach { c -> if (c.isDigit()) numArray.add(c) }
        var returnVal: String = numArray[0].toString() + numArray[numArray.lastIndex]
        sum += returnVal.toInt()
    }

    return sum
}

fun part2(input: List<String>):Int{
    val numMap: Map<String,Char> = mapOf<String,Char>("one" to '1', "two" to '2', "three" to '3', "four" to '4',
        "five" to '5', "six" to '6', "seven" to '7', "eight" to '8', "nine" to '9')

    var sum:Int = 0

    for (line in input){
        val numArray: MutableList<Char> = mutableListOf<Char>()
        var window:String = ""
        for (i in 0..line.lastIndex){
            if (line[i].isDigit()){
                numArray.add(line[i])
                window = ""
            }
            else{
                window += line[i]
                if (window.length > 5){
                    window = window.substring(1)
                }

                for (j in 0..min(window.length,2)){
                    val subWindow:String = window.substring(j)
                    if (subWindow in numMap.keys){
                        numArray.add(numMap.getValue(subWindow))
                        window = window.substring(window.lastIndex)
                        break
                    }
                }

            }
        }
        var returnString:String = numArray[0].toString() + numArray[numArray.lastIndex].toString()
        sum += returnString.toInt()
    }

    return sum
}
fun main(){
    val lineList: List<String> = getLines("src/Day1/day1Input.txt")
    println(part1(lineList))
    println(part2(lineList))

}