package com.base;

/**
 * 中缀变前缀
 *
 * @author 金浩
 */
public class InfixToPrefix {

    /**
     * s1装操作符，s2装操作数，input为输入字符串
     */
    private MyCharStack s1;
    private MyCharStack s2;
    private String input;

    public InfixToPrefix(String input) {
        this.input = input;
        s1 = new MyCharStack(input.length());
        s2 = new MyCharStack(input.length());
    }

    /**
     * 进行装换
     *
     * @return
     */
    public MyCharStack doTrans() {
        for (int i = input.length() - 1; i >= 0; i--) {
            System.out.print("s1栈元素为: ");
            s1.displayStack();
            System.out.print("s2栈元素为: ");
            s2.displayStack();
            char ch = input.charAt(i);
            System.out.println("当前解析的字符为: " + ch);
            switch (ch) {
                case '+':
                case '-':
                    getOper(ch, 1);
                    break;
                case '*':
                case '/':
                    getOper(ch, 2);
                    break;
                case ')':
                    s1.push(ch);
                    break;
                case '(':
                    getParen(ch);
                    break;
                default:
                    //遇到操作数，直接压栈s2
                    s2.push(ch);
                    break;
            }
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2;
    }

    /**
     * 遇到操作符
     *
     * @param ch
     * @param degree
     */
    public void getOper(char ch, int degree) {
        while (!s1.isEmpty()) {
            char op = s1.pop();
            //如果栈顶为右括号，退出循环直接入栈
            if (')' == op) {
                s1.push(op);
                break;
            } else {
                //否则比较优先级
                //临时变量存栈顶操作符优先级
                int temp = 0;
                //加减优先级为1，乘除为2
                if ('+' == op || '-' == op) {
                    temp = 1;
                } else {
                    temp = 2;
                }
                //比较当前和栈顶的操作符优先级大小
                //大于等于就入栈
                if (degree >= temp) {
                    s1.push(op);
                    break;
                } else {
                    //否则弹入s2
                    s2.push(op);
                }
            }
        }
        s1.push(ch);
    }

    /**
     * 遇到括号
     *
     * @param num
     */
    public void getParen(char num) {
        while (!s1.isEmpty()) {
            char chx = s1.pop();
            if (chx == ')') {
                break;
            } else {
                //右括号直接入栈
                s2.push(chx);
            }
        }
    }

    public static void main(String[] args) {
        InfixToPrefix infixToPrefix = new InfixToPrefix("a*(b+c)-d/(e+f)");
        MyCharStack myCharStack = infixToPrefix.doTrans();
        myCharStack.displayStack();
    }
}