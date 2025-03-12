package dev.yassiraitelghari.fms.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendVerificationEmail(String email, String verificationToken, String clientOrigin) {
        String verificationUrl = "<a href=http://localhost:9999/verify-email?token=" + verificationToken + "></a>";

        sendMail(
                email,
                "Verify Your Email",
                "To confirm your account, please click the link below:\n" + verificationUrl
        );
    }

    public void sendPasswordResetEmail(String email, String resetToken, String clientOrigin) {
        // Construct the reset link using the clientOrigin and resetToken
        String resetLink = clientOrigin + "/reset-password?token=" + resetToken;

        // Construct the email HTML content
        String emailContent = "<html dir=\"ltr\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title></title>\n" +
                "    <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "    <!--[if gte mso 9]>\n" +
                "<noscript>\n" +
                "         <xml>\n" +
                "           <o:OfficeDocumentSettings>\n" +
                "           <o:AllowPNG></o:AllowPNG>\n" +
                "           <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "           </o:OfficeDocumentSettings>\n" +
                "         </xml>\n" +
                "      </noscript>\n" +
                "<![endif]-->\n" +
                "    <!--[if mso]><xml>\n" +
                "    <w:WordDocument xmlns:w=\"urn:schemas-microsoft-com:office:word\">\n" +
                "      <w:DontUseAdvancedTypographyReadingMail/>\n" +
                "    </w:WordDocument>\n" +
                "    </xml><![endif]-->\n" +
                "  </head>\n" +
                "  <body class=\"body\">\n" +
                "    <div dir=\"ltr\" class=\"es-wrapper-color\">\n" +
                "      <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" color=\"#fafafa\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "      <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"es-wrapper\">\n" +
                "        <tbody>\n" +
                "          <tr>\n" +
                "            <td valign=\"top\" class=\"esd-email-paddings\">\n" +
                "              <table cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"es-header\">\n" +
                "                <tbody>\n" +
                "                  <tr>\n" +
                "                    <td align=\"center\" class=\"es-adaptive esd-stripe\">\n" +
                "                      <table width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#3d5ca3\" align=\"center\" class=\"es-header-body\" style=\"background-color:rgb(61, 92, 163)\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td bgcolor=\"#3d5ca3\" align=\"left\" class=\"esd-structure es-p20t es-p20b es-p20r es-p20l\" style=\"background-color:rgb(61, 92, 163)\">\n" +
                "                              <table cellspacing=\"0\" cellpadding=\"0\" align=\"left\" class=\"es-left\">\n" +
                "                                <tbody>\n" +
                "                                  <tr>\n" +
                "                                    <td width=\"560\" align=\"left\" class=\"es-m-p20b esd-container-frame\">\n" +
                "                                      <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\">\n" +
                "                                        <tbody>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"center\" class=\"esd-block-image\" style=\"font-size:0\">\n" +
                "                                              <a target=\"_blank\" href=\"http://localhost:5173/\">\n" +
                "                                                <img src=\"https://ftroedc.stripocdn.email/content/guids/a244219c-8981-42a2-b23f-871ff5dd61dc/images/fms.png\" alt=\"\" width=\"300\" class=\"adapt-img\">\n" +
                "                                              </a>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                        </tbody>\n" +
                "                                      </table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "              <table style=\" margin-top:40px\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"es-content\">\n" +
                "                <tbody>\n" +
                "                  <tr>\n" +
                "                    <td bgcolor=\"#fafafa\" align=\"center\" class=\"esd-stripe\" style=\"background-color:rgb(250, 250, 250)\">\n" +
                "                      <table esd-img-prev-src width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" class=\"es-content-body\" style=\"background-color:rgb(255, 255, 255)\">\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td esd-img-prev-src=\"https://fkus.stripocdn.email/content/guids/CABINET_8a8240f4650bd716d3cd69675fe184ca/images/1041555765740937.png\" esd-img-prev-position=\"left top\" esd-img-prev-repeat=\"no-repeat\" bgcolor=\"transparent\" align=\"left\" class=\"esd-structure es-p40t es-p20r es-p20l\" style=\"background-color:transparent;background-position:left top\">\n" +
                "                              <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                  <tr>\n" +
                "                                    <td width=\"560\" valign=\"top\" align=\"center\" class=\"esd-container-frame\">\n" +
                "                                      <table esd-img-prev-src esd-img-prev-position=\"left top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-position:left top\">\n" +
                "                                        <tbody>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"center\" class=\"esd-block-image es-p5t es-p5b\" style=\"font-size:0\">\n" +
                "                                              <a target=\"_blank\">\n" +
                "                                                <img src=\"https://ftroedc.stripocdn.email/content/guids/CABINET_dd354a98a803b60e2f0411e893c82f56/images/23891556799905703.png\" alt=\"\" width=\"175\" style=\"display:block\">\n" +
                "                                              </a>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"center\" class=\"esd-block-text es-p15t es-p15b\">\n" +
                "                                              <h1 style=\"color:#333333;font-size:20px\">\n" +
                "                                                <strong>FORGOT YOUR </strong>\n" +
                "                                              </h1>\n" +
                "                                              <h1 style=\"color:#333333;font-size:20px\">\n" +
                "                                                <strong>&nbsp;PASSWORD?</strong>\n" +
                "                                              </h1>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"left\" class=\"esd-block-text es-p40r es-p40l\">\n" +
                "                                              <p style=\"text-align:center\">\n" +
                "                                                HI There\n" +
                "                                              </p>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"left\" class=\"esd-block-text es-p35r es-p40l\">\n" +
                "                                              <p style=\"text-align:center\">\n" +
                "                                                There was a request to change your password!\n" +
                "                                              </p>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                          <tr>\n" +
                "                                            <td align=\"center\" class=\"esd-block-text es-p25t es-p40r es-p40l\">\n" +
                "                                              <p>\n" +
                "                                                If did not make this request, just ignore this email. Otherwise, please click the button below to change your password:\n" +
                "                                              </p>\n" +
                "                                            </td>\n" +
                "                                          </tr>\n" +
                "                                          <tr>\n" +
                "                                             <td align=\"center\" class=\"esd-block-button es-p40t es-p40b es-p10r es-p10l\">\n" +
                "                               <a href=\"" + resetLink + "\" style=\"background-color: #007bff; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; display: inline-block; font-weight: bold;\">\n" +
                "                               RESET PASSWORD\n" +
                "                               </a>\n" +
                "                             </td>\n" +
                "                                          </tr>\n" +
                "                                        </tbody>\n" +
                "                                      </table>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </tbody>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";

        // Send the email
        sendMail(email, "Reset Your Password", emailContent);
    }
    public void sendMail(String email, String subject, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true); // Set 'true' to enable HTML content

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
