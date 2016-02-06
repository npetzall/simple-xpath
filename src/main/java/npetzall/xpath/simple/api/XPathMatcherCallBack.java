package npetzall.xpath.simple.api;

import java.util.Map;

@FunctionalInterface
public interface XPathMatcherCallBack {
    boolean matchFound(XMLElement xmlElement, Map<String,String> parameters);
}
