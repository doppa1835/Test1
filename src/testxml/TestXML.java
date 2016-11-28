/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testxml;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HCDC
 */
public class TestXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("D:\\A\\nat_del-2001-19-575-1428\\nat_del-2001-19-575-1428_dissemination\\Document\\XML\\nat_del-2001-19-575-1428_dc.xml");
        DublinCoreXmlParser dcxp = new DublinCoreXmlParser();
        try {
            Map<String, List<String>> map = dcxp.parse(new FileInputStream(f));

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
               String doopa= findAndReplaceUnsupportedUTF8Character(entry.getValue().toString(),"*");
               // System.out.println("=============" + doopa);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String findAndReplaceUnsupportedUTF8Character(String inputString, String replaceString) throws Exception {

        Charset charset = Charset.forName("UTF-8");
        String label = charset.decode(charset.encode(inputString)).toString();
        System.out.println(label);
        return label;
    }

}
