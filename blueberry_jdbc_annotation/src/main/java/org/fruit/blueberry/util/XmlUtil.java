package org.fruit.blueberry.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by AFei on 2014/7/21.
 */
public class XmlUtil {
    private static String filePath;

    static {
        filePath = XmlUtil.class.getClassLoader().getResource("users.xml").getPath();
    }

    public static Document getDocument() throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new File(filePath));
    }

    public static void write2Xml(Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
    }
}
