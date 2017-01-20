package org.suzuki.data.timeout;

import org.suzuki.algorithm.queue.suzuki.SuzukiEvent;
import org.suzuki.algorithm.queue.suzuki.SuzukiEventVisitor;

public class ElectionBroadcastTimeout implements SuzukiEvent {

    @Override
    public void accept(SuzukiEventVisitor suzukiEventVisitor) {
        suzukiEventVisitor.visit(this);
    }
}