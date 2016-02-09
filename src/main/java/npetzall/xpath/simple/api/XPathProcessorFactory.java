package npetzall.xpath.simple.api;

import npetzall.xpath.simple.callback.Callbacks;
import npetzall.xpath.simple.xpath.XPathMatcherBuilder;
import npetzall.xpath.simple.xpath.XPathMatcherBuilderFactory;
import npetzall.xpath.simple.xpath.XPathProcessor;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XPathProcessorFactory {

    private final List<XPathMatcherBuilder> xPathMatcherBuilders;

    private XPathProcessorFactory(List<XPathMatcherBuilder> xPathMatcherBuilders) {
        this.xPathMatcherBuilders = xPathMatcherBuilders;
    }

    public static Builder builder() {
        return new Builder();
    }

    public XPathProcessor newProcessor() {
       return new XPathProcessor(xPathMatcherBuilders);
    }

    public static class Builder {

        private Map<String,String> prefixNamespace = new HashMap<>();
        private ArrayList<XPathMatcherBuilderFactory> xPathMatcherBuilderFactories = new ArrayList<>();

        public Builder addPrefixAndNamespace(String prefix, String namespace) {
            prefixNamespace.put(prefix,namespace);
            return this;
        }

        public Builder addExtractAttributeFirst(String xPath, QName attributeName, String key) {
            xPathMatcherBuilderFactories.add(new XPathMatcherBuilderFactory(xPath,  Callbacks.extractAttributeFirst(attributeName, key)));
            return this;
        }

        public Builder addExtractAttributeLast(String xPath, QName attributeName, String key) {
            xPathMatcherBuilderFactories.add(new XPathMatcherBuilderFactory(xPath, Callbacks.extractAttributeLast(attributeName, key)));
            return this;
        }

        public Builder addExtractTextFirst(String xPath, String key) {
            xPathMatcherBuilderFactories.add(new XPathMatcherBuilderFactory(xPath, Callbacks.extractTextFirst(key)));
            return this;
        }

        public Builder addExtractTextLast(String xPath, String key) {
            xPathMatcherBuilderFactories.add(new XPathMatcherBuilderFactory(xPath, Callbacks.extractTextLast(key)));
            return this;
        }

        public Builder addSetOnMatch(String xPath, String key, String value) {
            xPathMatcherBuilderFactories.add(new XPathMatcherBuilderFactory(xPath, Callbacks.setOnMatch(key, value)));
            return this;
        }

        public XPathProcessorFactory build() {
            return new XPathProcessorFactory(xPathMatcherBuilderFactories.stream().map(xPathMatcherBuilderFactory -> xPathMatcherBuilderFactory.build(prefixNamespace)).collect(Collectors.toList()));
        }
    }
}
