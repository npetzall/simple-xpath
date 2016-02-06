package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class SetOnMatch implements XPathMatcherCallBack {

    private final String key;
    private final String value;

    SetOnMatch(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement, Map<String,String> parameters) {
        parameters.put(key,value);
        return true;
    }
}
