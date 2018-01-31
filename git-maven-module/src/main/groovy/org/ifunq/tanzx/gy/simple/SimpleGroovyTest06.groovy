package org.ifunq.tanzx.gy.simple


def say = { name, age=20 ->
    println("name: ${name} age: " +age);
    "name: ${name} age: " +age;
}
say("tanzongxi", 28);
say.call("tanchengyu", 3);
def result = say.call("tangyan");
say "tanruoxi", 0;
say.call "tanxianbiao", 52;
println result;


def sayHello = { name, age=20 ->
    println name + " XXX";
}
def res = sayHello("tangyanming", 52)
println res;