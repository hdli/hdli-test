package com.example.hdlitest.tool;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 *
 * @author <a href="mailto:penghaozi.phz@alibaba-inc.com">penghaozi</a>
 * @version 1.0.0
 * @since 2024-12-13
 */
@Data
public class HtmlAttributeReplace implements Serializable {
    private static final long serialVersionUID = -214449834621301774L;
    private String attributeName;
    private String dataType;
    private String replaceKey;

    /**
     * origin-target
     */
    private List<Pair<Object, Object>> replaceValueList;

    private Predicate<JSONObject> predict;

    private Predicate<Element> predictElement;
}
