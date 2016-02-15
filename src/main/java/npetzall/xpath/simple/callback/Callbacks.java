package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XPathMatcherCallBack;

import javax.xml.namespace.QName;

public class Callbacks {

    private Callbacks() {}

    public static XPathMatcherCallBack extractTextFirst(String key) {
        return new ExtractTextFirst(key);
    }

    public static XPathMatcherCallBack extractTextLast(String key) {
        return new ExtractTextLast(key);
    }

    public static XPathMatcherCallBack extractAttributeFirst(QName attributeName, String key) {
        return new ExtractAttributeFirst(attributeName, key);
    }

    public static XPathMatcherCallBack extractAttributeLast(QName attributeName, String key) {
        return new ExtractAttributeLast(attributeName, key);
    }

    public static XPathMatcherCallBack setOnMatch(String key, String value) {
        return new SetOnMatch(key, value);
    }

    public static XPathMatcherCallBack countElements(String key) {
        return new ElementCounter(key);
    }
}
