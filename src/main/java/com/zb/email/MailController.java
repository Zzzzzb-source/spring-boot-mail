package com.zb.email;

import com.zb.common.ReturnModel;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author itmuch.com
 */
@SuppressWarnings("ALL")
@RestController
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private Configuration freemarkerConfiguration;

    /**
     * 简单邮件测试
     */
    @GetMapping("/simple")
    public ReturnModel simple() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人邮箱
        message.setFrom(this.mailProperties.getUsername());
        // 收信人邮箱
        message.setTo("45856713@qq.com");
        // 邮件主题
        message.setSubject("简单邮件测试");
        // 邮件内容
        message.setText("简单邮件测试");
        this.javaMailSender.send(message);
        return ReturnModel.ok().setMessage("简单邮件测试");
    }

    /**
     * HTML内容邮件测试
     */
    @GetMapping("/html")
    public ReturnModel html() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("45856713@qq.com");
        messageHelper.setSubject("HTML内容邮件测试");
        // 第二个参数表示是否html，设为true
        messageHelper.setText("<h1>HTML内容..</h1>", true);

        this.javaMailSender.send(message);
        return ReturnModel.ok().setMessage("HTML内容邮件测试");
    }

    /**
     * 带附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/attach")
    public ReturnModel attach() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        // 第二个参数表示是否开启multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("45856713@qq.com");
        messageHelper.setSubject("带附件的邮件测试");
        // 第二个参数表示是否html，设为true
        messageHelper.setText("<h1>HTML内容..</h1>", true);
        messageHelper.addAttachment("附件名称",
                new ClassPathResource("wx.jpg"));

        this.javaMailSender.send(message);
        return ReturnModel.ok().setMessage("带附件的邮件测试");
    }

    /**
     * 内联附件的邮件测试
     */
    @GetMapping("/inline-attach")
    public ReturnModel inlineAttach() throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        // 第二个参数表示是否开启multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(new InternetAddress("785894044@qq.com", "那你有点牛逼哦", "UTF-8"));
        messageHelper.setTo("2567573530@qq.com");
        messageHelper.setSubject("以后机会多");
        // 第二个参数表示是否html，设为true
        messageHelper.setText("<html>   +   </html>", true);
        messageHelper.addInline("attach", new ClassPathResource("以后机会多.png"));

        this.javaMailSender.send(message);
        return ReturnModel.ok().setMessage("内联附件的邮件测试");
    }

    /**
     * 内联附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/freemarker")
    public ReturnModel freemarker() throws MessagingException, IOException, TemplateException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        // 第二个参数表示是否开启multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("45856713@qq.com");
        messageHelper.setSubject("基于freemarker模板的邮件测试");

        Map<String, Object> model = new HashMap<>();
        model.put("username", "zb");
        model.put("event", "以后机会多");

        String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                this.freemarkerConfiguration.getTemplate("mail.ftl"), model);

        // 第二个参数表示是否html，设为true
        messageHelper.setText(content, true);

        this.javaMailSender.send(message);
        return ReturnModel.ok().setMessage("内联附件的邮件测试");
    }
}
