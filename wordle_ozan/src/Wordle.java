//oldu    dict.txt dosyasından kelime setini bir String dizisine yükleyin.
//oldu    Dosyanın 2317 kelime içerdiğini unutmayın, bu nedenle dizi boyutunu sabit olarak ayarlamak için hard-coding yöntemini benimseyebilirsiniz.
//oldu    Bu kelimeler arasından rastgele bir kelimeyi anahtar kelime olarak seçin.
//oldu    Programınıza komut satırı argümanı olarak altı kelime sağlamak için kullanıcıya izin verin.
//oldu    Her kelime için, programınız önce sağlanan kelimenin uzunluğunun beşten az veya büyük olup olmadığını kontrol etmelidir.
//oldu    Eğer öyleyse, "Kelimenin uzunluğu beş olmalı!" şeklinde bir çıktı vermelidir.
//oldu     Kullanıcının sağladığı kelimenin, wordle oyunundaki gerçek durumda olduğu gibi, sözlükte (yani, dizide) bulunması gerekmektedir.
//oldu     Bu nedenle, programınız kelimenin dizide var olup olmadığını kontrol etmelidir.
//oldu     Eğer kelime mevcut değilse, "Kelime sözlükte bulunmamaktadır!" şeklinde bir çıktı vermelidir.
//oldu     Kullanıcı son denemesinden önce anahtar kelimeyi doğru tahmin ettiğinde, aşağıdaki mesaj görüntülenmeli ve artık girişler değerlendirilmemelidir:
//oldu     Tebrikler! k. denemede doğru tahmin ettiniz!
//oldu     Burada k, bir deneme sayacıdır (yani, 1 ≤ k ≤ 6),
//oldu     bu nedenle sıra belirteci için ekler (örneğin, -st, -nd, -rd veya -th) k ile tutarlı olmalıdır.
//oldu     (örneğin, "Tebrikler! [1st, 2nd, 3rd, 4th, ...] denemede doğru tahmin ettiniz!")
//oldu     Yukarıdaki kriterlerin hiçbiri sağlanmazsa, programınızın kelimeyi harf harf incelemesi gerekmektedir.
//oldu     Anahtar kelime BASIC ve kullanıcı girişi DASNB3 için çıktı aşağıdaki gibi olmalıdır:
//oldu     1.Harf mevcut değil.
//oldu     2.Harf mevcut ve doğru konumda.
//oldu     3.Harf mevcut ve doğru konumda.
//oldu     4.Harf mevcut değil.
//oldu     5.Harf mevcut ancak yanlış konumda.
//     Kullanıcı tüm denemelerini tükettiğinde anahtar kelimeyi bulamadığı durumda,
//     programınız "Başarısız oldunuz! Anahtar kelime KEY." şeklinde bir çıktı vermeli; burada KEY bir yer tutucudur.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        // Words array created according to number of words in dic.tx
        String[] words=new String[2317];

        //dic.txt is readable for adding into array
        try (BufferedReader br = new BufferedReader(new FileReader("dict.txt"))) {
            String line;

            //All lines in txt are being added
            for (int i = 0; i < 2317; i++) {
                line=br.readLine();
                words[i]=line;
            }
        // If there is an input/output error print source of it
        } catch (IOException e) {
            e.printStackTrace();
        }
        //implementing Random class
        Random random=new Random();

        //It makes a random number between 0 and 2317
        int randomIndex= random.nextInt(2317);
        //Random key is created
        String keyWord=words[randomIndex];
        System.out.println(keyWord);

        //Implementing Scanner class
        Scanner scanner=new Scanner(System.in);

        int finishCounter=0;
        //Game is starting
        for (int starting = 0; starting < 6; starting++) {
            System.out.println("Enter a 5 letter word");
            //Entering input
            String input=scanner.nextLine();
            //Checking input is 5 letter or not
            if(input.length()==5){
                //If input is in the array
                //All array words are uppercase, so we must transition input to uppercase
                if (Arrays.asList(words).contains(input.toUpperCase())){
                    //The key is found
                    if (input.toUpperCase().equals(keyWord)){
                        //To arrange ordinal indicatories
                        String order = switch (starting) {
                            case 0 -> //First
                                    "th";
                            case 1 -> //Second
                                    "nd";
                            case 2 -> "rd"; //Third
                            default -> "th"; //Fourth,Fifth,Sixth
                        };
                        System.out.print("Congratulations! You guess right in "+(starting+1)+order+" shot!");
                        //Game is ended
                        break;
                    }else { //Key couldn't be found
                        for (int letters = 0; letters < 5; letters++) {
                            //Key contain letter
                            if (keyWord.contains(String.valueOf(input.toUpperCase().charAt(letters)))){
                                //Key letter and input letter are equal
                                if (input.toUpperCase().charAt(letters)==keyWord.charAt(letters)){
                                    System.out.println((letters+1)+". letter exists and located in right position.");
                                }else { //Key contain letter but not in right place
                                    System.out.println((letters+1)+". letter exists but located in wrong position.");
                                }

                            }else{ //Key not contain letter
                                System.out.println((letters+1)+". letter does not exist.");
                            }
                        }
                    }
                    // doesn't exist

                }else {
                    System.out.println("The word does not exist in the dictionary!");
                }
            }
            else{
                System.out.println("The length of word must be five!");
            }
            finishCounter++;
        }
        if (finishCounter==6){
            System.out.println("You failed! The key word is "+keyWord+".");
        }
    }
}

