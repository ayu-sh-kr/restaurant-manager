package org.example.algorithms

import org.example.swap


fun bubbleSort(arr: MutableList<Int>) {

    val n = arr.size

    for (i: Int in 0 until n - 1) {
        for (j: Int in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                arr.swap(j, j + 1)
            }
        }
    }

}

fun <T>MutableList<T>.copy(deep: Boolean = false): MutableList<T> {
    if(!deep) {
        return this
    }

    return this.map { it }.toMutableList()
}

interface SortingAlgorithm<T> {
    fun sort(): MutableList<T>
}

class IntMergerSort(val arr: MutableList<Int>) : SortingAlgorithm<Int> {

    override fun sort(): MutableList<Int> {
        mergesort(arr, 0, arr.size)
        return arr
    }

    private fun merge(arr: MutableList<Int>, start: Int, end: Int, mid: Int) {
        val left = arr.copy(deep = true).subList(start, mid + 1)
        val right = arr.copy(deep = true).subList(mid + 1, end)

        var i = 0
        var j = 0
        var k = start

        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) {
                arr[k] = left[i]
                i++
            } else {
                arr[k] = right[j]
                j++
            }

            k++
        }

        copyArray(arr, k, left, i)
        copyArray(arr, k, right, j)

    }

    private fun copyArray(to: MutableList<Int>, i: Int, from: MutableList<Int>, j: Int) {
        var iVar = i
        var jVar = j
        while(jVar < from.size) {
            to[iVar++] = from[jVar++]
        }
    }

    private fun mergesort(arr: MutableList<Int>, start: Int, end: Int) {

        if (start < end) {
            val mid = start + (end - start) / 2
            mergesort(arr, start, mid)
            mergesort(arr, mid + 1, end)
            merge(arr, start, end, mid)
        }

    }

}