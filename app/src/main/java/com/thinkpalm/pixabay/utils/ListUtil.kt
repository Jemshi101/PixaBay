package com.thinkpalm.pixabay.utils


/**
 * Created by Jemsheer K D on 19 November, 2018.
 * Package com.thinkpalm.pixabay.utils
 * Project Pixabay
 */
class ListUtil {

    fun <T> ArrayList<T>.isNonEmpty(): Boolean {
        return this != null && this.isNotEmpty()
    }

    fun <T> List<T>.isNonEmpty(): Boolean {
        return this != null && this.isNotEmpty()
    }

    /*fun <T> ArrayList<T>.lastItem(): T {
        return this[this.size - 1]
    }

    fun <T> List<T>.lastItem(): T {
        return this[this.size - 1]
    }*/

    companion object {

        @JvmStatic
        fun <T> ArrayList<T>.lastItem(): T {
            return this[this.size - 1]
        }

        @JvmStatic
        fun <T> List<T>.lastItem(): T {
            return this[this.size - 1]
        }


        @JvmStatic
        fun <T> getArrayList(tList: List<T>?): ArrayList<T> {

            val list = ArrayList<T>()
            for (t in getNonNullList(tList)) {
                list.add(t)
            }
            return list
        }

        @JvmStatic
        fun <T> getNonNullList(tList: ArrayList<T>?): ArrayList<T> {
            return tList ?: arrayListOf()
        }

        @JvmStatic
        fun <T> getNonNullList(tList: List<T>?): List<T> {
            return tList ?: listOf()
        }

        @JvmStatic
        fun <T> equals(tList1: ArrayList<T>, tList2: ArrayList<T>): Boolean {
            return tList1 == tList2
        }

        @JvmStatic
        fun <T> equals(tList1: List<T>, tList2: List<T>): Boolean {
            return tList1 == tList2
        }

        @JvmStatic
        fun <T> isEmpty(tList: List<T>?): Boolean {
            return tList == null || tList.isEmpty()
        }

        @JvmStatic
        fun <T> isEmpty(tList: ArrayList<T>?): Boolean {
            return tList == null || tList.isEmpty()
        }

        @JvmStatic
        fun <E> printList(list: ArrayList<E>, tag: String) {
            for (i in list.withIndex()) {
                println("$tag : Index: ${i.index}, Value : ${i.value}")
            }
        }
    }
}
