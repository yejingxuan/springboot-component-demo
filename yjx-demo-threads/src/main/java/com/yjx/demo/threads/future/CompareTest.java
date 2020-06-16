package com.yjx.demo.threads.future;

public class CompareTest{
        private String mValue="";

        public CompareTest(String value){
                mValue=value;
        }

        public static void compareObject(Object objA,Object objB){
            if(objA==objB){
            System.out.println("They are the same.");
            }else{
            System.out.println("They are different.");
            }
        }

        public static void main(String args[]){
            String strA="abcd";
            String strB="abcd";

            CompareTest ctA=new CompareTest(strA);
            CompareTest ctB=new CompareTest(strB);

            compareObject(strA,strB); // 这里会输出什么？为什么
            compareObject(ctA,ctB); // 这里会输出什么？为什么
        }
}

