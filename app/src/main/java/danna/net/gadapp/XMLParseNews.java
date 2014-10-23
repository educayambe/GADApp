package danna.net.gadapp;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 10/20/2014.
 */
public class XMLParseNews {
    private static final String ns = null;
    ArrayList<NewsClass> entries = new ArrayList<NewsClass>();
    public List<NewsClass> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }
    private ArrayList<NewsClass> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {


        parser.require(XmlPullParser.START_TAG, ns, "News");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the NewsClass tag
            if (name.equals("Item")) {
                entries.add(readNewsClass(parser));
            }
        }
        return entries;
    }
    // This class represents a single NewsClass (post) in the XML feed.
    // It includes the data members "title," "link," and "summary."

    // Parses the contents of an NewsClass. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private NewsClass readNewsClass(XmlPullParser parser) throws XmlPullParserException, IOException {
        NewsClass nuevo ;
        parser.require(XmlPullParser.START_TAG, ns, "Item");
        String Name="";
        String Desc="";
        String Image_name="";

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("Title")) {
                Name = readName(parser);
            } else if (name.equals("Detail")) {
                Desc = readDesc(parser);
            } else if (name.equals("Image")) {
                Image_name = readImage(parser);
            }
        }
        nuevo = new NewsClass(Name, Desc, Image_name);
        return nuevo;
    }
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Title");
        return title;
    }
    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private String readDesc(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Detail");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Detail");
        return title;
    }

    private String readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Image");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Image");
        return title;
    }

}

