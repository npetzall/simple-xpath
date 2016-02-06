package npetzall.xpath.simple.api;

import npetzall.xpath.simple.xpath.XPathProcessor;

import javax.xml.namespace.QName;

public class XPathProcessorFactory {

    private XPathProcessorFactory() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public XPathProcessor newProcessor() {
        return new XPathProcessor();
    }

    public static class Builder {

        public Builder addPrefixAndNamespace(String prefix, String namespace) {
            return this;
        }

        public Builder addAttributeExtractor(String xPath, QName attributeName, String key) {
            return this;
        }

        public Builder addTextExtractor(String xPath, String key) {
            return this;
        }

        public Builder addSetOnMatch(String xPath, String key, String value) {
            return this;
        }

        public XPathProcessorFactory build() {
            return null;
        }
    }
}
