package com.air.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.commons.lang3.StringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    // 腾讯企业邮箱 也可以换成别家的
    private static final String protocol = "smtp";// 协议
    private static final String host = "smtp.qq.com";// 地址
    private static final String port = "465";// 端口
    private static final String account = "1318860027@qq.com";// 用户名
    private static final String pass = "zuqywgmpryxlhdhg";// 密码
    private static final String personal = "AirMall";// 发件人别名，不需要设为空串或null

    // 权限认证
    static class MyAuthenricator extends Authenticator {
        String u = null;
        String p = null;

        public MyAuthenricator(String u, String p) {
            this.u = u;
            this.p = p;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(u, p);
        }
    }

    /**
     * 发送邮件工具方法
     *
     * @param recipients 收件人
     * @param subject    主题
     * @param content    内容
     * @param fileStr    附件路径
     * @return true/false 发送成功
     */
    public static boolean sendEmail(String recipients, String subject, String content, String fileStr) {
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", protocol);
        //服务器
        prop.setProperty("mail.smtp.host", host);
        //端口
        prop.setProperty("mail.smtp.port", port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
            mailSSLSocketFactory.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);

        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(account, pass));
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //发件人
            if (StringUtils.isNotBlank(personal))
                mimeMessage.setFrom(new InternetAddress(account, personal));//可以设置发件人的别名
            else
                mimeMessage.setFrom(new InternetAddress(account));//如果不需要就省略
            //收件人
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            //主题
            mimeMessage.setSubject(subject);
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类，可以包含多个MimeBodyPart对象
            Multipart mp = new MimeMultipart();

            //MimeBodyPart可以包装文本，图片，附件
            MimeBodyPart body = new MimeBodyPart();
            //HTML正文
            body.setContent(content, "text/html; charset=UTF-8");
            mp.addBodyPart(body);

            //添加图片&附件
            if (StringUtils.isNotBlank(fileStr)) {
                body = new MimeBodyPart();
                body.attachFile(fileStr);
                mp.addBodyPart(body);
            }

            //设置邮件内容
            mimeMessage.setContent(mp);
            //仅仅发送文本
            //mimeMessage.setText(content);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
            // 发送成功
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String EmailContext(String email, String id, String code) {
        StringBuilder context = new StringBuilder("亲爱的" + email + ":<br>");
        context.append("您好！感谢您的注册，请点击以下链接进行激活：<br>");
        context.append("http://api.airblog.top:8080/Air/user/check/" + id + "/" + code);
        context.append("<br>如链接发出后24小时，都未确认激活，此帐号将失效。");
        return String.valueOf(context);
    }
}
