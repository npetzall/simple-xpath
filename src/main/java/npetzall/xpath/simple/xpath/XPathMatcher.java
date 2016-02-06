package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

public class XPathMatcher implements XMLElementListener {

    private final XPath xPath;
    private final XPathMatcherCallBack xPathMatcherCallBack;

    private int depth=0;
    private int matchedToDepth=0;

    public XPathMatcher(XPath xPath, XPathMatcherCallBack xPathMatcherCallBack) {
        this.xPath = xPath;
        this.xPathMatcherCallBack = xPathMatcherCallBack;
    }

    @Override
    public void onStartElement(XMLElement xmlElement) {
        if (isMatching(xmlElement)) {
            matchedToDepth = depth;
            if (xPath.maxDepth() == matchedToDepth && xPathMatcherCallBack.matchFound(xmlElement)) {
                xmlElement.getSource().removeXMLElementListener(this);
            }
        }
        depth++;
    }

    private boolean isMatching(XMLElement xmlElement) {
        return (depth == 0 || depth == matchedToDepth + 1) && depth <= xPath.maxDepth() && xPath.matches(depth, xmlElement);
    }

    @Override
    public void onEndElement(XMLElement xmlElement) {
        depth--;
        if (depth <= matchedToDepth) {
            matchedToDepth--;
        }
    }
}
