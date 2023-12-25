package com.fileReader

import java.io.File
import java.io.InputStream

fun getLines(filePath:String):List<String>{
    val inputStream: InputStream = File(filePath).inputStream()
    val lineList: MutableList<String> = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

    return lineList.toList()
}