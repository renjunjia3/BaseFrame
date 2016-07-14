package com.scene.baseframe.event;


import com.scene.baselib.frame.SupportFragment;

/**
 * Created by scene on 16/6/30.
 */
public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
