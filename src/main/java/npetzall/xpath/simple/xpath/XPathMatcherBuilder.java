package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class XPathMatcherBuilder {
    private final XPath xPath;
    private final XPathMatcherCallBack xPathMatcherCallBack;

    public XPathMatcherBuilder(XPath xPath, XPathMatcherCallBack xPathMatcherCallBack) {
        this.xPath = xPath;
        this.xPathMatcherCallBack = xPathMatcherCallBack;
    }

    public XPathMatcher build(Map<String,String> parameters) {
        return new XPathMatcher(xPath, xPathMatcherCallBack, parameters);
    }
}
