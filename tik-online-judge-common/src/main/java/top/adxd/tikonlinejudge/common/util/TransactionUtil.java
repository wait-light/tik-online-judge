package top.adxd.tikonlinejudge.common.util;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class TransactionUtil {
    private TransactionUtil() {
    }

    /**
     * æćšćæ»
     */
    public static void rollback() {
        TransactionAspectSupport.currentTransactionStatus()
                .setRollbackOnly();
    }
}
