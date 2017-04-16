package org.ifunq.tanzx.anonymousclass;

/**
 * Created by tanzx on 2017/4/16.
 */
class Outer {
    class Inner {
        public String publicString = "Inner.publicString";
    }
    Other anonymousOther = new Other() {
    };
    public Other getAnonymousOther() {
        return anonymousOther;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
interface Other {
    public String publicString = "Other.publicString";
}
