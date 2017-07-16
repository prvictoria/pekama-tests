package Steps.Objects.Emails;

import Steps.MessagesIMAP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.SearchTerm;
import java.io.IOException;

public class EmailUtils {
    final static Logger rootLogger = LogManager.getRootLogger();
    public static boolean setReadFlag(Message message) throws MessagingException {
        message.setFlag(Flags.Flag.SEEN, true);
        return true;
    }

    public static SearchTerm searchTermSubject(final String keyword) {
        // creates a search criterion
        SearchTerm searchCondition = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    if (message.getSubject().contains(keyword)) {
                        return true;
                    }
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
        };
        rootLogger.info(searchCondition);
        return searchCondition;
    }

    //EMAIL PARSE STEPS
    public static String parseHtml(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        rootLogger.info(link);
        rootLogger.info("--------------------------------");

        String text = doc.body().text(); // "An example link"
        String linkHref = link.attr("href"); // "http://example.com/"
        rootLogger.info("Link attribute "+linkHref);
        rootLogger.info("--------------------------------");
        String linkText = link.text(); // "example""
        rootLogger.info(linkText);
        rootLogger.info("--------------------------------");

        String linkOuterH = link.outerHtml();
        rootLogger.info(linkOuterH);
        rootLogger.info("--------------------------------");
        // "<a href="http://example.com"><b>example</b></a>"
        String linkInnerH = link.html(); // "<b>example</b>"
        rootLogger.info(linkInnerH);
        rootLogger.info("--------------------------------");
        return linkHref;
    }

    public static String parseHtmlHref(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        String linkHref = link.attr("href"); // "http://example.com/"
        rootLogger.info("Link attribute "+linkHref);
        rootLogger.info("--------------------------------");
        return linkHref;
    }

    public static Elements parseHtmlHrefArray(String html){
        Document doc = Jsoup.parse(html);
        Elements link = doc.getElementsByAttribute("href");
        printAllLInks(link);
        return link;
    }

    public static void printAllLInks(Elements link){
        Integer size = link.size();
        Integer i = 0;
        while (size>0) {
            rootLogger.info("Link attribute " + link.get(i).attr("href"));
            rootLogger.info("--------------------------------");
            size--;
            i++;
        }
    }

    public static String getLink(Elements links, Integer index){
        String link = links.get(index).attr("href");
        rootLogger.info("Link #"+index+": "+link);
        rootLogger.info("-------------------------------------");
        return link;
    }

    public static String parseHtmlLinkText(String html){
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a[href]").first();
        String linkText = link.text(); // "example""
        rootLogger.info(linkText);
        rootLogger.debug("--------------------------------");
        return linkText;
    }

    public static Document document(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }

    public static Document parseCleanHtml(Document doc) throws IOException {
        // Load and parse the document fragment.
        doc.select("script, style, .hidden").remove();
        rootLogger.info(doc);
        // Remove all style and event-handler attributes from all elements.
        Elements all = doc.select("*");
        for (Element el : all) {
            for (Attribute attr : el.attributes()) {
                String attrKey = attr.getKey();
                if (attrKey.equals("style") || attrKey.startsWith("on")) {
                    el.removeAttr(attrKey);
                }
            }
        }
        rootLogger.info(doc);
        // See also - doc.select("*").removeAttr("style");
        return doc;
    }

    public static String parsedEmailToText(String html){
        Document doc = Jsoup.parse(html);
        rootLogger.debug(doc);
        String text = doc.text();
        rootLogger.info(text);
        return text;
    }

    public static String getHtmlElementByTag(Document document, String tagName, int index){
        String element = document.getElementsByTag(tagName).get(index).html();
        return element;
    }
}
