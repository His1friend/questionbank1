package org.homework.questions_bank.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    // 获取验证码图片
    @GetMapping("/generate")
    public Map<String, Object> generateCaptcha() throws IOException {
        String captchaId = UUID.randomUUID().toString();
        String text = defaultKaptcha.createText();

        // 使用 JWT 加密存储验证码
        String token = Jwts.builder()
                .setSubject(captchaId)
                .claim("captcha", text)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();

        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        Map<String, Object> result = new HashMap<>();
        result.put("captchaToken", token);
        result.put("captchaImage", "data:image/jpeg;base64," + base64Image);
        return result;
    }

    // 校验验证码
    @PostMapping("/validate")
    public ResponseEntity<String> validateCaptcha(
            @RequestParam("captchaToken") String captchaToken,
            @RequestParam("captcha") String userInputCaptcha) {
        try {
            // 解析 JWT Token
            String secretKey = "secretKey"; // 保持与生成验证码时的密钥一致
            var claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(captchaToken)
                    .getBody();

            // 获取存储的验证码
            String storedCaptcha = (String) claims.get("captcha");

            // 校验逻辑
            if (storedCaptcha == null || !storedCaptcha.equalsIgnoreCase(userInputCaptcha)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("验证码错误");
            }

            return ResponseEntity.ok("验证码验证成功");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码已过期");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码无效");
        }
    }

}
