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
public class XMLParseApps {
    private static final String ns = null;
    ArrayList<AppsClass> entries = new ArrayList<AppsClass>();
    public List<AppsClass> parse(InputStream in) throws XmlPullParserException, IOException {
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
    private ArrayList<AppsClass> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {


        parser.require(XmlPullParser.START_TAG, ns, "Apps");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the AppsClass tag
            if (name.equals("Group")) {
                entries.add(readAppsClass(parser));
            }
        }
        return entries;
    }
    // This class represents a single AppsClass (post) in the XML feed.
    // It includes the data members "title," "link," and "summary."

    // Parses the contents of an AppsClass. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private AppsClass readAppsClass(XmlPullParser parser) throws XmlPullParserException, IOException {
        AppsClass nuevo ;
        AppsClassChild child=null;

        parser.require(XmlPullParser.START_TAG, ns, "Group");
        String Name="";
        String NameC="";
        String Desc="";
        String Url="";
        nuevo = new AppsClass();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("Name")) {
                Name = readName(parser);
                nuevo.setNameApp(Name);
            } else if (name.equals("SubGroup")) {
                child = new AppsClassChild();

                parser.require(XmlPullParser.START_TAG, ns, "SubGroup");
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                     name = parser.getName();
                    if (name.equals("Item")) {
                        NameC = readItem(parser);
                    }
                    else  if (name.equals("Url")) {
                        Url = readUrl(parser);
                    }
                }
                child.setName(Name);
                child.setUrl(Url);
                nuevo.setChilds(child);

            }

        }

        return nuevo;
    }
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Name");
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
    private String readItem(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Item");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Item");
        return title;
    }

    private String readUrl(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Url");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Url");
        return title;
    }

}

