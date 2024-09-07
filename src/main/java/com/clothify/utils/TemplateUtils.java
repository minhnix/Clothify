package com.clothify.utils;

public class TemplateUtils {
    public static String getMailRegisterTemplate(String to, String expiredTime, String linkVerify) {
        return "<!DOCTYPE html><html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"x-apple-disable-message-reformatting\"><title></title><link href=\"https://fonts.googleapis.com/css?family=Roboto:400,600\" rel=\"stylesheet\" type=\"text/css\"><!-- Web Font / @font-face : BEGIN --><!--[if mso]><style>* {font-family: 'Roboto', sans-serif !important;}</style><![endif]--><!--[if !mso]><link href=\"https://fonts.googleapis.com/css?family=Roboto:400,600\" rel=\"stylesheet\" type=\"text/css\"><![endif]--><!-- Web Font / @font-face : END --><!-- CSS Reset : BEGIN --><style>html,body{margin:0 auto!important;padding:0!important;height:100%!important;width:100%!important;font-family:'Roboto',sans-serif!important;font-size:14px;margin-bottom:10px;line-height:24px;color:#8094ae;font-weight:400;}*{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;margin:0;padding:0;}table,td{mso-table-lspace:0pt!important;mso-table-rspace:0pt!important;}table{border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important;}table table table{table-layout:auto;}a{text-decoration:none;}img{-ms-interpolation-mode:bicubic;}</style></head><body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #f5f6fa;\"><center style=\"width: 100%; background-color: #f5f6fa;\"><table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f5f6fa\"><tr><td style=\"padding: 40px 0;\"><table style=\"width:100%;max-width:620px;margin:0 auto;background-color:#ffffff;\"><tbody><tr><td style=\"padding: 30px 30px 15px 30px;\"><h2 style=\"text-align:center; font-size: 26px; color: #6576ff; font-weight: 600; margin: 0;\">Xác nhận địa chỉ email</h2></td></tr><tr><td style=\"padding: 0 30px 20px\"><p style=\"margin-bottom: 10px;\">Xin chào " + to + "! <br>Bạn vừa tạo một tài khoản trên SHOPNIX. Vui lòng hãy xác nhận địa chỉ email này để chúng tôi biết bạn là chủ sở hữu hợp pháp của tài khoản này.</p><p style=\"margin-bottom: 22px;\">Liên kết bên dưới sẽ tồn tại trong vòng " + expiredTime + ".</p><a href=\"" + linkVerify +"\" style=\"background-color:#6576ff;border-radius:4px;color:#ffffff;display:inline-block;font-size:13px;font-weight:600;line-height:44px;text-align:center;text-decoration:none;text-transform:uppercase;padding:0 30px\">Verify Email</a></td></tr><tr><td style=\"padding: 20px 30px 40px\"><p style=\"margin-bottom: 5px\">Nếu bạn không thực hiện điều này, có thể liên lạc với chúng tôi hoặc bỏ qua tin nhắn này.</p><p style=\"margin: 0; font-size: 13px; line-height: 22px; color: #9ea8bb;\">Đây là tin nhắn tự động vui lòng không trả lời email này. Nếu bạn gặp phải bất kỳ vấn đề nào, vui lòng liên hệ với chúng tôi tại shop.nix@gmail.com</p></td></tr></tbody></table><table style=\"width:100%;max-width:620px;margin:0 auto;\"><tbody><tr><td style=\"text-align: center; padding: 25px 20px 0;\"><p style=\"font-size: 13px;\">Copyright © 2024 Shop NIX. All rights reserved.</p></td></tr></tbody></table></td></tr></table></center></body></html>";
    }
}
