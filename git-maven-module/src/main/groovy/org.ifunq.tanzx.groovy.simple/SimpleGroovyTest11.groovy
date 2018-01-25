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

