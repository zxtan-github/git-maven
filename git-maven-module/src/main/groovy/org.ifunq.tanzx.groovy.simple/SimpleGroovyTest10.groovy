package org.ifunq.tanzx.groovy.simple

class People {
    def name;
    def age;
    @Override
    String toString() {
        return "name:${name} age:${age}"
    }
    String write() {
        return "I'm writing!"
    }
}

def p2 = new People(["name":"tanzongxi","age":28])
println p2.write();
println p2.metaClass;
People.metaClass.say = { "say hello" }
People.metaClass.zzz = { "say zzz" }
p2.metaClass.methods.each { println it.name }
p2.say();