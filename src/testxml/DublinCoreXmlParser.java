package testxml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang3.StringEscapeUtils;   
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Ritesh
 */
public class DublinCoreXmlParser 
{
    /**
     * This method used to parse Dublin Core Standard XML file
     * @param inputStream
     *      Set path of XML file to be parsed
     * @return 
     *      return Map,key as DC tag name and value as List of corresponding values 
     * @throws java.lang.Exception
     */
    public Map<String,List<String>> parse(InputStream  inputStream) throws Exception
    {
        //get the DOM builder factory.
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        
        //get the DOM Builder
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        
//        Document document =builder.parse(xmlFilePath);
//        Reader reader = new InputStreamReader(inputStream);
        //this function is only for Museum data need to change or remove for other data
        Reader reader = new InputStreamReader(inputStream, "latin1");
        InputSource is = new InputSource(reader);
        is.setEncoding("latin1");
        Document document =builder.parse(is);
        
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        
        Map<String,List<String>> dublinCoreXmlTagValueMap=new HashMap<>();
        String tagName;
        List<String> valueList;
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node=nodeList.item(i);
            if(!node.getNodeName().equalsIgnoreCase("#text") && node.getTextContent()!=null && !node.getTextContent().trim().equalsIgnoreCase("null") && !node.getTextContent().trim().equalsIgnoreCase(""))
            {
                if(node.getNodeName().contains(":"))
                    tagName=node.getNodeName().split(":")[1];
                else
                    tagName=node.getNodeName();
                
                if(dublinCoreXmlTagValueMap.containsKey(tagName))
                    valueList=dublinCoreXmlTagValueMap.get(tagName);
                else
                    valueList=new ArrayList<>();
                
                valueList.add(StringEscapeUtils.unescapeXml(node.getTextContent()));
                dublinCoreXmlTagValueMap.put(tagName,valueList);
            }
        }
        return dublinCoreXmlTagValueMap;
    }
}