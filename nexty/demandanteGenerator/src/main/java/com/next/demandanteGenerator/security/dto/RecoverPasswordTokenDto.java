package com.next.demandanteGenerator.security.dto;


import lombok.Getter;

import java.util.Random;

@Getter
public class RecoverPasswordTokenDto {
    private final String keyRecover;

    public RecoverPasswordTokenDto() {
        this.keyRecover = generatePassword(8);
    }

    private String generatePassword(int length) {
        String token = "";
        boolean tokenOK = false;

        String[] l = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
                "a", "s", "d", "f", "g", "h", "j", "k", "l", "ç", "z", "x",
                "c", "v", "b", "n", "m" };

        String[] u = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ç", "Z", "X",
                "C", "V", "B", "N", "M" };

        String[] n = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        String[] c = { "!", "@", "_", "$", "%", "&", "*", ".", "/",
                "#", "?" };

        do {
            int r = new Random().nextInt(4) + 1;

            switch (r) {
                case 1:
                    r = new Random().nextInt(26);
                    for (; r < l.length;) {
                        token = token.concat(l[r]);
                        break;
                    }
                    break;

                case 2:
                    r = new Random().nextInt(26);
                    for (; r < u.length;) {
                        token = token.concat(u[r]);
                        break;
                    }
                    break;

                case 3:
                    r = new Random().nextInt(9);
                    for (; r < n.length;) {
                        token = token.concat(n[r]);
                        break;
                    }
                    break;

                case 4:
                    r = new Random().nextInt(10);
                    for (; r < c.length;) {
                        token = token.concat(c[r]);
                        break;
                    }
                    break;
            }

            // verifica se há pelo menos 1 carácter de cada tipo
            for (int count1 = 0; count1 < l.length; count1++) {
                for (int count2 = 0; count2 < u.length; count2++) {
                    for (int count3 = 0; count3 < n.length; count3++) {
                        for (int count4 = 0; count4 < c.length; count4++) {
                            if (token.contains(l[count1])
                                    && token.contains(u[count2])
                                    && token.contains(n[count3])
                                    && token.contains(c[count4])) {
                                tokenOK = true;
                            }
                        }
                    }
                }
            }
        } while (token.length() < length);

        return token;
    }
}