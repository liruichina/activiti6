package com.abc.springbootactiviti.demo.beans;

import java.util.List;
import java.util.Map;

/**
 * 存储模拟数据
 */
public class RunTestData {

    private Node startNode;

    private Node createCard;

    private List<Node> check;

    private String stopUser;

    public class Node{
        private String asssignee;

        private List<String> candidate;

        private List<String> candidateGroup;

        private Map<String,String> option;

        public String getAsssignee() {
            return asssignee;
        }

        public void setAsssignee(String asssignee) {
            this.asssignee = asssignee;
        }

        public List<String> getCandidate() {
            return candidate;
        }

        public void setCandidate(List<String> candidate) {
            this.candidate = candidate;
        }

        public List<String> getCandidateGroup() {
            return candidateGroup;
        }

        public void setCandidateGroup(List<String> candidateGroup) {
            this.candidateGroup = candidateGroup;
        }

        public Map<String, String> getOption() {
            return option;
        }

        public void setOption(Map<String, String> option) {
            this.option = option;
        }
    }

    public Node getCreateCard() {
        return createCard;
    }

    public void setCreateCard(Node createCard) {
        this.createCard = createCard;
    }

    public List<Node> getCheck() {
        return check;
    }

    public void setCheck(List<Node> check) {
        this.check = check;
    }

    public String getStopUser() {
        return stopUser;
    }

    public void setStopUser(String stopUser) {
        this.stopUser = stopUser;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }
}
