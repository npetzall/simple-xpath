package npetzall.xpath.simple.api;

@FunctionalInterface
public interface XPathMatcherCallBack {
    boolean matchFound(XMLElement xmlElement);
}
