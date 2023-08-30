package com.bakhdev.composefirstproject.helper

fun createDummyListData(): ArrayList<String> = arrayListOf<String>().apply {
    for (i in 0..40) add("Item ke $i")
}