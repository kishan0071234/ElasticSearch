
package org.elastic.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "historyId",
    "id",
    "internalDate",
    "labelIds",
    "payload",
    "sizeEstimate",
    "snippet",
    "threadId"
})
public class Source {

    @JsonProperty("historyId")
    private String historyId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("internalDate")
    private String internalDate;
    @JsonProperty("labelIds")
    private List<String> labelIds = null;
    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("sizeEstimate")
    private Integer sizeEstimate;
    @JsonProperty("snippet")
    private String snippet;
    @JsonProperty("threadId")
    private String threadId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("historyId")
    public String getHistoryId() {
        return historyId;
    }

    @JsonProperty("historyId")
    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("internalDate")
    public String getInternalDate() {
        return internalDate;
    }

    @JsonProperty("internalDate")
    public void setInternalDate(String internalDate) {
        this.internalDate = internalDate;
    }

    @JsonProperty("labelIds")
    public List<String> getLabelIds() {
        return labelIds;
    }

    @JsonProperty("labelIds")
    public void setLabelIds(List<String> labelIds) {
        this.labelIds = labelIds;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonProperty("sizeEstimate")
    public Integer getSizeEstimate() {
        return sizeEstimate;
    }

    @JsonProperty("sizeEstimate")
    public void setSizeEstimate(Integer sizeEstimate) {
        this.sizeEstimate = sizeEstimate;
    }

    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @JsonProperty("threadId")
    public String getThreadId() {
        return threadId;
    }

    @JsonProperty("threadId")
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
