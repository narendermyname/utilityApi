package com.jamcracker.entity.usage;

public class ChildClass extends BaseClaas{

	public ChildClass() {
		// TODO Auto-generated constructor stub
	}

	protected void childPrint(){
		System.out.println("Child Print");
	}
	public static void main(String[] args) {
		BaseClaas child=new ChildClass();
		child.childPrint();
		

	}
}
