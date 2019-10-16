package com.itmuch.email;

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
     *
     * @return success
     */
    @GetMapping("/simple")
    public String simple() {
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
        return "success";
    }

    /**
     * HTML内容邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/html")
    public String html() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("45856713@qq.com");
        messageHelper.setSubject("HTML内容邮件测试");
        // 第二个参数表示是否html，设为true
        messageHelper.setText("<h1>HTML内容..</h1>", true);

        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * 带附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/attach")
    public String attach() throws MessagingException {
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
        return "success";
    }

    /**
     * 内联附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/inline-attach")
    public String inlineAttach() throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        // 第二个参数表示是否开启multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(new InternetAddress("785894044@qq.com", "那你有点牛逼哦", "UTF-8"));
        messageHelper.setTo("2567573530@qq.com");
        messageHelper.setSubject("以后机会多");
        // 第二个参数表示是否html，设为true
        messageHelper.setText("<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "    <meta http-equiv=\"Content-Style-Type\" content=\"text/css\" />\n" +
                "    <meta name=\"generator\" content=\"Aspose.Words for .NET 15.1.0.0\" />\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:center\"><span style=\"color:#333333; font-family:微软雅黑; font-size:18pt; font-weight:bold\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:18pt; font-weight:bold\">隐私政策</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">更新日期：2019 年 0</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">8</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\"> 月 </span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">06</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\"> 日</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">生效日期：2019 年 0</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">8</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\"> 月 </span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">06</span><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\"> 日</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">（</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">以下或</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">简称“我们”）深知个人信息对您的重要性，我们将按照法律法规的规定，保护您的个人信息及隐私安全。我们制定本“隐私政策”并特别提示：希望您在使用</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">及相关服务前仔细阅读并理解本隐私政策，以便做出适当的选择。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:12pt; font-weight:bold\">本隐私政策将帮助你了解：</span></p>\n" +
                "    <ul type=\"disc\" style=\"margin:0pt; padding-left:0pt\">\n" +
                "        <li style=\"background-color:#ffffff; color:#333333; font-family:serif; font-size:10pt; margin:0pt 0pt 3.75pt 9.6pt; padding-left:8.4pt; text-align:justify; text-indent:0pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们会遵循隐私政策收集、使用您的信息，但不会仅因您同意本隐私政策而采用强制捆绑的方式一揽子收集个人信息。</span></li>\n" +
                "        <li style=\"background-color:#ffffff; color:#333333; font-family:serif; font-size:10pt; margin:0pt 0pt 3.75pt 9.6pt; padding-left:8.4pt; text-align:justify; text-indent:0pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">当您使用或开启相关功能或使用服务时，为实现功能、服务所必需，我们会收集、使用相关信息。除非是为实现基本业务功能或根据法律法规要求所必需的必要信息，您均可以拒绝提供且不影响其他功能或服务。我们将在隐私政策中逐项说明哪些是必要信息。</span></li>\n" +
                "        <li style=\"background-color:#ffffff; color:#333333; font-family:serif; font-size:10pt; margin:0pt 0pt 3.75pt 9.6pt; padding-left:8.4pt; text-align:justify; text-indent:0pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">如果您未登录帐号，我们会通过设备对应的标识符信息来保障信息推送的基本功能。如果您登录了账号，我们会根据账号信息实现信息推送。</span></li>\n" +
                "        <li style=\"background-color:#ffffff; color:#333333; font-family:serif; font-size:10pt; margin:0pt 0pt 3.75pt 9.6pt; padding-left:8.4pt; text-align:justify; text-indent:0pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">通讯录、精确地理位置、摄像头、麦克风、相册权限，均不会默认开启，只有经过您的明示授权才会在为实现特定功能或服务时使用，您也可以撤回授权。</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">特别需要</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">指出的是，即使经过您的授权，我们获得了这些敏感权限，也不会在相关功能或服务不需要时而收集您的信息。</span></li>\n" +
                "        <li style=\"background-color:#ffffff; color:#333333; font-family:serif; font-size:10pt; margin:0pt 0pt 3.75pt 9.6pt; padding-left:8.4pt; text-align:justify; text-indent:0pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">本隐私政策适用于您通过</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">应用程序、网页、供第三方网站和应用程序使用的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">应用程序编程接口（API）方式来访问和使用我们的产品和服务。</span></li>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们致力于为您提供安全、可信的产品与使用环境，提供优质而可靠的服务是我们的核心目标。为实现安全保障功能所收集的信息是</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">必要信息</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">1.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">5</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">.2 设备信息与日志信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.为了保障软件与服务的安全运行，我们会收集您的硬件型号、操作系统版本号、国际移动设备识别码、唯一设备标识符、网络设备硬件地址、IP 地址、WLAN接入点、蓝牙、基站、软件版本号、网络接入方式、类型、状态、网络质量数据、操作、使用、服务日志。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.为了预防恶意程序及安全运营所必需，我们会收集安装的应用信息或正在运行的进程信息、应用程序的总体运行、使用情况与频率、应用崩溃情况、总体安装使用情况、性能数据、应用来源。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c.我们可能使用您的账户信息、设备信息、服务日志信息以及我们关联公司、合作方在获得您授权或依法可以共享的信息，用于判断账户安全、进行身份验证、检测及防范安全事件。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">1.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 收集、使用个人信息目的变更</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">请您了解，随着我们业务的发展，可能会对“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”的功能和提供的服务有所调整变化。原则上，当新功能或服务与我们当前提供的功能或服务相关时，收集与使用的个人信息将与原处理目的具有直接或合理关联。在与原处理目的无直接或合理关联的场景下，我们收集、使用您的个人信息，会再次进行告知，并征得您的同意。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">1.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">7</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">依法豁免征得同意收集和使用的个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">请您理解，在下列情形中，根据法律法规及相关国家标准，我们收集和使用您的个人信息无需征得您的授权同意：</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.与国家安全、国防安全直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.与公共安全、公共卫生、重大公共利益直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c.与犯罪侦查、起诉、审判和判决执行等直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">d.出于维护个人信息主体或其他个人的生命、财产等重大合法权益但又很难得到本人同意的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">e.所收集的您的个人信息是您自行向社会公众公开的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">f.从合法公开披露的信息中收集的您的个人信息的，如合法的新闻报道、政府信息公开等渠道；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">g.根据您的要求签订或履行合同所必需的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">h.用于维护软件及相关服务的安全稳定运行所必需的，例如发现、处置软件及相关服务的故障；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">i.为合法的新闻报道所必需的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">j.学术研究机构基于公共利益开展统计或学术研究所必要，且对外提供学术研究或描述的结果时，对结果中所包含的个人信息进行去标识化处理的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">k.法律法规规定的其他情形。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">特别提示您注意，如信息无法单独或结合其他信息识别到您的个人身份，其不属于法律意义上您的个人信息；当您的信息可以单独或结合其他信息识别到您的个人身份时或我们将无法与任何特定个人信息建立联系的数据与其他您的个人信息结合使用时，这些信息在结合使用期间，将作为您的个人信息按照本隐私政策处理与保护。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2.对 Cookie 和同类技术的使用</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">Cookie 和同类技术是互联网中普遍使用的技术。当您使用</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">及相关服务时，我们可能会使用相关技术向您的设备发送一个或多个 Cookie 或匿名标识符，以收集和存储您访问、使用本产品时的信息。我们承诺，不会将 Cookie 用于本隐私政策所述目的之外的任何其他用途。我们使用 Cookie 和同类技术主要为了实现以下功能或服务：</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2.1 保障产品与服务的安全、高效运转</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们可能会设置认证与保障安全性的 cookie 或匿名标识符，使我们确认您是否安全登录服务，或者是否遇到盗用、欺诈及其他不法行为。这些技术还会帮助我们改进服务效率，提升登录和响应速度。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2.2 帮助您获得更轻松的访问体验</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">使用此类技术可以帮助您省去重复您填写个人信息、输入搜索内容的步骤和流程（示例：记录搜索、表单填写）。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2.3 为您推荐、展示、推送您可能感兴趣的内容或账号</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.我们可能会利用 Cookie 和同类技术了解您的偏好和使用习惯，进行数据分析，以改善产品服务、推荐用户感兴趣的信息或功能，并优化您对广告的选择。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.在</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">的分享页中，我们可能会使用cookie对浏览活动进行记录，用于向您推荐信息和排查崩溃、延迟的相关异常情况以及探索更好的服务方式。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2.4 cookie的清除</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">大多数浏览器均为用户提供了清除浏览器缓存数据的功能，您可以在浏览器设置功能中进行相应的数据清除操作。如您进行清除，可能因为这些修改，您可能无法使用依赖于Cookie由公司提供的服务或相应功能。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.我们如何共享、转让、公开披露个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.1共享</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.1.1 共享原则</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">a.授权同意原则：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">向我们的关联方、第三方共享您的个人信息，需经过您的授权同意，除非共享的个人信息是去标识化处理后的信息，且共享第三方无法重新识别此类信息的自然人主体。如果关联方、第三方使用信息的目的超越原授权同意范围，他们需要重新征得您的同意。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">b.合法正当与最小必要原则：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">向关联方、第三方共享的数据必须具有合法正当目的，且共享的数据以达成目的必要为限。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">c.安全审慎原则：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们将审慎评估关联方、第三方数据使用共享信息的目的，对这些合作方的安全保障能力进行综合评估，并要求其遵循合作法律协议。我们会对合作方获取信息的软件工具开发包（SDK）、应用程序接口（API）进行严格的安全监测，以保护数据安全。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.1.2 实现功能或服务的共享信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.当您在使用</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">中由我们的关联方、第三方提供的功能，或者当软件服务提供商、智能设备提供商、系统服务提供商与我们联合为您提供服务时我们会将去标识化后的个人信息与这些关联方、第三方共享，进行综合统计并通过算法做特征与偏好分析，形成间接人群画像，用以向您进行推荐、展示或推送您可能感兴趣的信息，或者推送更适合您的特定功能、服务或商业广告。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.当您使用</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班建业通</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">功能时，为保障您所接受服务的一致性，并方便统一管理您的信息，我们可能会与这些功能的提供方共享经过去标识化处理的第1条第1.2b款中的日志信息用以向您推荐您可能感兴趣的信息。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.地理位置服务：当您使用地理位置相关服务时，我们会将GPS信息与位置服务提供商（高德地图）进行共享以便可以向您返回位置结果。GPS信息是敏感个人信息，拒绝提供，仅会影响地理位置服务功能，但不影响其他功能的正常使用。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">d</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.支付功能：支付功能由与我们合作的第三方支付机构向您提供服务。第三方支付机构可能需要收集您的姓名、</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">银行卡类型及卡号、有效期及手机号码。银行卡号、有效期及手机号码</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">是个人敏感信息，这些信息是支付功能所必需的信息，拒绝提供将导致您无法使用该功能，但不影响其他功能的正常使用。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.1.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 实现安全与分析统计的共享信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.保障使用安全：我们非常重视账号与服务安全，为保障您和其他用户的账号与财产安全，使您和我们的正当合法权益免受不法侵害，我们和关联公司或服务提供商可能会共享必要的设备、账号及日志信息。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.分析产品使用情况：为分析我们服务的使用情况，提升用户使用的体验，可能会与关联方或第三方共享产品使用情况（崩溃、闪退）的统计性数据，这些数据难以与其他信息结合识别您的个人身份。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.1.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">4</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 帮助您参加营销推广活动</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">当您选择参加我们及我们的关联方或第三方举办的有关营销活动时，可能需要您提供</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">姓名、通信地址、联系方式、银行账号</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">信息。</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">这些信息是敏感个人信息，</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">拒绝提供可能会影响您参加相关活动，但不会影响其他功能。只有经过您的同意，我们才会将这些信息与关联方或第三方共享，以保障您在联合活动中获得体验一致的服务，或委托第三方及时向您兑现奖励。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.2 转让</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.我们不会转让您的个人信息给任何其他第三方，除非征得您的明确同意。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.随着我们业务的持续发展，我们将有可能进行合并、收购、资产转让，您的个人信息有可能因此而被转移。在发生前述变更时，我们将按照法律法规及不低于本隐私政策所载明的安全标准要求继受方保护您的个人信息，否则我们将要求继受方重新征得您的授权同意。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.3 披露</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a. 我们不会公开披露您的信息，除非遵循国家法律法规规定或者获得您的同意。我们公开披露您的个人信息会采用符合行业内标准的安全保护措施.</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">b. 对违规账号、欺诈行为进行处罚公告时，我们会披露相关账号的信息。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">3.4 依法豁免征得同意共享、转让、公开披露的个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">请您理解，在下列情形中，根据法律法规及国家标准，我们共享、转让、公开披露您的个人信息无需征得您的授权同意：</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.与国家安全、国防安全直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.与公共安全、公共卫生、重大公共利益直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c.与犯罪侦查、起诉、审判和判决执行等直接相关的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">d.出于维护您或其他个人的生命、财产等重大合法权益但又很难得到本人同意的；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">e.您自行向社会公众公开的个人信息；</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">f.从合法公开披露的信息中收集个人信息的，如合法的新闻报道、政府信息公开等渠道。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">4.我们如何存储个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">4.1 存储地点</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们依照法律法规的规定，将在境内运营过程中收集和产生的您的个人信息存储于中华人民共和国境内。目前，我们不会将上述信息传输至境外，如果我们向境外传输，我们将会遵循相关国家规定或者征求您的同意。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">4.2 存储期限</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们仅在为提供“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”及服务之目的所必需的期间内保留您的个人信息：您发布的评论、点赞及相关信息，我们会保留相关信息。超出必要期限后，我们将对您的个人信息进行删除或匿名化处理，但法律法规另有规定的除外。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">5.我们如何保护个人信息的安全</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.我们非常重视您个人信息的安全，将努力采取合理的安全措施（包括技术方面和管理方面）来保护您的个人信息，防止您提供的个人信息被不当使用或未经授权的情况下被访问、公开披露、使用、修改、损坏、丢失或泄漏。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.我们会使用不低于行业同行的加密技术、匿名化处理及相关合理可行的手段保护您的个人信息，并使用安全保护机制防止您的个人信息遭到恶意攻击。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c.我们会建立专门的安全部门、安全管理制度、数据安全流程保障您的个人信息安全。我们采取严格的数据使用和访问制度，确保只有授权人员才可访问您的个人信息，并适时对数据和技术进行安全审计。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">d.尽管已经采取了上述合理有效措施，并已经遵守了相关法律规定要求的标准，但请您理解，由于技术的限制以及可能存在的各种恶意手段，在互联网行业，即便竭尽所能加强安全措施，也不可能始终保证信息百分之百的安全，我们将尽力确保您提供给我们的个人信息的安全性。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">e.您知悉并理解，您接入我们的服务所用的系统和通讯网络，有可能因我们可控范围外的因素而出现问题。因此，我们强烈建议您采取积极措施保护个人信息的安全，包括但不限于使用复杂密码、定期修改密码、不将自己的账号密码及相关个人信息透露给他人。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">f.我们会制定应急处理预案，并在发生用户信息安全事件时立即启动应急预案，努力阻止这些安全事件的影响和后果扩大。一旦发生用户信息安全事件（泄露、丢失）后，我们将按照法律法规的要求，及时向您告知：安全事件的基本情况和可能的影响、我们已经采取或将要采取的处置措施、您可自主防范和降低风险的建议、对您的补救措施。我们将及时将事件相关情况以推送通知、邮件、信函、短信及相关形式告知您，难以逐一告知时，我们会采取合理、有效的方式发布公告。同时，我们还将按照相关监管部门要求，上报用户信息安全事件的处置情况。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">g.您一旦离开“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”及相关服务，浏览或使用其他网站、服务及内容资源，我们将没有能力和直接义务保护您在</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">及相关服务之外的软件、网站提交的任何个人信息，无论您登录、浏览或使用上述软件、网站是否基于“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”的链接或引导。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.管理您的个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们非常重视您对个人信息的管理，并尽全力保护您对于您个人信息的查询、访问、修改、删除、撤回同意授权、注销账号、投诉举报以及设置隐私功能的相关权利，以使您有能力保障您的隐私和信息安全。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.1 自主选择控制个性化推荐信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">a.自主决定接收推送资讯：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们的信息推荐功能可能会根据信息系统、算法在内的自动化决策机制做出。我们不断完善与探索推荐系统优化方案的同时，在审核层面也采取了严格的审核策略。</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">特别地，为保障您接收信息的自主性，当您对我们推送的信息不感兴趣或希望减少某些信息推荐时，您可以</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">在“我的订阅”里关闭订阅功能</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">b.自主订阅所需的资讯：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">&nbsp;</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">在“我的关注”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">中会根据您主动选择关注的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">地区、业务类型、业主单位、代理机构、关注企业、关键字等信息</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">进行展示，您可以自主选择关注或取消关注。</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> </span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.2 改变或撤回授权范围</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.2.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">1</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 改变或撤回敏感权限设置</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">您可以在设备本身操作系统中关闭GPS地理位置、通讯录、摄像头、麦克风、相册权限改变同意范围或撤回您的授权。撤回授权后我们将不再收集与这些权限相关信息。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.2.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">2</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 改变或撤回授权的信息处理</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">特定的业务功能和服务将需要您的信息才能得以完成，当您撤回同意或授权后，我们无法继续为您提供撤回同意或授权所对应的功能和服务，也不再处理您相应的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:bold; text-decoration:underline\">个人信息。但您撤回同意或授权的决定，不会影响公司此前基于您的授权而开展的个人信息处理。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.3 访问、删除、更正您的个人信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.3.1 访问个人账号信息</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">您可以查询、访问您的头像、用户名、性别、</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">手机</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">、地区</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">，企业等</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">，您可以在</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">的“编辑资料”中进行查询、访问。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.3.2 查询访问、更正、取消您关注</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">标讯</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.进入</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\"> </span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">—</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">关注”在关注</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">条件</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">中查询、访问、取消关注您关注的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">条件</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">收藏</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">或</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">—</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的收藏</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">查看我收藏过的标讯</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">c.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">“我的”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">—</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">分享</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">查看我分享过的标讯</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.3.3</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">取</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">标讯</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">收藏与订阅</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">取消我的收藏：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">点击我收藏的标讯，查看详情，点击底部的五角星即可取消收藏。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">b</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">取消标讯订阅：</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">”</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\"> </span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">—</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt; font-weight:normal\">“我的订阅”里点击订阅开关即可取消相关订阅功能。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">4</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 投诉举报</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">您可按照我们公示的制度进行投诉或举报。如果您认为您的个人信息权利可能受到侵害，或者发现侵害个人信息权利的线索（例如：认为我们收集您的个人信息违反法律规定或者双方约定），</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">可以通过官网公布的联系方式与</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们联系。我们核查后会及时反馈您的投诉与举报。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">5</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\"> 访问隐私政策</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.您可以在注册页面，或者</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我的</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”—“关于”查看本隐私政策的全部内容或通过www.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">lubanjianye</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.com网页端查看本隐私政策全部内容。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.请您了解，本隐私政策中所述的“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”及相关服务可能会根据您所使用的手机型号、系统版本、软件应用程序版本、移动客户端等因素而有所不同。最终的产品和服务以您所使用的“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”软件及相关服务为准。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">6.7 停止运营向您告知</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">如我们停止运营，我们将及时停止收集您个人信息的活动，将停止运营的通知以逐一送达或公告的形式通知您，并对所持有的您的个人信息进行删除或匿名化处理。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">7</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">.隐私政策的修订和通知</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.为了给您提供更好的服务，</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">及相关服务将不时更新与变化，我们会适时对本隐私政策进行修订，这些修订构成本隐私政策的一部分并具有等同于本隐私政策的效力，未经您明确同意，我们不会削减您依据当前生效的本隐私政策所应享受的权利。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.本隐私政策更新后，我们会在</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">鲁班乐标</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">发出更新版本，并在更新后的条款生效前通过官方网站（www.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">lubanjianye</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.com）公告或其他适当的方式提醒您更新的内容，以便您及时了解本隐私政策的最新版本。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:10.5pt 0pt; text-align:justify\"><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">8</span><span style=\"color:#333333; font-family:微软雅黑; font-size:13.5pt; font-weight:bold\">.联系我们</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">a.如果您对个人信息保护问题有投诉、建议、疑问，您可以将问题发送至（</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">wangyp</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">@</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">lubanjianye</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.com），我们核查并验证您的用户身份后会及时反馈您的投诉与举报。</span></p>\n" +
                "    <p style=\"background-color:#ffffff; margin:22.5pt 0pt; text-align:justify; text-indent:24pt\"><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">b.如对本隐私政策内容有任何疑问、意见或建议，您可通过官方网站（www.</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">lubanjianye</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">.com）首页“</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">关于</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">我们—</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">联系方式</span><span style=\"color:#333333; font-family:微软雅黑; font-size:11.5pt\">”入口与我们联系。</span></p>\n" +
                "    <p style=\"margin:0pt; orphans:0; text-align:justify; widows:0\"><span style=\"font-family:等线; font-size:10.5pt\">&nbsp;</span></p>\n" +
                "</div>\n" +
                "<div class=\"cnzz\" style=\"display: none;\">\n" +
                "    <script src=\"https://s23.cnzz.com/z_stat.php?id=1277655852&amp;web_id=1277655852\" language=\"JavaScript\"></script>\n" +
                "</div>\n" +
                "<div class=\"docpe\" style=\"position: absolute;color: white;margin-left:-450;\"></div>\n" +
                "</body>\n" +
                "</html>", true);
        messageHelper.addInline("attach", new ClassPathResource("以后机会多.png"));

        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * 内联附件的邮件测试
     *
     * @return success
     * @throws MessagingException
     */
    @GetMapping("/freemarker")
    public String freemarker() throws MessagingException, IOException, TemplateException {
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
        return "success";
    }
}
