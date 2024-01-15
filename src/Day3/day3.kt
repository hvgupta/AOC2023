package com.AOC.Day3.day3
import com.AOC.fileReader.getLines

fun getInt(sequence:String, index:Int):List<Int>{
    if (!sequence[index].isDigit()){
        return listOf<Int>(0,-1,-1)
    }
    var leftIndex:Int = index-1
    var rightIndex:Int = index+1
    var currentNum:String = sequence[index].toString()

    var leftDone:Boolean = false
    var rightDone:Boolean = false

    while (!leftDone || !rightDone){
        if (leftIndex >= 0 && sequence[leftIndex].isDigit() && !leftDone){
            currentNum = sequence[leftIndex].toString() + currentNum
            leftIndex -= 1
        }
        else{
            leftDone = true
        }

        if (rightIndex <= sequence.lastIndex && sequence[rightIndex].isDigit() && !rightDone){
            currentNum += sequence[rightIndex]
            rightIndex+=1
        }
        else{
            rightDone = true
        }
    }

    return listOf<Int>(currentNum.toInt(),leftIndex+1,rightIndex-1)
}
fun part1(lines: List<String>):Int{
    var sum:Int = 0
    for (rowIndex in 0..lines.lastIndex){
        for (coloumnIndex in 0..lines[rowIndex].lastIndex){
            if (lines[rowIndex][coloumnIndex] == '.' || lines[rowIndex][coloumnIndex].isDigit()){
                continue
            }
            if (coloumnIndex > 0){ // look left
                sum += getInt(lines[rowIndex],coloumnIndex-1)[0]
            }
            if (coloumnIndex < lines[rowIndex].lastIndex){ // look right
                sum += getInt(lines[rowIndex],coloumnIndex+1)[0]
            }
            if (rowIndex > 0){ // look up
                val intInfo:List<Int> = getInt(lines[rowIndex-1],coloumnIndex)
                if (intInfo[0] == 0){
                    sum += getInt(lines[rowIndex-1],coloumnIndex-1)[0]
                    sum += getInt(lines[rowIndex-1],coloumnIndex+1)[0]
                }
                else{
                    sum += intInfo[0]
                }
            }
            if (rowIndex < lines.lastIndex){ // look down
                val intInfo:List<Int> = getInt(lines[rowIndex+1],coloumnIndex)
                if (intInfo[0] == 0){
                    sum += getInt(lines[rowIndex+1],coloumnIndex-1)[0]
                    sum += getInt(lines[rowIndex+1],coloumnIndex+1)[0]
                }
                else{
                    sum += intInfo[0]
                }
            }
        }
    }
    return sum
}

fun part2(lines: List<String>):Int{
    var gearRatioSum:Int = 0
    for (rowIndex in 0..lines.lastIndex){
        for (coloumnIndex in 0..lines[rowIndex].lastIndex){
            if (lines[rowIndex][coloumnIndex] != '*'){
                continue
            }

            var numberArray:MutableList<Int> = mutableListOf<Int>()
            if (coloumnIndex > 0){ // look left
                val leftNum =  getInt(lines[rowIndex],coloumnIndex-1)[0]
                if (leftNum > 0){ numberArray.add(leftNum) }
            }
            if (coloumnIndex < lines[rowIndex].lastIndex){ // look right
                val rightNum = getInt(lines[rowIndex],coloumnIndex+1)[0]
                if (rightNum > 0){ numberArray.add(rightNum) }
            }
            if (rowIndex > 0){ // look up
                val intInfo:List<Int> = getInt(lines[rowIndex-1],coloumnIndex)
                if (intInfo[0] == 0){
                    val topLeft:Int =  getInt(lines[rowIndex-1],coloumnIndex-1)[0]
                    if (topLeft > 0){numberArray.add(topLeft)}
                    val topRight:Int = getInt(lines[rowIndex-1],coloumnIndex+1)[0]
                    if (topRight > 0){ numberArray.add(topRight) }
                }
                else{ numberArray.add(intInfo[0]) }
            }
            if (rowIndex < lines.lastIndex){ // look down
                val intInfo:List<Int> = getInt(lines[rowIndex+1],coloumnIndex)
                if (intInfo[0] == 0){
                    val bottomLeft:Int = getInt(lines[rowIndex+1],coloumnIndex-1)[0]
                    if (bottomLeft > 0){ numberArray.add(bottomLeft) }
                    val bottomRight:Int = getInt(lines[rowIndex+1],coloumnIndex+1)[0]
                    if (bottomRight > 0){ numberArray.add(bottomRight) }
                }
                else{ numberArray.add(intInfo[0]) }
            }

            if (numberArray.size == 2){
                gearRatioSum += numberArray[0]*numberArray[1]
            }
        }
    }

    return  gearRatioSum
}
fun main(){
    val input: List<String> = getLines("src/Day3/day3Input.txt")
    println(part1(input))
    println(part2(input))
}

