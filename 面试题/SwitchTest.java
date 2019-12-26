class SwitchTest {
    /**
     * 结果会抛空指针异常，switch 中的变量时不能为空的
     * 其内部实现是调用 hashCode 方法去匹配的
     * 如果为 null 的话，调用 hashCode 则会抛出空指针异常
     */
    public static void main(String[] args) {
        String param = null;
        switch (param) {
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }
}