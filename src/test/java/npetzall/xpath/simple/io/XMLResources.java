package npetzall.xpath.simple.io;

public class XMLResources {

    private XMLResources() {}

    public static String getSimpleXMLWithoutNamespaces() {
        return "<root><one><two>hello</two></one></root>";
    }
    public static int getSimpleXMLWithoutNamespacesStartElementCount() { return 3; }

    public static String getSimpleXMLWithTextWithspaceAndAttribute() {
        return "<root><one>hello</one><two id=\"2\">    </two></root>";
    }
    public static int getSimpleXMLWithTextWithspaceAndAttributeStartElementCount() { return 3; }

    public static String getXMLWithOnlyXMLDeclartion() { return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; }
    public static int getXMLWithOnlyXMLDeclarationStartElementCount() { return 0; }

    public static String getInvalidXML() { return "<?xml?<?<xml/>"; }
    public static int getInvalidXMLStartElementCount() { return 0; }
}
