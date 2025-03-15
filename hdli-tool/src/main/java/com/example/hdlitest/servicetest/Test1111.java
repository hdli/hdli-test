package com.example.hdlitest.servicetest;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.hdlitest.tool.HtmlAttributeReplace;
import com.example.hdlitest.tool.HtmlUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * @author luyi
 * @date 2025/1/7 20:03
 */
public class Test1111 {


    public static void main(String[] args) {
        String textHtml = "<tag data-info-data-type=\"文本\" class=\"agent-lab-mentions-tag agent-lab-mentions-tag__info\" contenteditable=\"false\" data-keywords=\"德玛\" data-ref-info=\"{&quot;nodeName&quot;:&quot;信息收集&quot;,&quot;refType&quot;:&quot;agent-info-gather&quot;,&quot;refId&quot;:907,&quot;refName&quot;:&quot;德玛&quot;,&quot;nodeId&quot;:&quot;76b1e132-e459-45c9-a021-e5520dcbbf7a&quot;}\" data-ref-id=\"907\"><img src=\"https://img.alicdn.com/imgextra/i3/O1CN01w2zEV61JkHBMPBqpg_!!6000000001066-2-tps-48-48.png\" alt=\"\" class=\"agent-lab-mentions-tag-icon\"><span class=\"agent-lab-mentions-tag-label agent-lab-mentions-tag-name__line\">德玛</span>\\n      <div class=\"agent-lab-mentions-tag-close-container\">\\n        <span class=\"agent-lab-mentions-tag-close-mask\"></span>\\n        <div class=\"agent-lab-mentions-tag-close-icon\">\\n          <img class=\"agent-lab-mentions-tag-close\" alt=\"\" src=\"https://img.alicdn.com/imgextra/i3/O1CN01A0TEPr1iOASPecZgQ_!!6000000004402-2-tps-32-32.png\">\\n        </img></div>\\n      </div>\\n    </img></tag>";

        String simpleHtml = "<tag data-info-data-type=\"文本\" class=\"agent-lab-mentions-tag agent-lab-mentions-tag__info\" contenteditable=\"false\" data-keywords=\"德玛\" data-ref-info=\"\" data-ref-id=\"907\" style=\"position: static\"><img src=\"https://img.alicdn.com/imgextra/i3/O1CN01w2zEV61JkHBMPBqpg_!!6000000001066-2-tps-48-48.png\" alt=\"\" class=\"agent-lab-mentions-tag-icon\"><span class=\"agent-lab-mentions-tag-label agent-lab-mentions-tag-name__line\">德玛</span>\\n      \\n    </tag>";
        
        Map<Long, TenantAgentUserMemory> memoryMap = new HashMap<>();
        TenantAgentUserMemory tenantAgentUserMemory = new TenantAgentUserMemory();
        tenantAgentUserMemory.setId(1204L);
        memoryMap.put(907L, tenantAgentUserMemory);
        List<AgentNodeTextReferInfo.ReferInfo> refInfoList = new ArrayList<>();
        AgentNodeTextReferInfo.ReferInfo referInfo1 = new AgentNodeTextReferInfo.ReferInfo();
        referInfo1.setNodeName("信息收集");
        referInfo1.setNodeId("76b1e132-e459-45c9-a021-e5520dcbbf7a");
        referInfo1.setRefId("907");
        referInfo1.setRefName("德玛");
        referInfo1.setRefType(AgentRefTypeEnum.KEYWORD_INFO.getType());
        refInfoList.add(referInfo1);

        AgentNodeTextReferInfo agentNodeTextReferInfo = new AgentNodeTextReferInfo();
        agentNodeTextReferInfo.setTextHtml(textHtml);
        agentNodeTextReferInfo.setSimpleHtml(simpleHtml);
        agentNodeTextReferInfo.setRefInfoList(refInfoList);

        List<HtmlAttributeReplace> replaceList = new ArrayList<>();
        List<Pair<Object, Object>> replaceValueList = new ArrayList<>();

        for (AgentNodeTextReferInfo.ReferInfo referInfo : agentNodeTextReferInfo.getRefInfoList()) {
            if (referInfo == null) {
                continue;
            }
            if (AgentRefTypeEnum.KEYWORD_INFO.getType().equals(referInfo.getRefType())) {
                String refId = referInfo.getRefId();
                String replaceKey = referInfo.buildReplaceKey();
                TenantAgentUserMemory memory = memoryMap.get(Long.valueOf(refId));
                if (memory == null) {
                    continue;
                }
                String targetId = String.valueOf(memory.getId());
                referInfo.setRefId(targetId);
                String targetKey = referInfo.buildReplaceKey();
                replaceValueList.add(Pair.of(refId, targetId));
                agentNodeTextReferInfo.setText(StrUtil.replace(agentNodeTextReferInfo.getText(), replaceKey, targetKey));
            }
        }

        HtmlAttributeReplace replaceSingle = new HtmlAttributeReplace();
        replaceSingle.setAttributeName("data-ref-id");
        replaceSingle.setDataType(HtmlUtils.SINGLE_ATTRIBUTE_DATA_TYPE);
        replaceSingle.setReplaceValueList(replaceValueList);
//        replaceSingle.setPredictElement((e) -> {
//            String attr = e.attr("data-ref-info");
//            if (StringUtils.isBlank(attr) || !JSONUtil.isTypeJSONObject(attr)) {
//                return false;
//            }
//            JSONObject dataRefInfo = JSON.parseObject(attr);
//            return Objects.equals(dataRefInfo.getString("refType"), NodeComponentCodeEnum.AGENT_KEYWORD_COLLECT.getCode());
//        });

        HtmlAttributeReplace replaceObject = new HtmlAttributeReplace();
        replaceObject.setAttributeName("data-ref-info");
        replaceObject.setDataType(HtmlUtils.JSON_OBJECT_ATTRIBUTE_DATA_TYPE);
        replaceObject.setReplaceValueList(replaceValueList);
        replaceObject.setReplaceKey("refId");
        replaceObject.setPredict((k) -> Objects.equals(k.getString("refType"), NodeComponentCodeEnum.AGENT_KEYWORD_COLLECT.getCode()));

        replaceList.add(replaceSingle);
        replaceList.add(replaceObject);

//        String textHtml2 = HtmlUtils.batchReplaceAttribute(agentNodeTextReferInfo.getTextHtml(), "tag", replaceList);
//
//        System.out.println(textHtml2);

        System.out.println("------------------");
        String simpleHtml2 = HtmlUtils.batchReplaceAttribute(agentNodeTextReferInfo.getSimpleHtml(), "tag", Lists.newArrayList(replaceSingle));
        System.out.println(simpleHtml2);


    }
}
