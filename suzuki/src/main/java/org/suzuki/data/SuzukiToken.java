package org.suzuki.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.suzuki.queue.event.EventVisitor;

import java.util.List;

@ToString(callSuper = true, includeFieldNames = false)
public class SuzukiToken extends Message {

    @Getter
    @Setter
    private SuzukiTokenBody value;

    public SuzukiToken() {
        type = TYPE_TOKEN;
    }

    @Override
    public void accept(EventVisitor eventVisitor) {
        eventVisitor.visit(this);
    }

    public int numberOf(int nodeId) {
        for(SuzukiTokenBodyElement element : getValue().getLastRequests()) {
            if(nodeId == element.getNodeId()) {
                return element.getNumber();
            }
        }

        throw new RuntimeException("No entry found for " + nodeId + " in " + this);
    }

    public void setNumber(int nodeId, int number) {
        for(SuzukiTokenBodyElement element : getValue().getLastRequests()) {
            if(nodeId == element.getNodeId()) {
                element.setNumber(number);
                return;
            }
        }

        throw new RuntimeException("No entry found for " + nodeId + " in " + this);
    }

    public void appendProcesses(List<Integer> nodeIds) {
        nodeIds.forEach( nodeId -> {
            if(!getValue().getQueue().contains(nodeId)) {
                getValue().getQueue().add(nodeId);
            }
        });
    }

}
