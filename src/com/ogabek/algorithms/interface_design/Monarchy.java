package com.ogabek.algorithms.interface_design;

import java.util.List;

// 11:36 12:10
public interface Monarchy {
    void birth(String child, String parent);
    void death(String name);
    List<String> getOrderOfSuccession();
}
