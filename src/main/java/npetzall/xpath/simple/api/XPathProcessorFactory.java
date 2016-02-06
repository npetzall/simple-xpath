package npetzall.xpath.simple.api;

import npetzall.xpath.simple.callback.Callbacks;
import npetzall.xpath.simple.xpath.XPath;
import npetzall.xpath.simple.xpath.XPathProcessor;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XPathProcessorFactory {

    private final Map<XPath, XPathMatcherCallBack> xPathAndXPathMatcherCallBack;

    private XPathProcessorFactory(Map<String,String> prefixNamespace, Map<String,XPathMatcherCallBack> xPathStrAndCallbacks) {
        final HashMap<XPath, XPathMatcherCallBack> tmpXPathAndXPathMatcherCallBackMap = new HashMap<>();
        xPathStrAndCallbacks.forEach((xPath, xPathMatcherCallBack) ->
                tmpXPathAndXPathMatcherCallBackMap.put(XPath.parse(xPath,prefixNamespace), xPathMatcherCallBack)
        );
        xPathAndXPathMatcherCallBack = Collections.unmodifiableMap(tmpXPathAndXPathMatcherCallBackMap);
    }

    public static Builder builder() {
        return new Builder();
    }

    public XPathProcessor newProcessor() {
       return new XPathProcessor(xPathAndXPathMatcherCallBack);
    }

    public static class Builder {

        private Map<String,String> prefixNamespace = new HashMap<>();
        private Map<String, XPathMatcherCallBack> xPathAndCallBack = new HashMap<>();

        public Builder addPrefixAndNamespace(String prefix, String namespace) {
            prefixNamespace.put(prefix,namespace);
            return this;
        }

        public Builder addExtractAttributeFirst(String xPath, QName attributeName, String key) {
            xPathAndCallBack.put(xPath, Callbacks.extractAttributeFirst(attributeName, key));
            return this;
        }

        public Builder addExtractAttributeLast(String xPath, QName attributeName, String key) {
            xPathAndCallBack.put(xPath, Callbacks.extractAttributeLast(attributeName, key));
            return this;
        }

        public Builder addExtractTextFirst(String xPath, String key) {
            xPathAndCallBack.put(xPath, Callbacks.extractTextFirst(key));
            return this;
        }

        public Builder addExtractTextLast(String xPath, String key) {
            xPathAndCallBack.put(xPath, Callbacks.extractTextLast(key));
            return this;
        }

        public Builder addSetOnMatch(String xPath, String key, String value) {
            xPathAndCallBack.put(xPath, Callbacks.setOnMatch(key, value));
            return this;
        }

        public XPathProcessorFactory build() {
            return new XPathProcessorFactory(prefixNamespace, xPathAndCallBack);
        }
    }
}
