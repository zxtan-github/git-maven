package org.ifunq.tanzx.gy.simple

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
println People.metaClass;
People.metaClass.say = { "say hello" }
People.metaClass.zzz = { delegate.write() }
People.metaClass.methods.each { println it.name }
p3 = new People();
println p3.say();
println p3.zzz();
println p2.say();