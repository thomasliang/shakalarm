package _threads;

public class TestSynchronized {
}

class Super {
	void f() {}
	synchronized void g() {}
}

class Sub extends Super {
//	synchronized void f() {}
//	void g() {}
}