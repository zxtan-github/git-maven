package org.ifunq.tanzx.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.ifunq.tanzx.pojo.Person;

/**
 * Java虚引用使用示例
 * @author tanzx
 *
 */
public class PhantomReferenceTest {

	public static void main(String[] args) {
		 Person p = new Person();
	        p.setName("zhaosi");
	        p.setAge(17);
	        ReferenceQueue<Person> queue = new ReferenceQueue<Person>();
	        PhantomReference<Person> phaRef = new PhantomReference<Person>(p, queue);

	        p = null;
	        System.gc();
	        
	        while (true) {
	            Object o = queue.poll();
	            if (o != null) {
	            	System.out.println(o);
	            	System.out.println(o.getClass());
	            	PhantomReference s = (PhantomReference) o;
	            	System.out.println(s.get());
//	                try {
//	                    Field rereferent = Reference.class.getDeclaredField("referent");
//	                    rereferent.setAccessible(true);
//	                    Person result = (Person)rereferent.get(o);
//	                    System.out.println("system gc will kill person:" +result.getName());
//	                    System.out.println(result);
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
	            	break;
	            }
	        }

	}

}
