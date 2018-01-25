package org.ifunq.tanzx.groovy.simple

def msg = "Hello!"
println msg.metaClass
// 这就是闭包up
String.metaClass.up = {  owner.toUpperCase() }
println msg.up()
msg.metaClass.methods.each { println it.name }
msg.metaClass.properties.each { println it.name }