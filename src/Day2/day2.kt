package com.AOC.Day2.day2
import com.AOC.fileReader.getLines
import kotlin.math.max
import kotlin.math.round

val numberOfCubes: Map<String,Int> = mapOf<String,Int>("red" to 12, "green" to 13, "blue" to 14)
fun part1(lines:List<String>):Int{
    var sum:Int = 0
    for (i in 1..lines.lastIndex+1){
        val gameString:String = lines[i-1].substring(6+i.toString().length).replace(",","")
        val roundsList: List<String> = gameString.split(";")
        var possible:Boolean = true
        for (round in roundsList){
            val spaceDelimList: List<String> = round.split(" ")
            if ("red" in spaceDelimList){
                val index:Int = spaceDelimList.indexOf("red")
                if (spaceDelimList[index-1].toInt() > numberOfCubes.getValue("red")){
                    possible = false
                    break
                }
            }
            if ("green" in spaceDelimList){
                val index:Int = spaceDelimList.indexOf("green")
                if (spaceDelimList[index-1].toInt() > numberOfCubes.getValue("green")){
                    possible = false
                    break
                }
            }

            if ("blue" in spaceDelimList){
                val index:Int = spaceDelimList.indexOf("blue")
                if (spaceDelimList[index-1].toInt() > numberOfCubes.getValue("blue")){
                    possible = false
                    break
                }
            }
        }
        if (possible){
            sum += i
        }
    }
    return sum
}

fun part2(lines: List<String>):Int{
    var sum:Int = 0

    for (i in 0..lines.lastIndex){
        val gameString: String = lines[i].substring(6+i.toString().length).replace(",","")
        val roundsList: List<String> = gameString.split(";")
        var colourList: MutableList<Int> = MutableList<Int>(3){0}
        for (round in roundsList){
            val spaceDelimList: List<String> = round.split(" ")
            if ("red" in spaceDelimList){
                val index:Int = spaceDelimList.indexOf("red")
                colourList[0] = max(colourList[0],spaceDelimList[index-1].toInt())
            }

            if ("green" in spaceDelimList){
                val index:Int = spaceDelimList.indexOf("green")
                colourList[1] = max(colourList[1],spaceDelimList[index-1].toInt())
            }

            if ("blue" in spaceDelimList){
                val index: Int = spaceDelimList.indexOf("blue")
                colourList[2] = max(colourList[2],spaceDelimList[index-1].toInt())
            }
        }
        sum += colourList[0]*colourList[1]*colourList[2]
    }

    return sum
}
fun main(){
    val input:List<String> = getLines("src/Day2/day2Input.txt")
    println(part1(input))
    println(part2(input))
}

