package org.antlr.v4.codegen.target;

import org.antlr.v4.codegen.CodeGenerator;
import org.antlr.v4.codegen.Target;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.misc.IntegerList;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarAST;
import org.apache.commons.lang3.StringEscapeUtils;
import org.stringtemplate.v4.*;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Created by janyou on 15/9/15.
 */
public class SwiftTarget extends Target {

    /**
     * The Swift target can cache the code generation templates.
     */
    private static final ThreadLocal<STGroup> targetTemplates = new ThreadLocal<STGroup>();

    protected static final String[] swiftKeywords = {
            "associatedtype", "class", "deinit", "enum", "extension", "func", "import", "init", "inout", "internal",
            "let", "operator", "private", "protocol", "public", "static", "struct", "subscript", "typealias", "var",
            "break", "case", "continue", "default", "defer", "do", "else", "fallthrough", "for", "guard", "if",
            "in", "repeat", "return", "switch", "where", "while",
            "as", "catch", "dynamicType", "false", "is", "nil", "rethrows", "super", "self", "Self", "throw", "throws",
            "true", "try", "__COLUMN__", "__FILE__", "__FUNCTION__","__LINE__", "#column", "#file", "#function", "#line", "_" , "#available", "#else", "#elseif", "#endif", "#if", "#selector",
            "associativity", "convenience", "dynamic", "didSet", "final", "get", "infix", "indirect", "lazy",
            "left", "mutating", "none", "nonmutating", "optional", "override", "postfix", "precedence",
            "prefix", "Protocol", "required", "right", "set", "Type", "unowned", "weak", "willSet"
 };

    /** Avoid grammar symbols in this set to prevent conflicts in gen'd code. */
    protected final Set<String> badWords = new HashSet<String>();

    public SwiftTarget(CodeGenerator gen) {
        super(gen, "Swift");
    }

    @Override
    public String getVersion() {
        return "4.5.3"; // Java and tool versions move in lock step
    }

    public Set<String> getBadWords() {
        if (badWords.isEmpty()) {
            addBadWords();
        }

        return badWords;
    }

    protected void addBadWords() {
        badWords.addAll(Arrays.asList(swiftKeywords));
        badWords.add("rule");
        badWords.add("parserRule");
    }

    @Override
    public int getSerializedATNSegmentLimit() {
        // 65535 is the class file format byte limit for a UTF-8 encoded string literal
        // 3 is the maximum number of bytes it takes to encode a value in the range 0-0xFFFF
        return 65535 / 3;
    }

    @Override
    protected boolean visibleGrammarSymbolCausesIssueInGeneratedCode(GrammarAST idNode) {
        return getBadWords().contains(idNode.getText());
    }

    @Override
    protected STGroup loadTemplates() {
        STGroup result = targetTemplates.get();
        if (result == null) {
            result = super.loadTemplates();
            result.registerRenderer(String.class, new SwiftStringRenderer(), true);
            targetTemplates.set(result);
        }

        return result;
    }

    protected static class SwiftStringRenderer extends StringRenderer {

        @Override
        public String toString(Object o, String formatString, Locale locale) {
            if ("swift-escaped".equals(formatString)) {
                String s = StringEscapeUtils.unescapeJava((String)o);
                StringBuffer sb = new StringBuffer();
                for (char c : s.toCharArray()) {
                    sb.append((int)c);
                    sb.append(',');
                }
                return sb.toString();
            }
            return super.toString(o, formatString, locale);
        }
    }
}
