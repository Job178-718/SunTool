# JavaEE -----javaMail
## 1.引入maven
```
    <!-- https://mvnrepository.com/artifact/com.sun.mail/javax.mail -->
      <dependency>
          <groupId>com.sun.mail</groupId>
          <artifactId>javax.mail</artifactId>
          <version>1.6.2</version>
      </dependency>
```
## 2.mail配置
```
public class MailTool {

    public static void sendMail(String mail,String title,String context) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.qq.com");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.transport", "smtp");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        //建立两点之间的连接
        System.out.println("执行了2");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2306236479@qq.com", "sefvfifrauizebeg");
            }
        });
        System.out.println("执行了3");

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress("2306236479@qq.com"));
            //设置邮件对象
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(mail));//收件人
            //设置主题
            message.setSubject(title);
            //设置邮件正文 第二个参数邮件发送的类型
            message.setContent(context,"text/html;charset=UTF-8");
            //发送一份邮件
            Transport transport = session.getTransport();
            transport.connect("2306236479@qq.com","sefvfifrauizebeg");
            Transport.send(message);
            System.out.println("执行了");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
```

## 3.测试
```
    @Test
    public void vailMail(){
        MailTool.sendMail("收件邮箱","邮箱主题","正文内容");
    }
```


