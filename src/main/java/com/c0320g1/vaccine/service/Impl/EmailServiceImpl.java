package com.c0320g1.vaccine.service.Impl;

import com.c0320g1.vaccine.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {

    // Creator: Đức
    @Autowired
    private JavaMailSender javaMailSender;

    //CREATE BY ANH DUC
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("Trung Tâm Tiêm Chủng GPS <CGBVN@gmail.com>");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

    @Override
    public void sendSimpleHTMLMessage(String to, String subject, String text) throws MessagingException {

        LocalDate timeNow = LocalDate.now();
        String htmlMsg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                "  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                "      style=\"width:100%;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "  <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "  <title>Trung tâm tiên chủng GPS</title>\n" +
                "  <!--[if (mso 16)]>\n" +
                "  <style type=\"text/css\">\n" +
                "    a {\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "  </style>\n" +
                "  <![endif]-->\n" +
                "  <!--[if gte mso 9]>\n" +
                "  <style>sup {\n" +
                "    font-size: 100% !important;\n" +
                "  }</style><![endif]-->\n" +
                "  <!--[if gte mso 9]>\n" +
                "  <xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "      <o:AllowPNG></o:AllowPNG>\n" +
                "      <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "  </xml>\n" +
                "  <![endif]-->\n" +
                "  <!--[if !mso]><!-- -->\n" +
                "  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700,700i\" rel=\"stylesheet\">\n" +
                "  <!--<![endif]-->\n" +
                "  <style type=\"text/css\">\n" +
                "    #outlook a {\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    .ExternalClass {\n" +
                "      width: 100%;\n" +
                "    }\n" +
                "\n" +
                "    .ExternalClass,\n" +
                "    .ExternalClass p,\n" +
                "    .ExternalClass span,\n" +
                "    .ExternalClass font,\n" +
                "    .ExternalClass td,\n" +
                "    .ExternalClass div {\n" +
                "      line-height: 100%;\n" +
                "    }\n" +
                "\n" +
                "    .es-button {\n" +
                "      mso-style-priority: 100 !important;\n" +
                "      text-decoration: none !important;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "      font-size: inherit !important;\n" +
                "      font-family: inherit !important;\n" +
                "      font-weight: inherit !important;\n" +
                "      line-height: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    .es-desk-hidden {\n" +
                "      display: none;\n" +
                "      float: left;\n" +
                "      overflow: hidden;\n" +
                "      width: 0;\n" +
                "      max-height: 0;\n" +
                "      line-height: 0;\n" +
                "      mso-hide: all;\n" +
                "    }\n" +
                "\n" +
                "    @media only screen and (max-width: 600px) {\n" +
                "      p, ul li, ol li, a {\n" +
                "        font-size: 16px !important;\n" +
                "        line-height: 150% !important\n" +
                "      }\n" +
                "\n" +
                "      h1 {\n" +
                "        font-size: 32px !important;\n" +
                "        text-align: center;\n" +
                "        line-height: 120% !important\n" +
                "      }\n" +
                "\n" +
                "      h2 {\n" +
                "        font-size: 26px !important;\n" +
                "        text-align: center;\n" +
                "        line-height: 120% !important\n" +
                "      }\n" +
                "\n" +
                "      h3 {\n" +
                "        font-size: 20px !important;\n" +
                "        text-align: center;\n" +
                "        line-height: 120% !important\n" +
                "      }\n" +
                "\n" +
                "      h1 a {\n" +
                "        font-size: 32px !important\n" +
                "      }\n" +
                "\n" +
                "      h2 a {\n" +
                "        font-size: 26px !important\n" +
                "      }\n" +
                "\n" +
                "      h3 a {\n" +
                "        font-size: 20px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-menu td a {\n" +
                "        font-size: 16px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a {\n" +
                "        font-size: 16px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a {\n" +
                "        font-size: 16px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a {\n" +
                "        font-size: 12px !important\n" +
                "      }\n" +
                "\n" +
                "      *[class=\"gmail-fix\"] {\n" +
                "        display: none !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 {\n" +
                "        text-align: center !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 {\n" +
                "        text-align: right !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 {\n" +
                "        text-align: left !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img {\n" +
                "        display: inline !important\n" +
                "      }\n" +
                "\n" +
                "      .es-button-border {\n" +
                "        display: inline-block !important\n" +
                "      }\n" +
                "\n" +
                "      a.es-button {\n" +
                "        font-size: 16px !important;\n" +
                "        display: inline-block !important;\n" +
                "        border-width: 15px 30px 15px 30px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-btn-fw {\n" +
                "        border-width: 10px 0px !important;\n" +
                "        text-align: center !important\n" +
                "      }\n" +
                "\n" +
                "      .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right {\n" +
                "        width: 100% !important\n" +
                "      }\n" +
                "\n" +
                "      .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header {\n" +
                "        width: 100% !important;\n" +
                "        max-width: 600px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-adapt-td {\n" +
                "        display: block !important;\n" +
                "        width: 100% !important\n" +
                "      }\n" +
                "\n" +
                "      .adapt-img {\n" +
                "        width: 100% !important;\n" +
                "        height: auto !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p0 {\n" +
                "        padding: 0px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p0r {\n" +
                "        padding-right: 0px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p0l {\n" +
                "        padding-left: 0px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p0t {\n" +
                "        padding-top: 0px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p0b {\n" +
                "        padding-bottom: 0 !important\n" +
                "      }\n" +
                "\n" +
                "      .es-m-p20b {\n" +
                "        padding-bottom: 20px !important\n" +
                "      }\n" +
                "\n" +
                "      .es-mobile-hidden, .es-hidden {\n" +
                "        display: none !important\n" +
                "      }\n" +
                "\n" +
                "      tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden {\n" +
                "        width: auto !important;\n" +
                "        overflow: visible !important;\n" +
                "        float: none !important;\n" +
                "        max-height: inherit !important;\n" +
                "        line-height: inherit !important\n" +
                "      }\n" +
                "\n" +
                "      tr.es-desk-hidden {\n" +
                "        display: table-row !important\n" +
                "      }\n" +
                "\n" +
                "      table.es-desk-hidden {\n" +
                "        display: table !important\n" +
                "      }\n" +
                "\n" +
                "      td.es-desk-menu-hidden {\n" +
                "        display: table-cell !important\n" +
                "      }\n" +
                "\n" +
                "      .es-menu td {\n" +
                "        width: 1% !important\n" +
                "      }\n" +
                "\n" +
                "      table.es-table-not-adapt, .esd-block-html table {\n" +
                "        width: auto !important\n" +
                "      }\n" +
                "\n" +
                "      table.es-social {\n" +
                "        display: inline-block !important\n" +
                "      }\n" +
                "\n" +
                "      table.es-social td {\n" +
                "        display: inline-block !important\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body\n" +
                "  style=\"width:100%;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\n" +
                "<div class=\"es-wrapper-color\" style=\"background-color:#EEEEEE\">\n" +
                "  <!--[if gte mso 9]>\n" +
                "  <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "    <v:fill type=\"tile\" color=\"#eeeeee\"></v:fill>\n" +
                "  </v:background>\n" +
                "  <![endif]-->\n" +
                "  <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "         style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\">\n" +
                "    <tr style=\"border-collapse:collapse\">\n" +
                "      <td valign=\"top\" style=\"padding:0;Margin:0\">\n" +
                "        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\"\n" +
                "               style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "          <tr style=\"border-collapse:collapse\">\n" +
                "            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "              <table class=\"es-content-body\"\n" +
                "                     style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"\n" +
                "                     cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
                "                <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\"\n" +
                "                      style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:15px;padding-bottom:15px\">\n" +
                "                    <!--[if mso]>\n" +
                "                    <table style=\"width:580px\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      <tr>\n" +
                "                        <td style=\"width:282px\" valign=\"top\"><![endif]-->\n" +
                "                    <!--[if mso]></td>\n" +
                "                  <td style=\"width:20px\"></td>\n" +
                "                  <td style=\"width:278px\" valign=\"top\"><![endif]-->\n" +
                "                    <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"left\" style=\"padding:0;Margin:0;width:278px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"right\" class=\"es-infoblock es-m-txt-c\"\n" +
                "                                  style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p\n" +
                "                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:12px;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:14px;color:#CCCCCC\">\n" +
                "                                <a href=\"https://viewstripo.email\" class=\"view\" target=\"_blank\"\n" +
                "                                   style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:12px;text-decoration:none;color:#CCCCCC\"><font\n" +
                "                                  style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Xem trong trình\n" +
                "                                  duyệt</font></font></a></p></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "               style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "          <tr style=\"border-collapse:collapse\"></tr>\n" +
                "          <tr style=\"border-collapse:collapse\">\n" +
                "            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "              <table class=\"es-header-body\"\n" +
                "                     style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#044767;width:600px\"\n" +
                "                     cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#044767\" align=\"center\">\n" +
                "                <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\"\n" +
                "                      style=\"Margin:0;padding-top:35px;padding-bottom:35px;padding-left:35px;padding-right:35px\">\n" +
                "                    <!--[if mso]>\n" +
                "                    <table style=\"width:530px\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      <tr>\n" +
                "                        <td style=\"width:340px\" valign=\"top\"><![endif]-->\n" +
                "                    <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\"\n" +
                "                            style=\"padding:0;Margin:0;width:340px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0\"><h1\n" +
                "                                style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:36px;font-style:normal;font-weight:bold;color:#FFFFFF\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Tiêm Chủng\n" +
                "                                  GPS</font></font></h1></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!--[if mso]></td>\n" +
                "                  <td style=\"width:20px\"></td>\n" +
                "                  <td style=\"width:170px\" valign=\"top\"><![endif]-->\n" +
                "                    <table cellspacing=\"0\" cellpadding=\"0\" align=\"right\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                      <tr class=\"es-hidden\" style=\"border-collapse:collapse\">\n" +
                "                        <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:170px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:5px;font-size:0\">\n" +
                "                                <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"\n" +
                "                                       role=\"presentation\"\n" +
                "                                       style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                                  <tr style=\"border-collapse:collapse\">\n" +
                "                                    <td\n" +
                "                                      style=\"padding:0;Margin:0;border-bottom:1px solid #044767;background:#FFFFFFnone repeat scroll 0% 0%;height:1px;width:100%;margin:0px\"></td>\n" +
                "                                  </tr>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "               style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "          <tr style=\"border-collapse:collapse\">\n" +
                "            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "              <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"\n" +
                "                     style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "                <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\" style=\"padding:0;Margin:0;padding-left:35px;padding-right:35px;padding-top:40px\">\n" +
                "                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:530px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"center\"\n" +
                "                                  style=\"Margin:0;padding-top:25px;padding-bottom:25px;padding-left:35px;padding-right:35px;font-size:0\">\n" +
                "                                <a target=\"_blank\" href=\"https://viewstripo.email/\"\n" +
                "                                   style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:16px;text-decoration:none;color:#ED8E20\"><img\n" +
                "                                  src=\"https://iquebc.stripocdn.email/content/guids/CABINET_75694a6fc3c4633b3ee8e3c750851c02/images/67611522142640957.png\"\n" +
                "                                  alt\n" +
                "                                  style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"\n" +
                "                                  width=\"120\"></a></td>\n" +
                "                            </tr>\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:10px\"><h2\n" +
                "                                style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#333333\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Phản Hồi : " + subject + "</font></font></h2></td>\n" +
                "                            </tr>\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"left\" style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:20px\"><p\n" +
                "                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:24px;color:#777777\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">" + text + " </font><font\n" +
                "                              </p></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "               style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "          <tr style=\"border-collapse:collapse\">\n" +
                "            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "              <table class=\"es-content-body\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"\n" +
                "                     style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\n" +
                "                <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\"\n" +
                "                      style=\"Margin:0;padding-left:35px;padding-right:35px;padding-top:40px;padding-bottom:40px\">\n" +
                "                    <!--[if mso]>\n" +
                "                    <table style=\"width:530px\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      <tr>\n" +
                "                        <td style=\"width:255px\" valign=\"top\"><![endif]-->\n" +
                "                    <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:255px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:15px\"><h4\n" +
                "                                style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Địa\n" +
                "                                  Chỉ&nbsp;</font></font></h4></td>\n" +
                "                            </tr>\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:10px\"><p\n" +
                "                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:24px;color:#333333\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">295 Nguyễn Tất\n" +
                "                                  Thành, Hải Châu, Đà Nẵng</font></font></p></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!--[if mso]></td>\n" +
                "                  <td style=\"width:20px\"></td>\n" +
                "                  <td style=\"width:255px\" valign=\"top\"><![endif]-->\n" +
                "                    <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:15px\"><h4\n" +
                "                          style=\"Margin:0;line-height:120%;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif\">\n" +
                "                          <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Hôm Nay\n" +
                "                          </font></font></h4></td>\n" +
                "                      </tr>\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td align=\"left\" style=\"padding:0;Margin:0;width:255px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"left\" style=\"padding:0;Margin:0\"><p\n" +
                "                                style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;line-height:24px;color:#333333\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font\n" +
                "                                  style=\"vertical-align:inherit\">"+timeNow+"</font></font></p></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!--[if mso]></td></tr></table><![endif]--></td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "               style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\n" +
                "          <tr style=\"border-collapse:collapse\">\n" +
                "            <td align=\"center\" style=\"padding:0;Margin:0\">\n" +
                "              <table class=\"es-content-body\"\n" +
                "                     style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1B9BA3;width:600px\"\n" +
                "                     cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#1b9ba3\" align=\"center\">\n" +
                "                <tr style=\"border-collapse:collapse\">\n" +
                "                  <td align=\"left\"\n" +
                "                      style=\"Margin:0;padding-top:35px;padding-bottom:35px;padding-left:35px;padding-right:35px\">\n" +
                "                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                           style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                      <tr style=\"border-collapse:collapse\">\n" +
                "                        <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:530px\">\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"center\" style=\"padding:0;Margin:0;padding-top:25px\"><h2\n" +
                "                                style=\"Margin:0;line-height:29px;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:24px;font-style:normal;font-weight:bold;color:#FFFFFF\">\n" +
                "                                <font style=\"vertical-align:inherit\"><font style=\"vertical-align:inherit\">Chúc bạn một\n" +
                "                                  ngày làm việc vui vẻ</font></font></h2></td>\n" +
                "                            </tr>\n" +
                "                            <tr style=\"border-collapse:collapse\">\n" +
                "                              <td align=\"center\"\n" +
                "                                  style=\"Margin:0;padding-left:10px;padding-right:10px;padding-bottom:15px;padding-top:30px\">\n" +
                "                                <span class=\"es-button-border\"\n" +
                "                                      style=\"border-style:solid;border-color:transparent;background:#66B3B7;border-width:0px;display:inline-block;border-radius:5px;width:auto\"><a\n" +
                "                                  href=\"https://viewstripo.email/\" class=\"es-button\" target=\"_blank\"\n" +
                "                                  style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'open sans', 'helvetica neue', helvetica, arial, sans-serif;font-size:18px;color:#FFFFFF;border-style:solid;border-color:#66B3B7;border-width:15px 30px 15px 30px;display:inline-block;background:#66B3B7;border-radius:5px;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center\"><font\n" +
                "                                  style=\"vertical-align:inherit\"><font\n" +
                "                                  style=\"vertical-align:inherit\">Tuyệt vời</font></font></a></span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <!--\n" +
                "               </td>\n" +
                "             </tr>\n" +
                "           </table>\n" +
                "          </div>\n" +
                "         </body>\n" +
                "        </html>\n";
        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setFrom("Trung Tâm Tiêm Chủng GPS <CGBVN@gmail.com>");
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(to);
        // Create the HTML body using Thymeleaf
        mimeMessageHelper.setText(htmlMsg, true);
        javaMailSender.send(mimeMessage);
    }
}
