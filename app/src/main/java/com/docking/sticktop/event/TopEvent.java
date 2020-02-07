package com.docking.sticktop.event;

public class TopEvent {
    /**
     * true 代表折叠(置顶)，false 展开
     */
    public boolean isTop = false;

    public TopEvent(boolean isTop) {
        this.isTop = isTop;
    }
}
