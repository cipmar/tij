package com.basware.tij.dynamicproxy;

public class RealObject implements Interface {

	@Override
	public String echo(String message) {
		System.out.println("echo");
		return message;
	}
}
