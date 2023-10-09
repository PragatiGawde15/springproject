package com.rays.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.rays.bean.UserBean;
import com.rays.bean.UserDetails;

public class UserTest {

	public static void main(String[] args) {

		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		UserDetails bean = (UserDetails) factory.getBean("userDetails");
		//System.out.println(bean.getName());
		//System.out.println(bean.getLogin());
		//System.out.println(bean.getPassword());
		System.out.println(bean.getMob());
		System.out.println(bean.getEmail());
		System.out.println(bean.getAddress());
		

	}
}
