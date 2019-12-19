package com.example.demo.comment.sendemail;

/**
 * 邮件发送Demo
 * @author 860118060
 */
public class SendEmailDemo {
    public static void main(String[] args){
        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 发送方邮箱
        mailInfo.setUserName("18955971653@163.com");
        // 发送方邮箱密码
        mailInfo.setPassword("19960408hao");
        // 发送方邮箱
        mailInfo.setFromAddress("18955971653@163.com");
        // 接收方邮箱
        mailInfo.setToAddress("1393214591@qq.com");
        // 邮件标题
        mailInfo.setSubject("测试用邮箱发送邮件");
        // 邮件内容
        mailInfo.setContent("<h1>邮件内容非常丰富,附带附件哦<h1>");

        //发送文体格式
//        SimpleMailSender.sendTextMail(mailInfo);
        //发送html格式
        SimpleMailSender.sendHtmlMail(mailInfo);
        //发送邮件附带附件
//        SimpleMailSender.sendAttachmentMail(mailInfo);
    }
}
