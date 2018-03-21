package com.base;

import java.util.Scanner;

/**
 * 中缀变后缀
 * @author 金浩
 */
public class InfioxToSuffix {
	private MyCharStack s1;
	private MyCharStack s2;
	private String input;

	public InfioxToSuffix(String in) {
		input = in;
		s1 = new MyCharStack(input.length());
		s2 = new MyCharStack(input.length());
	}

	public MyCharStack doTrans() {
		for (int i = 0; i < input.length(); i++) {
			System.out.print("s1栈元素为: ");
			s1.displayStack();
			System.out.print("s2栈元素为: ");
			s2.displayStack();
			char ch = input.charAt(i);
			System.out.println("当前解析的字符为: " + ch);
			switch (ch) {
			case '+':
			case '-':
				gotOper(ch, 1);
				break;
			case '*':
			case '/':
				gotOper(ch, 2);
				break;
			case '(':
				s1.push(ch);
				break;
			case ')':
				gotParen(ch);
				break;
			default:
				s2.push(ch);
				break;
			}
		}
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		return s2;
	}

	public void gotOper(char opThis, int prec1) {
		while (!s1.isEmpty()) {
			char opTop = s1.pop();
			if (opTop == '(') {
				s1.push(opTop);
				break;
			} else {
				int prec2;
				if (opTop == '+' || opTop == '-') {
					prec2 = 1;
				} else {
					prec2 = 2;
				}
				if (prec1 > prec2) {
					s1.push(opTop);
					break;
				} else {
					s2.push(opTop);
				}
			}
		}
		s1.push(opThis);
	}

	public void gotParen(char ch) {
		while (!s1.isEmpty()) {
			char chx = s1.pop();
			if (chx == '(') {
				break;
			} else {
				s2.push(chx);
			}
		}
	}

	public static void main(String[] args) {
		String input;
		System.out.println("Enter infix:");
		Scanner scanner = new Scanner(System.in);
		input = scanner.nextLine();
		InfioxToSuffix infioxToSuffix = new InfioxToSuffix(input);
		MyCharStack myCharStack = infioxToSuffix.doTrans();
		myCharStack.displayStack();
	}
}
