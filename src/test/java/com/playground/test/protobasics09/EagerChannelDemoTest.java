package com.playground.test.protobasics09;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagerChannelDemoTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(EagerChannelDemoTest.class);

    @Test
    public void eagerChannelDemo() {
        log.info("{}", channel.getState(true));
    }
}
