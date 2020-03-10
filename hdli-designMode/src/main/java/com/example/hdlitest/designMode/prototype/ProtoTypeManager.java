package com.example.hdlitest.designMode.prototype;

import java.util.HashMap;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-18 15:30
 */
public class ProtoTypeManager {
    private HashMap<String, Shape> ht = new HashMap<String, Shape>();

    public ProtoTypeManager() {
        ht.put("Circle", new Circle());
        ht.put("Square", new Square());
    }

    public void addshape(String key, Shape obj) {
        ht.put(key, obj);
    }

    public Shape getShape(String key) {
        Shape temp = ht.get(key);
        return (Shape) temp.clone();
    }
}
