/oauth/authorize：授权端点。
/oauth/token：令牌端点。
/oauth/confirm_access：用户确认授权提交端点。
/oauth/error：授权服务错误信息端点。
/oauth/check_token：用于资源服务访问的令牌解析端点。
/oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话

##授权码模式

```
http://localhost:8080/oauth/authorize?client_id=client_test&response_type=code&scope=all&redirect_uri=http://www.baidu.com
http://localhost:8080/oauth/token?client_id=client_pontos&client_secret=123&grant_type=authorization_code&redirect_uri=http://www.baidu.com&code=xbmGYF
http://localhost:8080/oauth/check_token?token=c278ae93-c1af-4c78-8da9-52cc48cd758f
```

##密码模式

```
http://localhost:8080/oauth/token?client_id=client_pontos&client_secret=123&username=123&password=123456&grant_type=password&scope=scope1
http://localhost:8899/auth/oauth/token?client_id=client_pontos&client_secret=123&username=123&password=123456&grant_type=password&scope=scope1
```