package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class ElementCounter implements XPathMatcherCallBack {

    private final String key;

    public ElementCounter(String key) {
        this.key = key;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement, Map<String, String> parameters) {
        int value = Integer.parseInt(parameters.getOrDefault(key,"0"));
        parameters.put(key, Integer.toString(++value));
        return false;
    }
}