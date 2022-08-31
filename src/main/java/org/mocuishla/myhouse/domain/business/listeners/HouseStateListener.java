package org.mocuishla.myhouse.domain.business.listeners;

import org.mocuishla.myhouse.domain.business.model.HouseState;

public interface HouseStateListener {
    void onStateChanged(HouseState newState);
}
