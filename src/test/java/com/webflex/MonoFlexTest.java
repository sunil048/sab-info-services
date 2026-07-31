package com.webflex;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFlexTest {

    @Test
    public void testMono() {
        Mono <String> monoString = Mono.just("My first").log();
        monoString.subscribe(System.out::println);
    }
}
