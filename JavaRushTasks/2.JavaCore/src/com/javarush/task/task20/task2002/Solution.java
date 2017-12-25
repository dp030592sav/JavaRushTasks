package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush.
В файле your_file_name.tmp может быть несколько объектов JavaRush.
Метод main реализован только для вас и не участвует в тестировании.


Требования:
1. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users пустой.
2. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users не пустой.
3. Класс Solution.JavaRush не должен поддерживать интерфейс Serializable.
4. Класс Solution.JavaRush должен быть публичным.
5. Класс Solution.JavaRush не должен поддерживать интерфейс Externalizable.
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("C:\\Users\\user\\Desktop\\qwe1.txt");
            InputStream inputStream = new FileInputStream("C:\\Users\\user\\Desktop\\qwe1.txt");
            JavaRush javaRush = new JavaRush();

            User user1 = new User();
            user1.setFirstName("user");
            user1.setLastName("1");
            user1.setBirthDate(new Date());
            user1.setMale(true);
            user1.setCountry(User.Country.UKRAINE);

            javaRush.users.add(user1);

            User user2 = new User();
            user2.setFirstName("user");
            user2.setLastName("2");
            user2.setBirthDate(new Date());
            user2.setMale(false);
            user2.setCountry(User.Country.RUSSIA);

            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);

            for (User i : users) {
                writer.print(i.getFirstName() + " " + i.getLastName() + " "
                        + i.getBirthDate().getTime() + " " + i.isMale() + " " + i.getCountry()
                        + System.getProperty("line.separator"));
            }

            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            while (reader.ready()) {
                String[] values = reader.readLine().split(" ");
                User user = new User();
                user.setFirstName(values[0]);
                user.setLastName(values[1]);
                Date date = new Date();
                date.setTime(Long.parseLong(values[2]));
                user.setBirthDate(date);
                user.setMale(Boolean.parseBoolean(values[3]));
                user.setCountry(User.Country.valueOf(values[4]));
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
