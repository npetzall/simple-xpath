package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class SetOnMatch implements XPathMatcherCallBack {

    private final String key;
    private final String value;
    private final Map<String,String> map;

    SetOnMatch(String key, String value, Map<String,String> map) {
        this.key = key;
        this.value = value;
        this.map = map;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement) {
        map.put(key,value);
        return true;
    }
}
