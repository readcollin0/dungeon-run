package com.readcollin0.util.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ObjectBus<T> {
	
	private ArrayList<Object> BUS_LIST = new ArrayList<Object>();
	
	@SuppressWarnings("unchecked")
	public void fire(T e) {
		for (Object o : (ArrayList<Object>) BUS_LIST.clone()) {
			Method[] list = o.getClass().getMethods();
			for (Method m : list) {
				if (m.isAnnotationPresent(SubscribeEvent.class)) {
					if (m.getParameterCount() != 1) continue;
					if (m.getParameters()[0].getType().isAssignableFrom(e.getClass())) {
						try {
							m.invoke(o, e);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							System.err.println("Illegal object handler!");
							e1.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
	public void subscribeObject(Object o) {
		BUS_LIST.add(o);
	}
	
	public void clearBusList() {
		BUS_LIST.clear();
	}
	
}
