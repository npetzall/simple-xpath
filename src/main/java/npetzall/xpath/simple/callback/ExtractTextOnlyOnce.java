package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class ExtractTextOnlyOnce implements XPathMatcherCallBack {

    private final String key;
    private final Map<String,String> map;

    ExtractTextOnlyOnce(String key, Map<String,String> map) {
        this.key = key;
        this.map = map;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement) {
        if (xmlElement.hasText()) {
            map.put(key, xmlElement.getText());
            return true;
        }
        return false;
    }
}
