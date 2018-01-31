package org.ifunq.tanzx.gy.simple

class Person {
    def name;
    def age;
    @Override
    String toString() {
        return "name:${name} age:${age}"
    }
}
Person p1 = new Person();
p1.name = "tanzongxi"
p1.setAge(28);
println p1;
Person p2 = new Person(["name":"tangyan","age":20])
println p2;
Person p3 = new Person(["name":"tanxianbiao", "age": "fifty-two"])
println p3;
Person p4 = new Person(["name":"tangyanming"])
println p4;