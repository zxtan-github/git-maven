package org.ifunq.tanzx;

class Outer {  
	
	public Outer() {
		// TODO Auto-generated constructor stub
		this.getName();
	}
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
