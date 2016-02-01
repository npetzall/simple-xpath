package npetzall.xpath.simple.io;

/**
 * Created by Nosse on 30/01/16.
 */
public class XMLResources {

    private XMLResources() {}

    public static String getSimpleXMLWithoutNamespaces() {
        return "<root><one><two>hello</two></one></root>";
    }
    public static int getSimpleXMLWithoutNamespacesStartElementCount() {
        return 3;
    }
}
