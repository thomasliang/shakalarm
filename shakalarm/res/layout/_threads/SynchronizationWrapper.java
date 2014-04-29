package _threads;

public class SynchronizationWrapper {
	public static void main(String [] args) {
		// create a nonsynchronized object
		AnObject c = new NonsynchronizedObject();
		// use c as a nonsynchronized object without synchronization overheads
		// ...
		
		// would like to manipulate c concurrently
		c = SynchronizedObjectWrapper.wrap(c);
		// create multiple threads and access c concurrently
		// ...
		
		// may unwrap c if synchronization operations are no longer needed
	}
}

interface AnObject {
	void operation1();
	void operation2();
}

class NonsynchronizedObject implements AnObject {
	private java.util.LinkedList data;
	public void operation1 () {
		// do something and manipulate data
	}
	public void operation2 () {
		// do something and manipulate data
	}
}

class SynchronizedObjectWrapper implements AnObject{
	private AnObject obj;
	
	SynchronizedObjectWrapper(AnObject o) {
		obj = o;
	}
	synchronized public void operation1() {
		obj.operation1();
	}
	synchronized public void operation2() {
		obj.operation2();
	}
	static public AnObject wrap(AnObject o) {
		return new SynchronizedObjectWrapper(o);
	}
	static public AnObject unwrap(AnObject o) {
		if (o instanceof SynchronizedObjectWrapper)
			return ((SynchronizedObjectWrapper) o).obj;
		else
			return o;
	}
}