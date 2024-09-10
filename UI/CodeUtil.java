package UI;

import javax.swing.*;
import java.util.Random;

public class CodeUtil {

        // 生成4位随机验证码
        public static String getCode() {
            String code = "";
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                int rand = random.nextInt(26);  // 生成0-25的数字
                char c = (char) (rand + 'a');  // 转换成字母
                code += c;
            }
            return code;
        }

}
