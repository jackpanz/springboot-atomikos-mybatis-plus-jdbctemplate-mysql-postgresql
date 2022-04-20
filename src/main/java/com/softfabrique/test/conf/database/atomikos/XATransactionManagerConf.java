package com.softfabrique.test.conf.database.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Configuration
public class XATransactionManagerConf {

    @Bean
    UserTransaction atomikosUserTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);
        return userTransactionImp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    UserTransactionManager atomikosUserTransactionManager() {
        return new UserTransactionManager();
    }

    @Bean("atomikosTransaction")
    JtaTransactionManager transactionManager(
            @Qualifier("atomikosUserTransactionManager") UserTransactionManager atomikosUserTransactionManager,
            @Qualifier("atomikosUserTransaction") UserTransaction atomikosUserTransaction
    ) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(atomikosUserTransactionManager);
        jtaTransactionManager.setUserTransaction(atomikosUserTransaction);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        jtaTransactionManager.afterPropertiesSet();
        return jtaTransactionManager;
    }

}
