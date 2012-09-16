package com.basware.tij.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {
		// create a real object that we want to be proxied
		RealObject real = new RealObject();

		// create a proxy to the real object; the real object is passed as constructor argument of the invocation
		// handler; in the most cases is invocation handler's responsability to search the real object
		Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
				new Class[] { Interface.class }, new DynamicProxyHandler(real));

		// call the proxy; first, the invoke metod from invocation handler will be called; the invocation handler will
		// call the real object
		proxy.echo("Echo message");
	}
}
