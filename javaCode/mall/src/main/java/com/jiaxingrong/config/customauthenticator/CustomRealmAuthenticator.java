package com.jiaxingrong.config.customauthenticator;

import com.jiaxingrong.config.token.MallToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    public AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) {
        // assertRealmsConfigured方法是判断Realm是否存在,不存在则会抛出IllegalStateException.
        // 随后根据Realm的个数来判断执行哪个方法
        this.assertRealmsConfigured();
        Collection<Realm> realms = this.getRealms();
        MallToken mallToken = (MallToken) authenticationToken;
        String type = mallToken.getType();
        List<Realm> realmList = new ArrayList<>();
        for(Realm realm : realms) {
            if (realm.getName().toLowerCase().contains(type)) {
                realmList.add(realm);
            }
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken) :
                this.doMultiRealmAuthentication(realms, authenticationToken);

    }
}
