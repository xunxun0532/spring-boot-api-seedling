package com.zoctan.seedling.core.jasypt;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.springframework.stereotype.Component;

/**
 * 自定义被加密值的发现器
 * 默认：ENC(abc)
 * 自定义：MyEnc({abc})
 *
 * @author Zoctan
 * @date 2018/07/20
 */
@Component
public class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {
    /**
     * 前缀
     */
    private static final String PREFIX = "MyEnc({";
    /**
     * 后缀
     */
    private static final String SUFFIX = "})";

    @Override
    public boolean isEncrypted(final String property) {
        if (property == null) {
            return false;
        }
        final String trimmedProperty = property.trim();

        return trimmedProperty.startsWith(PREFIX)
                && trimmedProperty.endsWith(SUFFIX);
    }

    @Override
    public String unwrapEncryptedValue(final String property) {
        return property.substring(PREFIX.length(), property.length() - SUFFIX.length());
    }
}