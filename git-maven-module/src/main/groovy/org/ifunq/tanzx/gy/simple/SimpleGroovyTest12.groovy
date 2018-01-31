package org.ifunq.tanzx.gy.simple

def father = new Father();
def son = new Son();
father.write.delegate = son;
println father.write.delegate;
println Father.metaClass.write.delegate;
father.write()
Father.metaClass.sayHello = {
    delegate.write()
}

println Father.metaClass.sayHello.delegate;
// father没有sayHello的引用
// println father.sayHello.delegate;
def father2 = new Father();
// 新建的Father也没有sayHello的引用，但是却能调用sayHello - -！
// println father2.sayHello.delegate;
father2.sayHello();