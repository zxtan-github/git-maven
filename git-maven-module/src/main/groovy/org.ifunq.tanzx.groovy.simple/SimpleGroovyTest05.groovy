package org.ifunq.tanzx.groovy.simple

def map = ["id": 1, "name": "tcy", "isMan": false];
map.each({ key, value ->
    println("${key}--->${value}")
})
map.each { println(it) }
map.each { println(it.key + " : " + it.value) }
map.each { k, e ->
    println("${k}--->${e}")
}


def collection = [1, "tzx", 2L, true];
collection.each {println(it)}
collection.each {e -> println(e)}