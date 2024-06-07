package ed.uopp.uoppemailsender.util;


import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

public final class MarkdownUtil {
    private static final Parser parser = Parser.builder().build();
    private static final HtmlRenderer renderer = HtmlRenderer.builder().build();

    private MarkdownUtil() {
    }

    public static String convertMarkdownToHtml(String markdownText) {
        Node document = parser.parse(markdownText);
        return renderer.render(document);
    }
}
