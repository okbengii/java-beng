package com.beng.cache;

/**
 * 自定义缓存
 * 
 * @author apple
 */
public class CacheImmutable {

    private static final int MAX_SIZE = 10; // 缓存的最大尺寸

    private static CacheImmutable[] cache = new CacheImmutable[MAX_SIZE]; // 缓存池

    private static int pos = 0; // 记录当前的缓存地址

    private final String name;

    private CacheImmutable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name
     * @return
     */
    public static CacheImmutable valueOf(String name) {

        // 遍历缓存池，返回名字相同的缓存
        for (int i = 0; i < MAX_SIZE; i++) {
            if (cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }
        // 如果缓存池满了
        if (pos == MAX_SIZE) {
            cache[0] = new CacheImmutable(name);
            pos = 1;
        } else {
            cache[pos++] = new CacheImmutable(name);
        }
        return cache[pos - 1];
    }

    /**
     * 重写 equals
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == CacheImmutable.class) {
            CacheImmutable ci = (CacheImmutable) obj;
            return ci.getName().equals(name);
        }
        return false;
    }

    public int hashCode(Object obj) {
        return name.hashCode();
    }

    public static void main(String[] args) {

        CacheImmutable c1 = CacheImmutable.valueOf("hello");
        CacheImmutable c2 = CacheImmutable.valueOf("hello1");

        System.out.println(c1.equals(c2));

    }

}
