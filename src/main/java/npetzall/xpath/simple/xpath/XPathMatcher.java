package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

public class XPathMatcher implements XMLElementListener {

    private final XPath xPath;
    private final XPathMatcherCallBack xPathMatcherCallBack;

    public XPathMatcher(XPath xPath, XPathMatcherCallBack xPathMatcherCallBack) {
        this.xPath = xPath;
        this.xPathMatcherCallBack = xPathMatcherCallBack;
    }

    @Override
    public void onStartElement(XMLElement xmlElement) {
        xPathMatcherCallBack.matchFound(xmlElement);
    }

    @Override
    public void onEndElement(XMLElement xmlElement) {

    }
}
