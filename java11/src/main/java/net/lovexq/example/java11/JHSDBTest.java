package net.lovexq.example.java11;

/**
 * @author LuPindong
 * @time 2020-04-06 11:59
 */
public class JHSDBTest {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done...");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDBTest.Test();
        test.foo();
    }
}
