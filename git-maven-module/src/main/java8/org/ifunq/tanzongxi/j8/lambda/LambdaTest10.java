package org.ifunq.tanzongxi.j8.lambda;

public class LambdaTest10 {

    public static void main(String[] args) {

        String sss = "ss";

        // 引用定义类型时，具体实例定不定义都无所谓
        Point<String> point1 = new Point<String>();
        Point<String> point2 = new Point();
        Point<String> point3 = new Point<>();

        // 引用没有定义类型时，具体实例定不定义都无所谓
        Point point4 = new Point<String>();
        Point point5 = new Point();
        Point point6 = new Point<>();

        // 引用定义?号类型时，根据具体的方法再来判断类型
        Point<?> point7 = new Point<String>();
        Point<?> point8 = new Point();
        Point<?> point9 = new Point<>();

        String x1 = point1.getVarWithT(sss);
        String x2 = point2.getVarWithT(sss);
        String x3 = point3.getVarWithT(sss);

        String x4 = (String) point4.getVarWithoutT(sss);
        String x5 = (String) point5.getVarWithoutT(sss);
        String x6 = (String) point6.getVarWithoutT(sss);

        String x7 = point7.getVarMethodF(sss);
        String x8 = point8.getVarMethodF(sss);
        String x9 = point9.getVarMethodF(sss);

        // 方法上面和类定义的是同一泛型，必须用同样的类型
        // Integer i1 = point1.getVarWithoutT(2);

        // 方法上面单独定义的类型时，和类上面的定义的就可以没有关系
        Integer i2 = point1.getVarMethodF(2);

        GenericImpl102<String,String,String> genericImpl102 = new GenericImpl102();
//        genericImpl102.sayR();

    }
}


// 类上面定义泛型时，方法上面可以不用定义
class Point<T> {

    // 方法上面定义类型时，类上面也定义同样的类型时，方法上面可以不用重复定义
    public <T> T getVarWithT(T t) {
        return t;
    }

    // 方法上面定义类型时，类上面也定义同样的类型时，方法上面可以不用重复定义
    public T getVarWithoutT(T t) {
        return t;
    }

    // 方法上面定义类型时，类上面可以不定义
    public <F> F getVarMethodF(F t) {
        return t;
    }

    // 方法上面没有定义类型时，类上面一定要定义，否则报错
    // public V getVar2(V v) {
    //    return v;
    // }
}

interface Generic<T,R> {}
class Generic2<T,R> {
    public void sayT(T t) {
        System.out.println(t);
    }
    public void sayR(R r) {
        System.out.println(r);
    }

}
// ===============泛型都没定义类型=============
class GenericImpl1 implements Generic {}
class GenericImpl2<T,R> implements Generic<T,R> {}
class GenericImpl3<T,R> implements Generic {}
// 报错，不可以这么定义
//class GenericImpl4 implements Generic<T,R> {}

// ===============泛型定义类型=============
class GenericImpl5<String, R> implements Generic<String, R> {}
class GenericImpl6<String, Object> implements Generic<String, Object> {}
class GenericImpl7<String, Object> implements Generic {}
class GenericImpl8 implements Generic<String, Object> {}

class GenericImpl9<T extends String, R extends Object> implements Generic<T,R>{}
class GenericImpl10<T extends String, R extends Object> implements Generic{}
class GenericImpl11<T extends String, R extends Object> {}
class GenericImpl12<String, Object> implements Generic {}


// 子类（实现类）定义的泛型和父类没有关系的
// 子类（实现类定义的<W,M,N>和父类或者接口定义的<T,R>，没有任何联系
class GenericImpl101<W,M,N> implements Generic {};
class GenericImpl102<W,M,N> extends Generic2 {};
// 子类（实现类定义的<W,M,N>和父类或者接口定义中需要使用到时
class GenericImpl103<W,M,N,Z> implements Generic<W,M> {}
class GenericImpl104<W,M,N,Z> implements Generic<Z,M> {}
class GenericImpl105<W,M,N,Z> extends Generic2<M,M> {}
// 子类（实现类定义的<W,M,N>和父类或者接口定义中需要使用到时，同时限定部分父类或者接口中的泛型类型
class GenericImpl106<W,M,N,Z> implements Generic<M,String> {}
class GenericImpl107<W,M,N,Z> implements Generic<String,String> {}
// class GenericImpl106<W,M,N,Z> implements Generic<M,R> {} // 编译错误，R在子类（实现类）中为定义
// class GenericImpl106<W,M,N,Z> implements Generic<M,?> {} // 编译错误，不能使用通配符，说明子类（实现类）定义的泛型父类或者接口定义中需要使用到时，必须全部定义完
