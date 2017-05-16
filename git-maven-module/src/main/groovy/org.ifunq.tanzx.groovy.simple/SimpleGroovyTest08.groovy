package org.ifunq.tanzx.groovy.simple

def msg = "Hello!"
println msg.metaClass
String.metaClass.up = {  delegate.toUpperCase() }
println msg.up()
msg.metaClass.methods.each { println it.name }
msg.metaClass.properties.each { println it.name }