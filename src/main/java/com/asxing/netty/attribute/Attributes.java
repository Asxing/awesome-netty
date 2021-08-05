package com.asxing.netty.attribute;

import io.netty.util.AttributeKey;

public interface Attributes {
    final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
