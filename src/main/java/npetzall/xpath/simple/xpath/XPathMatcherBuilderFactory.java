package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class XPathMatcherBuilderFactory {
    private final String xPath;
    private final XPathMatcherCallBack xPathMatcherCallBack;

    public XPathMatcherBuilderFactory(String xPath, XPathMatcherCallBack xPathMatcherCallBack) {
        this.xPath = xPath;
        this.xPathMatcherCallBack = xPathMatcherCallBack;
    }

    public XPathMatcherBuilder build(Map<String,String> prefixNamespace) {
        return new XPathMatcherBuilder(XPath.parse(xPath, prefixNamespace), xPathMatcherCallBack);
    }
}
