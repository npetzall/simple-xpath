package npetzall.xpath.simple.at;

import npetzall.xpath.simple.api.XPathProcessorFactory;
import npetzall.xpath.simple.io.XMLResources;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleUseCaseTest {

    @Test(enabled = false)
    public void simpleUsageNoNameSpace() {
        XPathProcessorFactory xPathProcessorFactory = XPathProcessorFactory
                .builder()
                .addPrefixAndNamespace("","")
                .addExtractAttributeLast("",new QName("id"), "id")
                .addExtractTextLast("", "text")
                .addSetOnMatch("","selector","something")
                .build();

        InputStream inputStream = new ByteArrayInputStream(XMLResources.getAdvanceXMLWithoutNamespace().getBytes(StandardCharsets.UTF_8));
        Map<String,String> data = xPathProcessorFactory.newProcessor().process(inputStream);
        assertThat(data).isNotNull();
        assertThat(data.containsKey("id")).isTrue();
        assertThat(data.containsKey("text")).isTrue();
        assertThat(data.containsKey("selector")).isTrue();
    }

}
