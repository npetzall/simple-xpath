package npetzall.xpath.simple.helper;

import java.util.HashMap;

public class NamespaceMap extends HashMap<String,String> {
    public static NamespaceMap namespace(String prefix, String namespace) {
        NamespaceMap namespaceMap = new NamespaceMap();
        return namespaceMap.add(prefix, namespace);
    }

    public NamespaceMap add(String prefix, String namespace) {
        put(prefix, namespace);
        return this;
    }
}
