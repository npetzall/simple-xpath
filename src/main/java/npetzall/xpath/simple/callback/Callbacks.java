package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XPathMatcherCallBack;

import javax.xml.namespace.QName;
import java.util.Map;

public class Callbacks {

    private Callbacks() {}

    public static XPathMatcherCallBack extractText(String key, Map<String,String> map) {
        return new ExtractText(key,map);
    }

    public static XPathMatcherCallBack extractTextOnlyOnce(String key, Map<String,String> map) {
        return new ExtractTextOnlyOnce(key,map);
    }

    public static XPathMatcherCallBack extractAttribute(QName attributeName, String key, Map<String,String> map) {
        return new ExtractAttribute(attributeName, key, map);
    }

    public static XPathMatcherCallBack extractAttributeOnlyOnce(QName attributeName, String key, Map<String,String> map) {
        return new ExtractAttributeOnlyOnce(attributeName, key, map);
    }

    public static XPathMatcherCallBack setOnMatch(String key, String value, Map<String,String> map) {
        return new SetOnMatch(key, value, map);
    }

}
