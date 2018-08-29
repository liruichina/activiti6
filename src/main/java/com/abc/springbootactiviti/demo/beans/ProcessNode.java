package com.abc.springbootactiviti.demo.beans;

import java.util.List;
import java.util.Map;

public class ProcessNode {

    private Map<String,String> startNode;

    private Map<String,Object> createCardNode;

    private Map<String,Object> checkNode;

    private String stopNode;

    public Map<String, String> getStartNode() {
        return startNode;
    }

    public void setStartNode(Map<String, String> startNode) {
        this.startNode = startNode;
    }

    public Map<String, Object> getCreateCardNode() {
        return createCardNode;
    }

    public void setCreateCardNode(Map<String, Object> createCardNode) {
        this.createCardNode = createCardNode;
    }

    public Map<String, Object> getCheckNode() {
        return checkNode;
    }

    public void setCheckNode(Map<String, Object> checkNode) {
        this.checkNode = checkNode;
    }

    public String getStopNode() {
        return stopNode;
    }

    public void setStopNode(String stopNode) {
        this.stopNode = stopNode;
    }
}
