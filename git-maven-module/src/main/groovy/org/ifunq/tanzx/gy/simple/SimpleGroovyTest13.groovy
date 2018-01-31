package org.ifunq.tanzx.gy.simple

class Chinese {
    def name;
    @Override
    String toString() {
        return name
    }
    def showInference = {
        println this;
        println owner;
        println delegate;
    }
}

Chinese.metaClass.showDelegate = {
    println this;
    println owner;
    println delegate;
}

Chinese chinese1 = new Chinese(['name':'tanzongxi'])
Chinese chinese2 = new Chinese(['name':'tanchengyu'])
chinese1.showInference()
chinese2.showInference()
println("-------------------------------")
chinese1.showDelegate()
chinese2.showDelegate()