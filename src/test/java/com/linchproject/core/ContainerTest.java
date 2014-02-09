package com.linchproject.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Georg Schmidl
 */
public class ContainerTest {

    public class MyClass {
        private A a;
        private B b;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    @Test
    public void testGet() throws Exception {
        Container container = Container.getInstance();
        container.add("a", A.class);
        Object object = container.get("a");

        assertNotNull(object);
        assertTrue(object instanceof A);

        Object myComponent = new Object();
        container.add("myComponent", myComponent);
        assertEquals(myComponent, container.get("myComponent"));
    }

    @Test
    public void testAutowire() throws Exception {
        Container container = Container.getInstance();
        container.add("a", A.class);
        container.add("b", B.class);

        MyClass myClass = new MyClass();
        container.autowire(myClass);

        assertNotNull(myClass.getA());
        assertNotNull(myClass.getA().getB());
        assertNotNull(myClass.getB());
        assertNotNull(myClass.getB().getA());
    }
}
