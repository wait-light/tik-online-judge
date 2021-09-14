package top.adxd.tikonlinejudge.common.util;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class TransactionUtil {
    private TransactionUtil() {
    }

    /**
     * 手动回滚
     */
    public static void rollback() {
        TransactionAspectSupport.currentTransactionStatus()
                .setRollbackOnly();
    }
}
