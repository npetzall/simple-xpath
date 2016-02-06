package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class ExtractTextFirst implements XPathMatcherCallBack {

    private final String key;

    ExtractTextFirst(String key) {
        this.key = key;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement, Map<String,String> parameters) {
        if (xmlElement.hasText()) {
            parameters.put(key, xmlElement.getText());
            return true;
        }
        return false;
    }
}
