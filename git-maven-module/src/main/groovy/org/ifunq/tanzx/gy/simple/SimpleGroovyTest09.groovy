package org.ifunq.tanzx.gy.simple

import org.ifunq.tanzx.pojo.Person

def p1 = new Person()
p1.name = "tanzongxi"
println p1.metaClass;
Person.metaClass.say = { "say hello" }
println p1.say();
Person.metaClass.up = { delegate.name.toUpperCase() }
println p1.up();
Person.metaClass.sayHello = { fex -> fex + delegate.name }
println p1.sayHello("Hello ");
