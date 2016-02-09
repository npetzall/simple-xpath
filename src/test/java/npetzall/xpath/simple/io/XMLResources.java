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

    public static String getAdvanceXMLWithoutNamespace() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<settings>\n" +
                "  <section id=\"videos\">\n" +
                "    <category id=\"videoacceleration\">\n" +
                "      <group id=\"1\">\n" +
                "        <visible>false</visible>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.usemmal\" type=\"boolean\" label=\"36434\" help=\"36435\">\n" +
                "          <level>2</level>\n" +
                "          <default>true</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.limitguiupdate\" type=\"integer\" label=\"38013\" help=\"38014\">\n" +
                "          <level>2</level>\n" +
                "          <default>10</default>\n" +
                "          <constraints>\n" +
                "            <minimum label=\"38015\">0</minimum> <!-- Unlimited -->\n" +
                "            <step>5</step>\n" +
                "            <maximum>25</maximum>\n" +
                "          </constraints>\n" +
                "          <control type=\"spinner\" format=\"string\">\n" +
                "            <formatlabel>38016</formatlabel>\n" +
                "          </control>\n" +
                "          <control type=\"edit\" format=\"integer\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.supportmvc\" type=\"boolean\" label=\"38027\" help=\"38028\">\n" +
                "          <level>2</level>\n" +
                "          <default>true</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "    <category id=\"myvideos\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"myvideos.extractchapterthumbs\">\n" +
                "          <default>false</default>\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "  </section>\n" +
                "\n" +
                "  <section id=\"system\">\n" +
                "    <category id=\"videoscreen\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"videoscreen.screen\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.blankdisplays\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.fakefullscreen\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.textures32\" type=\"boolean\" label=\"37020\" help=\"36547\">\n" +
                "          <level>2</level>\n" +
                "          <default>false</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.limitgui\" type=\"integer\" label=\"37021\" help=\"36548\">\n" +
                "          <level>2</level>\n" +
                "          <default>0</default>\n" +
                "          <constraints>\n" +
                "            <options>\n" +
                "              <option label=\"37026\">0</option>    <!-- auto -->\n" +
                "              <option label=\"37027\">540</option>  <!-- 540 -->\n" +
                "              <option label=\"37028\">720</option>  <!-- 720 -->\n" +
                "              <option label=\"37029\">900</option>  <!-- 900 -->\n" +
                "              <option label=\"37030\">1080</option> <!-- unlimited -->\n" +
                "            </options>\n" +
                "          </constraints>\n" +
                "          <control type=\"spinner\" format=\"string\" />\n" +
                "          <control type=\"edit\" format=\"integer\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"5\">\n" +
                "        <setting id=\"videoscreen.framepacking\" type=\"boolean\" label=\"38029\" help=\"38030\">\n" +
                "          <level>2</level>\n" +
                "          <default>false</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "    <category id=\"audiooutput\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"audiooutput.processquality\">\n" +
                "          <default>101</default> <!-- AE_QUALITY_GPU -->\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"audiooutput.ac3transcode\" help=\"37024\">\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "  </section>\n" +
                "</settings>";
    }
    public static int getAdvancedXMLWithoutNamespaceStartElementCount() { return 66; }

    public static String getAdvanceXMLWithNamespace() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<settings xmlns=\"http://kodi/system/settings\">\n" +
                "  <section id=\"videos\">\n" +
                "    <category id=\"videoacceleration\">\n" +
                "      <group id=\"1\">\n" +
                "        <visible>false</visible>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.usemmal\" type=\"boolean\" label=\"36434\" help=\"36435\">\n" +
                "          <level>2</level>\n" +
                "          <default>true</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.limitguiupdate\" type=\"integer\" label=\"38013\" help=\"38014\">\n" +
                "          <level>2</level>\n" +
                "          <default>10</default>\n" +
                "          <constraints>\n" +
                "            <minimum label=\"38015\">0</minimum> <!-- Unlimited -->\n" +
                "            <step>5</step>\n" +
                "            <maximum>25</maximum>\n" +
                "          </constraints>\n" +
                "          <control type=\"spinner\" format=\"string\">\n" +
                "            <formatlabel>38016</formatlabel>\n" +
                "          </control>\n" +
                "          <control type=\"edit\" format=\"integer\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"videoplayer.supportmvc\" type=\"boolean\" label=\"38027\" help=\"38028\">\n" +
                "          <level>2</level>\n" +
                "          <default>true</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "    <category id=\"myvideos\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"myvideos.extractchapterthumbs\">\n" +
                "          <default>false</default>\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "  </section>\n" +
                "\n" +
                "  <section id=\"system\">\n" +
                "    <category id=\"videoscreen\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"videoscreen.screen\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.blankdisplays\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.fakefullscreen\">\n" +
                "          <visible>false</visible>\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.textures32\" type=\"boolean\" label=\"37020\" help=\"36547\">\n" +
                "          <level>2</level>\n" +
                "          <default>false</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "        <setting id=\"videoscreen.limitgui\" type=\"integer\" label=\"37021\" help=\"36548\">\n" +
                "          <level>2</level>\n" +
                "          <default>0</default>\n" +
                "          <constraints>\n" +
                "            <options>\n" +
                "              <option label=\"37026\">0</option>    <!-- auto -->\n" +
                "              <option label=\"37027\">540</option>  <!-- 540 -->\n" +
                "              <option label=\"37028\">720</option>  <!-- 720 -->\n" +
                "              <option label=\"37029\">900</option>  <!-- 900 -->\n" +
                "              <option label=\"37030\">1080</option> <!-- unlimited -->\n" +
                "            </options>\n" +
                "          </constraints>\n" +
                "          <control type=\"spinner\" format=\"string\" />\n" +
                "          <control type=\"edit\" format=\"integer\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"5\">\n" +
                "        <setting id=\"videoscreen.framepacking\" type=\"boolean\" label=\"38029\" help=\"38030\">\n" +
                "          <level>2</level>\n" +
                "          <default>false</default>\n" +
                "          <control type=\"toggle\" />\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "    <category id=\"audiooutput\">\n" +
                "      <group id=\"1\">\n" +
                "        <setting id=\"audiooutput.processquality\">\n" +
                "          <default>101</default> <!-- AE_QUALITY_GPU -->\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "      <group id=\"3\">\n" +
                "        <setting id=\"audiooutput.ac3transcode\" help=\"37024\">\n" +
                "        </setting>\n" +
                "      </group>\n" +
                "    </category>\n" +
                "  </section>\n" +
                "</settings>";
    }
    public static int getAdvancedXMLWithNamespaceStartElementCount() { return 66; }
}
