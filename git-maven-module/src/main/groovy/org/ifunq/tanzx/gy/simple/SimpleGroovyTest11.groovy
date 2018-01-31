package org.ifunq.tanzx.gy.simple

class Father {
    // 闭包
    def write = {
        delegate.realyWrite()
    }
    // 方法
    def realyWrite() {
        println("No, I'm not Write.")
    }
}
class Son {
    // 方法
    def realyWrite() {
        println("Yes, I'm realy Write.")
    }
}
def father = new Father();
def son = new Son();
father.write.delegate = son;
father.write()
//father.write.this.realyWrite()
father.write.owner.realyWrite()
father.write.delegate.realyWrite()

def fc = {
    println("Fc Closure...")
}

def mc() {
    println("Mc Closure...")
}
fc()