package transformer.demo;

public class BaseConstants {

    /**
     * @Author: youpengda@qq.com
     * @Description: 0:失效 1:生效
     */
    public enum State {
        INVALID("0", "失效"),
        VALID("1", "生效"),
        ;
        private String key;
        private String name;

        State(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public static String getNameBykey(String key) {
            for (State type : State.values()) {
                if (type.getKey().equals(key)) {
                    return type.getName();
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
